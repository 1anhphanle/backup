package com.vn.jewelry_management_system.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.DocumentException;
import com.vn.jewelry_management_system.domain.Customer;
import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.Promotion;
import com.vn.jewelry_management_system.domain.SalesInvoice;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetail;
import com.vn.jewelry_management_system.domain.SalesInvoiceDetailId;
import com.vn.jewelry_management_system.service.CustomerService;
import com.vn.jewelry_management_system.service.EmployeeService;
import com.vn.jewelry_management_system.service.InvoicePdfService;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.PromotionService;
import com.vn.jewelry_management_system.service.SalesInvoiceDetailService;
import com.vn.jewelry_management_system.service.SalesInvoiceService;
import com.vn.jewelry_management_system.service.StallService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/sales-invoices")
public class SalesInvoiceController {
    private final SalesInvoiceService salesInvoiceService;
    private final CustomerService customerService;
    private final EmployeeService employeeService;
    private final StallService stallService;
    private final ProductService productService; // Inject ProductService
    private final SalesInvoiceDetailService salesInvoiceDetailService; // Inject SalesInvoiceDetailService
    private final InvoicePdfService invoicePdfService; // Inject InvoicePdfService
    private final PromotionService promotionService; // Inject PromotionService

    public SalesInvoiceController(SalesInvoiceService salesInvoiceService, CustomerService customerService,
            EmployeeService employeeService, StallService stallService, ProductService productService,
            SalesInvoiceDetailService salesInvoiceDetailService, InvoicePdfService invoicePdfService,
            PromotionService promotionService) {
        this.salesInvoiceService = salesInvoiceService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.stallService = stallService;
        this.productService = productService;
        this.salesInvoiceDetailService = salesInvoiceDetailService;
        this.invoicePdfService = invoicePdfService;
        this.promotionService = promotionService;

    }

    // Phương thức tính tổng chiết khấu
    private BigDecimal calculateTotalDiscount(SalesInvoice salesInvoice) {
        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalAmount = salesInvoice.getTotalAmount();

        for (Promotion promotion : salesInvoice.getPromotions()) {
            if (promotion.getPromotionType() == Promotion.PromotionType.PERCENTAGE) {
                // Giảm giá theo phần trăm
                totalDiscount = totalDiscount
                        .add(totalAmount.multiply(promotion.getValue().divide(BigDecimal.valueOf(100))));
            } else {
                // Giảm giá theo số tiền
                totalDiscount = totalDiscount.add(promotion.getValue());
            }
        }
        return totalDiscount;
    }

    @GetMapping
    public String showAllSalesInvoices(Model model) {
        model.addAttribute("salesInvoices", salesInvoiceService.getAllSalesInvoices());
        return "admin/sales-invoice/table-sales-invoice";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("salesInvoice", new SalesInvoice());
        model.addAttribute("customers", customerService.getAllCustomers());
        model.addAttribute("employees", employeeService.getAllEmployees());
        model.addAttribute("stalls", stallService.getAllStalls());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("salesInvoiceDetails", new ArrayList<SalesInvoiceDetail>());
        model.addAttribute("promotions", promotionService.getAllPromotions());
        return "admin/sales-invoice/create";
    }

    @PostMapping("/create")
    public String createSalesInvoice(@ModelAttribute("salesInvoice") SalesInvoice salesInvoice,
            @RequestParam("productIds") List<Integer> productIds,
            @RequestParam("quantities") List<Integer> quantities,
            @RequestParam("promotionIds") List<Integer> promotionIds, // Nhận danh sách promotionIds
            Model model) {
        // Xử lý chi tiết đơn hàng
        List<SalesInvoiceDetail> details = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (int i = 0; i < productIds.size(); i++) {
            int productId = productIds.get(i);
            int quantity = quantities.get(i);

            // Bỏ qua nếu quantity = 0
            if (quantity == 0) {
                continue;
            }

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
                        product.getProductId());
                detail.setId(detailId);

                // Tính giá bán
                BigDecimal sellingPrice = productService.calculateSellingPrice(product);
                detail.setUnitPrice(sellingPrice);

                details.add(detail);

                // // Cộng dồn totalAmount
                // totalAmount =
                // totalAmount.add(sellingPrice.multiply(BigDecimal.valueOf(quantity)));
            } else {
                // Xử lý trường hợp không tìm thấy sản phẩm (ví dụ: hiển thị thông báo lỗi)
                model.addAttribute("errorMessage", "Product not found!");
                return "admin/sales-invoice/create";
            }
        }
        // Xử lý promotionIds
        List<Promotion> promotions = new ArrayList<>();
        for (Integer promotionId : promotionIds) {
            Optional<Promotion> promotionOptional = promotionService.getPromotionById(promotionId);
            promotionOptional.ifPresent(promotions::add);
        }
        salesInvoice.setPromotions(promotions);

        // // Tính toán tổng chiết khấu
        // BigDecimal totalDiscount = calculateTotalDiscount(salesInvoice);
        // salesInvoice.setDiscount(totalDiscount);

        // Lưu SalesInvoice trước để có salesInvoiceId
        salesInvoice = salesInvoiceService.saveSalesInvoice(salesInvoice);

        // Lưu SalesInvoiceDetails
        for (SalesInvoiceDetail detail : details) {
            detail.setSalesInvoice(salesInvoice); // Cập nhật lại salesInvoice (đã có ID)
            salesInvoiceDetailService.saveSalesInvoiceDetail(detail);
        }

        // Tính toán lại totalAmount sau khi đã lưu SalesInvoiceDetails
        totalAmount = salesInvoiceDetailService.getTotalAmountByInvoiceId(salesInvoice.getSalesInvoiceId());
        salesInvoice.setTotalAmount(totalAmount);

        // Tính toán tổng chiết khấu
        BigDecimal totalDiscount = calculateTotalDiscount(salesInvoice);
        salesInvoice.setDiscount(totalDiscount);

        // Lưu lại SalesInvoice (đã có totalAmount và discount)
        salesInvoiceService.saveSalesInvoice(salesInvoice);

        // Cập nhật LoyaltyPoint cho khách hàng
        Customer customer = salesInvoice.getCustomer();
        int newLoyaltyPoints = (int) (salesInvoice.getTotalAmount().subtract(salesInvoice.getDiscount()).intValue()
                / 10000);
        customer.setLoyaltyPoint(customer.getLoyaltyPoint() + newLoyaltyPoints);
        customerService.saveCustomer(customer);

        return "redirect:/admin/sales-invoices";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadInvoicePdf(@PathVariable("id") int id) throws DocumentException {
        Optional<SalesInvoice> invoiceOptional = salesInvoiceService.getSalesInvoiceById(id);
        if (invoiceOptional.isPresent()) {
            SalesInvoice invoice = invoiceOptional.get();
            byte[] pdfBytes = invoicePdfService.generateInvoicePdf(invoice);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "invoice_" + invoice.getSalesInvoiceId() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/download-warranty/{id}")
    public ResponseEntity<byte[]> downloadWarrantyPdf(@PathVariable("id") int id) throws DocumentException {
        Optional<SalesInvoice> invoiceOptional = salesInvoiceService.getSalesInvoiceById(id);
        if (invoiceOptional.isPresent()) {
            SalesInvoice invoice = invoiceOptional.get();
            byte[] pdfBytes = invoicePdfService.generateWarrantyPdf(invoice);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "warranty_" + invoice.getSalesInvoiceId() + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}