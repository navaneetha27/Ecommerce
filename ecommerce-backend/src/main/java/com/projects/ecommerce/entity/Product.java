package com.projects.ecommerce.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(schema = "ecommerce" , name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prodcutId;
    private String productName;
    private String productDescription;
    private Double productDiscountedPrice;

    private Double productAcutalPrice;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = {
            @JoinColumn(name = "product_id")
            },
            inverseJoinColumns = {
            @JoinColumn(name = "image_id")
            }
    )
    private Set<ImageModel> productImages;

    public Integer getProdcutId() {
        return prodcutId;
    }

    public void setProdcutId(Integer prodcutId) {
        this.prodcutId = prodcutId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Set<ImageModel> getProductImages() {
        return productImages;
    }

    public void setProductImages(Set<ImageModel> productImages) {
        this.productImages = productImages;
    }

    public Double getProductDiscountedPrice() {
        return productDiscountedPrice;
    }

    public void setProductDiscountedPrice(Double productDiscountedPrice) {
        this.productDiscountedPrice = productDiscountedPrice;
    }

    public Double getProductAcutalPrice() {
        return productAcutalPrice;
    }

    public void setProductAcutalPrice(Double productAcutalPrice) {
        this.productAcutalPrice = productAcutalPrice;
    }
}
