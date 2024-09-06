package com.vn.jewelry_management_system.domain;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productTypeId;

    private String productTypeName;

    @OneToMany(mappedBy = "productType", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Product> products;

    // Constructors, Getters and Setters

    public ProductType() {
    }

    public ProductType(String productTypeName, List<Product> products) {
        this.productTypeName = productTypeName;
        this.products = products;
    }

    // Constructor without products
    public ProductType(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductType [productTypeId=" + productTypeId + ", productTypeName=" + productTypeName + "]";
    }
}