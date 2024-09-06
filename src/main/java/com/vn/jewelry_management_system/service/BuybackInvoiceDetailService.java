package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetail;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetailId;
import com.vn.jewelry_management_system.repository.BuybackInvoiceDetailRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BuybackInvoiceDetailService {
    private final BuybackInvoiceDetailRepository buybackInvoiceDetailRepository;

    public BuybackInvoiceDetailService(BuybackInvoiceDetailRepository buybackInvoiceDetailRepository) {
        this.buybackInvoiceDetailRepository = buybackInvoiceDetailRepository;
    }

    public List<BuybackInvoiceDetail> getAllBuybackInvoiceDetails() {
        return buybackInvoiceDetailRepository.findAll();
    }

    public Optional<BuybackInvoiceDetail> getBuybackInvoiceDetailById(BuybackInvoiceDetailId id) {
        return buybackInvoiceDetailRepository.findById(id);
    }

    public BuybackInvoiceDetail saveBuybackInvoiceDetail(BuybackInvoiceDetail buybackInvoiceDetail) {
        return buybackInvoiceDetailRepository.save(buybackInvoiceDetail);
    }

    public void deleteBuybackInvoiceDetail(BuybackInvoiceDetailId id) {
        buybackInvoiceDetailRepository.deleteById(id);
    }
}