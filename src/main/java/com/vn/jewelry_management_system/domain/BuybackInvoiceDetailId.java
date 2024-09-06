package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable // Khai báo là EmbeddedId
public class BuybackInvoiceDetailId implements Serializable {
    private int buybackInvoiceId;
    private int productId;

    // Constructors

    public BuybackInvoiceDetailId() {
    }

    public BuybackInvoiceDetailId(int buybackInvoiceId, int productId) {
        this.buybackInvoiceId = buybackInvoiceId;
        this.productId = productId;
    }

    public int getBuybackInvoiceId() {
        return buybackInvoiceId;
    }

    public void setBuybackInvoiceId(int buybackInvoiceId) {
        this.buybackInvoiceId = buybackInvoiceId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}