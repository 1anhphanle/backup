package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetailId;
import java.util.List;

@Repository
public interface SalesInvoiceDetailRepository extends JpaRepository<SalesInvoiceDetail, SalesInvoiceDetailId> {
    // SalesInvoiceDetailRepository.java
    List<SalesInvoiceDetail> findAllById_SalesInvoiceId(int salesInvoiceId);
}