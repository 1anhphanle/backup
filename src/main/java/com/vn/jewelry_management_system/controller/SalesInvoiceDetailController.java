package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.SalesInvoiceDetailService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;

import java.util.List;

@Controller
@RequestMapping("/admin/sales-invoice-details")
public class SalesInvoiceDetailController {
    private final SalesInvoiceDetailService salesInvoiceDetailService;
    private final SalesInvoiceService salesInvoiceService;
    private final ProductService productService;

    public SalesInvoiceDetailController(SalesInvoiceDetailService salesInvoiceDetailService,
            SalesInvoiceService salesInvoiceService, ProductService productService) {
        this.salesInvoiceDetailService = salesInvoiceDetailService;
        this.salesInvoiceService = salesInvoiceService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllSalesInvoiceDetails(Model model) {
        model.addAttribute("salesInvoiceDetails", salesInvoiceDetailService.getAllSalesInvoiceDetails());
        model.addAttribute("salesInvoices", salesInvoiceService.getAllSalesInvoices());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/sales-invoice-detail/table-sales-invoice-detail";
    }

    @GetMapping("/invoice/{salesInvoiceId}")
    public String showInvoiceDetails(@PathVariable("salesInvoiceId") int salesInvoiceId, Model model) {
        // Lấy danh sách chi tiết hóa đơn từ service
        List<SalesInvoiceDetail> details = salesInvoiceDetailService.getDetailsBySalesInvoiceId(salesInvoiceId);

        // Thêm danh sách chi tiết vào model
        model.addAttribute("salesInvoiceDetails", details);

        // Trả về view hiển thị chi tiết hóa đơn
        return "admin/sales-invoice-detail/invoice-details";
    }
}