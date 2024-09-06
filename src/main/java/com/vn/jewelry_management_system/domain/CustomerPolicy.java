package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class CustomerPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int policyId;

    private String policyName;
    private String customerType;
    private BigDecimal discountRate;

    // Constructors, Getters and Setters

    public CustomerPolicy() {
    }

    public CustomerPolicy(String policyName, String customerType, BigDecimal discountRate) {
        this.policyName = policyName;
        this.customerType = customerType;
        this.discountRate = discountRate;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String toString() {
        return "CustomerPolicy [policyId=" + policyId + ", policyName=" + policyName + ", customerType=" + customerType
                + ", discountRate=" + discountRate + "]";
    }

}