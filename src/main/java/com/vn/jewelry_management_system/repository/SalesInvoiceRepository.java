package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.SalesInvoice;

import java.math.BigDecimal;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Integer> {

    @Query("SELECT SUM(si.totalAmount - si.discount) FROM SalesInvoice si WHERE si.stall.stallId = :stallId")
    BigDecimal getTotalRevenueByStallId(@Param("stallId") int stallId);

    @Query("SELECT SUM(si.totalAmount - si.discount) FROM SalesInvoice si WHERE si.employee.employeeId = :employeeId")
    BigDecimal getTotalRevenueByEmployeeId(@Param("employeeId") int employeeId);
}