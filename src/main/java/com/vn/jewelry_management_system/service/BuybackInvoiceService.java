package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.BuybackInvoice;
import com.vn.jewelry_management_system.repository.BuybackInvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuybackInvoiceService {
    private final BuybackInvoiceRepository buybackInvoiceRepository;

    public BuybackInvoiceService(BuybackInvoiceRepository buybackInvoiceRepository) {
        this.buybackInvoiceRepository = buybackInvoiceRepository;
    }

    public List<BuybackInvoice> getAllBuybackInvoices() {
        return buybackInvoiceRepository.findAll();
    }

    public Optional<BuybackInvoice> getBuybackInvoiceById(int id) {
        return buybackInvoiceRepository.findById(id);
    }

    public BuybackInvoice saveBuybackInvoice(BuybackInvoice buybackInvoice) {
        return buybackInvoiceRepository.save(buybackInvoice);
    }

    public void deleteBuybackInvoice(int id) {
        buybackInvoiceRepository.deleteById(id);
    }
}