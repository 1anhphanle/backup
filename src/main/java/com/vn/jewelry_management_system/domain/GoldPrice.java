// GoldPrice.java
package com.vn.jewelry_management_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class GoldPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int goldPriceId;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateDate;

    private String goldName;
    private BigDecimal buyingPrice;
    private BigDecimal sellingPrice;

    public int getGoldPriceId() {
        return goldPriceId;
    }

    public void setGoldPriceId(int goldPriceId) {
        this.goldPriceId = goldPriceId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getGoldName() {
        return goldName;
    }

    public void setGoldName(String goldName) {
        this.goldName = goldName;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(BigDecimal sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}