package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetailId;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.SalesInvoiceDetailService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("salesInvoiceDetail", new SalesInvoiceDetail());
        model.addAttribute("salesInvoices", salesInvoiceService.getAllSalesInvoices());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/sales-invoice-detail/create";
    }

    @PostMapping("/create")
    public String createSalesInvoice(@ModelAttribute("salesInvoice") SalesInvoice salesInvoice,
            @RequestParam("productId") int productId,
            @RequestParam("quantity") int quantity,
            Model model) {
        // Tìm kiếm sản phẩm từ database dựa trên productId
        Optional<Product> productOptional = productService.getProductById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();

            // Tạo SalesInvoiceDetail
            SalesInvoiceDetail detail = new SalesInvoiceDetail();
            detail.setProduct(product);
            detail.setQuantity(quantity);
            detail.setSalesInvoice(salesInvoice);

            // Tạo SalesInvoiceDetailId và set cho detail
            SalesInvoiceDetailId detailId = new SalesInvoiceDetailId(salesInvoice.getSalesInvoiceId(),
                    product.getProductId()); // Giả sử salesInvoice.getSalesInvoiceId() đã có giá trị
            detail.setId(detailId);

            // Tính giá bán
            BigDecimal sellingPrice = productService.calculateSellingPrice(product);
            detail.setUnitPrice(sellingPrice);

            // Thêm detail vào danh sách chi tiết của salesInvoice
            List<SalesInvoiceDetail> details = new ArrayList<>();
            details.add(detail);
            salesInvoice.setSalesInvoiceDetails(details);

            // Tính toán tổng tiền của hóa đơn
            BigDecimal totalAmount = sellingPrice.multiply(BigDecimal.valueOf(quantity));
            salesInvoice.setTotalAmount(totalAmount);

            salesInvoiceService.saveSalesInvoice(salesInvoice);
        } else {
            // Xử lý trường hợp không tìm thấy sản phẩm (ví dụ: hiển thị thông báo lỗi)
            model.addAttribute("errorMessage", "Product not found!");
            return "admin/sales-invoice/create";
        }

        return "redirect:/admin/sales-invoices";
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