package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable // Khai báo là EmbeddedId
public class SalesInvoiceDetailId implements Serializable {
    private int salesInvoiceId;
    private int productId;

    // Constructors

    public SalesInvoiceDetailId() {
    }

    public SalesInvoiceDetailId(int salesInvoiceId, int productId) {
        this.salesInvoiceId = salesInvoiceId;
        this.productId = productId;
    }

    public int getSalesInvoiceId() {
        return salesInvoiceId;
    }

    public void setSalesInvoiceId(int salesInvoiceId) {
        this.salesInvoiceId = salesInvoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}