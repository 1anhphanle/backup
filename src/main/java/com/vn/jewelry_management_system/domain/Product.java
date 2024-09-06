package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String productName;
    private String productType;
    private BigDecimal weight;
    private String material;
    private BigDecimal makingFee;
    private BigDecimal gemstonePrice;
    private BigDecimal costPrice;
    private BigDecimal markupRatio;
    private BigDecimal sellingPrice;

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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
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

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}