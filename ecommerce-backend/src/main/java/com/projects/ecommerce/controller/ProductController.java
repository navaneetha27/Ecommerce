package com.projects.ecommerce.controller;

import com.projects.ecommerce.entity.ImageModel;
import com.projects.ecommerce.entity.Product;
import com.projects.ecommerce.dto.SearchRequest;
import com.projects.ecommerce.helper.MultiPartFileProcessor;
import com.projects.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @PostMapping(value = {"/product/add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE} )

    public Product addNewProduct(@RequestPart("product") Product product, @RequestPart("imageFile")MultipartFile[] file){
        try{
            Set<ImageModel> imageModelSet = MultiPartFileProcessor.uploadImage(file);
            product.setProductImages(imageModelSet);
        }
        catch (Exception e){
            throw  new RuntimeException("Invalid file!!");
        }
        return productService.addNewProduct(product);
    }

    @GetMapping(value = {"/product/get"})
    public List<Product> getAllProducts(){
        return  productService.getAllProducts();
    }

    @DeleteMapping(value = {"product/{id}"})
    public void removeProduct(@PathVariable("id") Integer productId){
        productService.removeProduct(productId);
    }

    @PostMapping(value = {"/product/{id}"},consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product updateProduct(@RequestPart("product") Product product, @RequestPart("imageFile")MultipartFile[] file){
        try{
            Set<ImageModel> imageModelSet = MultiPartFileProcessor.uploadImage(file);
            product.setProductImages(imageModelSet);
        }
        catch (Exception e){
            throw  new RuntimeException("Invalid file!!");
        }
        return productService.updateProduct(product);
    }
    public List<Product>  searchProduct(@RequestBody SearchRequest request){
        return null;
    }
}
