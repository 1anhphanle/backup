package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.BuybackInvoiceService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;
import com.vn.jewelry_management_system.service.StallService;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class DashboardController {

    private final SalesInvoiceService salesInvoiceService;
    private final StallService stallService;
    private final BuybackInvoiceService buybackInvoiceService;

    public DashboardController(SalesInvoiceService salesInvoiceService, StallService stallService,
            BuybackInvoiceService buybackInvoiceService) {
        this.salesInvoiceService = salesInvoiceService;
        this.stallService = stallService;
        this.buybackInvoiceService = buybackInvoiceService;
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        BigDecimal totalRevenue = salesInvoiceService.getTotalRevenue();
        long totalSalesInvoices = salesInvoiceService.countSalesInvoices();
        long totalBuybackInvoices = buybackInvoiceService.countBuybackInvoices();
        Map<Stall, BigDecimal> revenueByStall = salesInvoiceService.getRevenueByStall();

        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("totalSalesInvoices", totalSalesInvoices);
        model.addAttribute("totalBuybackInvoices", totalBuybackInvoices);
        model.addAttribute("revenueByStall", revenueByStall);

        return "admin/dashboard";
    }
}