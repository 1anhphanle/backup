package com.vn.jewelry_management_system.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

import com.itextpdf.text.DocumentException;
import com.vn.jewelry_management_system.domain.BuybackInvoice;
import com.vn.jewelry_management_system.domain.Customer;
import com.vn.jewelry_management_system.domain.Employee;
import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.BuybackInvoiceService;
import com.vn.jewelry_management_system.service.CustomerService;
import com.vn.jewelry_management_system.service.EmployeeService;
import com.vn.jewelry_management_system.service.GoldPriceService;
import com.vn.jewelry_management_system.service.InvoicePdfService;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;
import com.vn.jewelry_management_system.service.StallService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/buyback-invoices")
public class BuybackInvoiceController {
    private final BuybackInvoiceService buybackInvoiceService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final StallService stallService;
    private final SalesInvoiceService salesInvoiceService;
    private final ProductService productService;
    private final InvoicePdfService invoicePdfService;

    public BuybackInvoiceController(BuybackInvoiceService buybackInvoiceService, CustomerService customerService,
            EmployeeService employeeService, StallService stallService, SalesInvoiceService salesInvoiceService,
            GoldPriceService goldPriceService, InvoicePdfService invoicePdfService, ProductService productService) {
        this.buybackInvoiceService = buybackInvoiceService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.stallService = stallService;
        this.salesInvoiceService = salesInvoiceService;
        this.productService = productService;
        this.invoicePdfService = invoicePdfService;
    }

    @GetMapping
    public String showAllBuybackInvoices(Model model) {
        model.addAttribute("buybackInvoices", buybackInvoiceService.getAllBuybackInvoices());
        return "admin/buyback-invoice/table-buyback-invoice";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("buybackInvoice", new BuybackInvoice());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/buyback-invoice/create";
    }

    @PostMapping("/create")
    public String createBuybackInvoice(@ModelAttribute("buybackInvoice") BuybackInvoice buybackInvoice,
            @RequestParam("salesInvoiceId") int salesInvoiceId,
            Model model) {
        // 1. Tìm kiếm hóa đơn bán
        Optional<SalesInvoice> salesInvoiceOptional = salesInvoiceService.getSalesInvoiceById(salesInvoiceId);
        if (salesInvoiceOptional.isEmpty()) {
            model.addAttribute("errorMessage", "Sales invoice not found!");
            return "admin/buyback-invoice/create";
        }
        SalesInvoice salesInvoice = salesInvoiceOptional.get();
        buybackInvoice.setSalesInvoice(salesInvoice);

        // 2. Tính toán giá mua lại (sử dụng calculateBuybackPrice từ ProductService)
        BigDecimal totalBuybackAmount = BigDecimal.ZERO;
        for (SalesInvoiceDetail detail : salesInvoice.getSalesInvoiceDetails()) {
            Product product = detail.getProduct();
            BigDecimal buybackPrice = productService.calculateBuybackPrice(product); // Gọi phương thức từ
                                                                                     // ProductService
            totalBuybackAmount = totalBuybackAmount
                    .add(buybackPrice.multiply(BigDecimal.valueOf(detail.getQuantity())));
        }
        buybackInvoice.setTotalAmount(totalBuybackAmount);

        // 3. Lưu thông tin mua lại
        buybackInvoiceService.saveBuybackInvoice(buybackInvoice);

        // 4. Chuyển hướng đến trang chi tiết hóa đơn mua lại (hoặc danh sách)
        return "redirect:/admin/buyback-invoices";
    }

    @GetMapping("/delete/{id}")
    public String deleteBuybackInvoice(@PathVariable("id") int id) {
        buybackInvoiceService.deleteBuybackInvoice(id);
        return "redirect:/admin/buyback-invoices";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadBuyBackInvoicePdf(@PathVariable("id") int id)
            throws DocumentException, IOException {
        Optional<BuybackInvoice> invoiceOptional = buybackInvoiceService.getBuybackInvoiceById(id);
        if (invoiceOptional.isPresent()) {
            BuybackInvoice invoice = invoiceOptional.get();
            byte[] pdfBytes = invoicePdfService.generateBuybackInvoicePdf(invoice);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment",
                    "buybackInvoice_" + invoice.getBuybackInvoiceId() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}