package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetail;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetailId;

@Repository
public interface BuybackInvoiceDetailRepository extends JpaRepository<BuybackInvoiceDetail, BuybackInvoiceDetailId> {
    // Không cần phương thức findBy... vì BuybackInvoiceDetailId đã là khóa chính
}