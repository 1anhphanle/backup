package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.SalesInvoice;

@Repository
public interface SalesInvoiceRepository extends JpaRepository<SalesInvoice, Integer> {
    // Không cần phương thức findBy... vì salesInvoiceId là khóa chính duy nhất
}