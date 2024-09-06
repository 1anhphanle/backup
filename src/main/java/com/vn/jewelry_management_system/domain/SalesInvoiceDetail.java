package com.vn.jewelry_management_system.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class SalesInvoiceDetail {
    @EmbeddedId // Sử dụng EmbeddedId cho khóa chính composite
    private SalesInvoiceDetailId id;

    private int quantity;
    private BigDecimal unitPrice;

    @ManyToOne
    @MapsId("salesInvoiceId")
    private SalesInvoice salesInvoice;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    // Constructors, Getters and Setters

    public SalesInvoiceDetail() {
    }

    public SalesInvoiceDetail(SalesInvoice salesInvoice, Product product, int quantity, BigDecimal unitPrice) {
        this.id = new SalesInvoiceDetailId(salesInvoice.getSalesInvoiceId(), product.getProductId());
        this.salesInvoice = salesInvoice;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public SalesInvoiceDetailId getId() {
        return id;
    }

    public void setId(SalesInvoiceDetailId id) {
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

    public SalesInvoice getSalesInvoice() {
        return salesInvoice;
    }

    public void setSalesInvoice(SalesInvoice salesInvoice) {
        this.salesInvoice = salesInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}