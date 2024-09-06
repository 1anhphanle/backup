package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.repository.SalesInvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalesInvoiceService {
    private final SalesInvoiceRepository salesInvoiceRepository;

    public SalesInvoiceService(SalesInvoiceRepository salesInvoiceRepository) {
        this.salesInvoiceRepository = salesInvoiceRepository;
    }

    public List<SalesInvoice> getAllSalesInvoices() {
        return salesInvoiceRepository.findAll();
    }

    public Optional<SalesInvoice> getSalesInvoiceById(int id) {
        return salesInvoiceRepository.findById(id);
    }

    public SalesInvoice saveSalesInvoice(SalesInvoice salesInvoice) {
        return salesInvoiceRepository.save(salesInvoice);
    }

    public void deleteSalesInvoice(int id) {
        salesInvoiceRepository.deleteById(id);
    }
}