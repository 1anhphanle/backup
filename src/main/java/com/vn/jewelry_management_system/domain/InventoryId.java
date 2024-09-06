package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable // Khai báo là EmbeddedId
public class InventoryId implements Serializable {
    private int stallId;
    private int productId;

    // Constructors

    public InventoryId() {
    }

    public InventoryId(int stallId, int productId) {
        this.stallId = stallId;
        this.productId = productId;
    }

    public int getStallId() {
        return stallId;
    }

    public void setStallId(int stallId) {
        this.stallId = stallId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

}