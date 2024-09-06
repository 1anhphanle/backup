package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.BuybackInvoice;

@Repository
public interface BuybackInvoiceRepository extends JpaRepository<BuybackInvoice, Integer> {
    // Không cần phương thức findBy... vì buybackInvoiceId là khóa chính duy nhất
}