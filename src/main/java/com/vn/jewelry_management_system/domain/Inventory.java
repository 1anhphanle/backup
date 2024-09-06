package com.vn.jewelry_management_system.domain;

import jakarta.persistence.*;

@Entity
public class Inventory {
    @EmbeddedId // Sử dụng EmbeddedId cho khóa chính composite
    private InventoryId id;

    private int quantity;

    @ManyToOne
    @MapsId("stallId") // Mapping đến stallId trong InventoryId
    private Stall stall;

    @ManyToOne
    @MapsId("productId") // Mapping đến productId trong InventoryId
    private Product product;

    // Constructors, Getters and Setters

    public Inventory() {
    }

    public Inventory(Stall stall, Product product, int quantity) {
        this.id = new InventoryId(stall.getStallId(), product.getProductId());
        this.stall = stall;
        this.product = product;
        this.quantity = quantity;
    }

    public InventoryId getId() {
        return id;
    }

    public void setId(InventoryId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}