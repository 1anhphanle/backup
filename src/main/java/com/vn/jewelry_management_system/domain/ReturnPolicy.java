package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class ReturnPolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int returnPolicyId;

    @Lob // Sử dụng @Lob để lưu trữ text dài
    private String cdt; // Đã đổi tên từ Condition thành Cdt
    private int duration;

    // Constructors, Getters and Setters

    public ReturnPolicy() {
    }

    public ReturnPolicy(String cdt, int duration) {
        this.cdt = cdt;
        this.duration = duration;
    }

    public int getReturnPolicyId() {
        return returnPolicyId;
    }

    public void setReturnPolicyId(int returnPolicyId) {
        this.returnPolicyId = returnPolicyId;
    }

    public String getCdt() {
        return cdt;
    }

    public void setCdt(String cdt) {
        this.cdt = cdt;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "ReturnPolicy [returnPolicyId=" + returnPolicyId + ", cdt=" + cdt + ", duration=" + duration + "]";
    }

}