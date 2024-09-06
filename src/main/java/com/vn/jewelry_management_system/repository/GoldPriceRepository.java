package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;
import com.vn.jewelry_management_system.domain.GoldPrice;

@Repository
public interface GoldPriceRepository extends JpaRepository<GoldPrice, Integer> {
    Optional<GoldPrice> findByUpdateDate(Date updateDate);

    // Lấy giá mua mới nhất
    @Query("SELECT gp.buyingPrice FROM GoldPrice gp WHERE gp.goldPriceId = (SELECT MAX(g.goldPriceId) FROM GoldPrice g)")
    Optional<BigDecimal> findLatestBuyingPrice();

    // Lấy giá bán mới nhất
    @Query("SELECT gp.sellingPrice FROM GoldPrice gp WHERE gp.goldPriceId = (SELECT MAX(g.goldPriceId) FROM GoldPrice g)")
    Optional<BigDecimal> findLatestSellingPrice();
}