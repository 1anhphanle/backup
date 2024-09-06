package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    ProductType productType;

    private BigDecimal weight;
    private String material;
    private BigDecimal makingFee;
    private BigDecimal gemstonePrice;
    private BigDecimal costPrice;
    private BigDecimal markupRatio;
    private String image;
    private boolean status;

    private BigDecimal sellingPrice;

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    // Constructors, Getters and Setters

    public Product() {
    }

    // Constructor without productType
    public Product(String productName, BigDecimal weight, String material, BigDecimal makingFee,
            BigDecimal gemstonePrice, BigDecimal costPrice, BigDecimal markupRatio, String image, boolean status) {
        this.productName = productName;
        this.weight = weight;
        this.material = material;
        this.makingFee = makingFee;
        this.gemstonePrice = gemstonePrice;
        this.costPrice = costPrice;
        this.markupRatio = markupRatio;
        this.image = image;
        this.status = status;
    }

    // Constructor with productType
    public Product(String productName, ProductType productType, BigDecimal weight, String material,
            BigDecimal makingFee,
            BigDecimal gemstonePrice, BigDecimal costPrice, BigDecimal markupRatio, String image, boolean status) {
        this.productName = productName;
        this.productType = productType;
        this.weight = weight;
        this.material = material;
        this.makingFee = makingFee;
        this.gemstonePrice = gemstonePrice;
        this.costPrice = costPrice;
        this.markupRatio = markupRatio;
        this.image = image;
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public BigDecimal getMakingFee() {
        return makingFee;
    }

    public void setMakingFee(BigDecimal makingFee) {
        this.makingFee = makingFee;
    }

    public BigDecimal getGemstonePrice() {
        return gemstonePrice;
    }

    public void setGemstonePrice(BigDecimal gemstonePrice) {
        this.gemstonePrice = gemstonePrice;
    }

    public BigDecimal getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    public BigDecimal getMarkupRatio() {
        return markupRatio;
    }

    public void setMarkupRatio(BigDecimal markupRatio) {
        this.markupRatio = markupRatio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", weight=" + weight + ", material="
                + material + ", makingFee=" + makingFee + ", gemstonePrice=" + gemstonePrice + ", costPrice="
                + costPrice + ", markupRatio=" + markupRatio + ", image=" + image + ", status=" + status + "]";
    }

}