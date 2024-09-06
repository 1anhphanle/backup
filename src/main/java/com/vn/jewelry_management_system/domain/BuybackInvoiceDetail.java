package com.vn.jewelry_management_system.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class BuybackInvoiceDetail {
    @EmbeddedId // Sử dụng EmbeddedId cho khóa chính composite
    private BuybackInvoiceDetailId id;

    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @MapsId("buybackInvoiceId")
    private BuybackInvoice buybackInvoice;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    // Constructors, Getters and Setters

    public BuybackInvoiceDetail() {
    }

    public BuybackInvoiceDetail(BuybackInvoice buybackInvoice, Product product, int quantity, BigDecimal unitPrice) {
        this.id = new BuybackInvoiceDetailId(buybackInvoice.getBuybackInvoiceId(), product.getProductId());
        this.buybackInvoice = buybackInvoice;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public BuybackInvoiceDetailId getId() {
        return id;
    }

    public void setId(BuybackInvoiceDetailId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BuybackInvoice getBuybackInvoice() {
        return buybackInvoice;
    }

    public void setBuybackInvoice(BuybackInvoice buybackInvoice) {
        this.buybackInvoice = buybackInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}