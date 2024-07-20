package com.projects.ecommerce.service;

import com.projects.ecommerce.dao.ProductDao;
import com.projects.ecommerce.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDao productDao;
    public Product addNewProduct(Product product){
        return productDao.save(product);
    }

    public List<Product> getAllProducts(){
        return  productDao.findAll();
    }

    public void removeProduct(Integer productId) {
        productDao.deleteById(productId);
    }

    public Product updateProduct(Product product) {
        Product currentProductDetails = productDao.findById(product.getProdcutId()).get();
        if(currentProductDetails != null){
            productDao.deleteById(product.getProdcutId());
            return productDao.save(product);
        }
        return null;
    }
}
