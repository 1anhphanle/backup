package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetail;
import com.vn.jewelry_management_system.domain.BuybackInvoiceDetailId;
import com.vn.jewelry_management_system.service.BuybackInvoiceDetailService;
import com.vn.jewelry_management_system.service.BuybackInvoiceService;
import com.vn.jewelry_management_system.service.ProductService;
import java.util.Optional;

@Controller
@RequestMapping("/admin/buyback-invoice-details")
public class BuybackInvoiceDetailController {
    private final BuybackInvoiceDetailService buybackInvoiceDetailService;
    private final BuybackInvoiceService buybackInvoiceService;
    private final ProductService productService;

    public BuybackInvoiceDetailController(BuybackInvoiceDetailService buybackInvoiceDetailService,
            BuybackInvoiceService buybackInvoiceService, ProductService productService) {
        this.buybackInvoiceDetailService = buybackInvoiceDetailService;
        this.buybackInvoiceService = buybackInvoiceService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllBuybackInvoiceDetails(Model model) {
        model.addAttribute("buybackInvoiceDetails", buybackInvoiceDetailService.getAllBuybackInvoiceDetails());
        model.addAttribute("buybackInvoices", buybackInvoiceService.getAllBuybackInvoices());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/buyback-invoice-detail/table-buyback-invoice-detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("buybackInvoiceDetail", new BuybackInvoiceDetail());
        model.addAttribute("buybackInvoices", buybackInvoiceService.getAllBuybackInvoices());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/buyback-invoice-detail/create";
    }

    @PostMapping("/create")
    public String createBuybackInvoiceDetail(
            @ModelAttribute("buybackInvoiceDetail") BuybackInvoiceDetail buybackInvoiceDetail) {
        // Lấy buybackInvoiceId và productId từ form
        int buybackInvoiceId = buybackInvoiceDetail.getBuybackInvoice().getBuybackInvoiceId();
        int productId = buybackInvoiceDetail.getProduct().getProductId();

        // Tạo BuybackInvoiceDetailId
        BuybackInvoiceDetailId buybackInvoiceDetailId = new BuybackInvoiceDetailId(buybackInvoiceId, productId);

        // Set BuybackInvoiceDetailId cho buybackInvoiceDetail
        buybackInvoiceDetail.setId(buybackInvoiceDetailId);

        buybackInvoiceDetailService.saveBuybackInvoiceDetail(buybackInvoiceDetail);
        return "redirect:/admin/buyback-invoice-details";
    }

    @GetMapping("/edit/{buybackInvoiceId}/{productId}")
    public String showEditForm(@PathVariable("buybackInvoiceId") int buybackInvoiceId,
            @PathVariable("productId") int productId, Model model) {
        BuybackInvoiceDetailId buybackInvoiceDetailId = new BuybackInvoiceDetailId(buybackInvoiceId, productId);
        Optional<BuybackInvoiceDetail> buybackInvoiceDetail = buybackInvoiceDetailService
                .getBuybackInvoiceDetailById(buybackInvoiceDetailId);
        model.addAttribute("buybackInvoiceDetail", buybackInvoiceDetail.orElse(null));
        model.addAttribute("buybackInvoices", buybackInvoiceService.getAllBuybackInvoices());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/buyback-invoice-detail/edit";
    }

    @PostMapping("/edit/{buybackInvoiceId}/{productId}")
    public String updateBuybackInvoiceDetail(@PathVariable("buybackInvoiceId") int buybackInvoiceId,
            @PathVariable("productId") int productId,
            @ModelAttribute("buybackInvoiceDetail") BuybackInvoiceDetail buybackInvoiceDetail) {
        // Tạo BuybackInvoiceDetailId
        BuybackInvoiceDetailId buybackInvoiceDetailId = new BuybackInvoiceDetailId(buybackInvoiceId, productId);
        // Set BuybackInvoiceDetailId cho buybackInvoiceDetail
        buybackInvoiceDetail.setId(buybackInvoiceDetailId);

        buybackInvoiceDetailService.saveBuybackInvoiceDetail(buybackInvoiceDetail);
        return "redirect:/admin/buyback-invoice-details";
    }

    @GetMapping("/delete/{buybackInvoiceId}/{productId}")
    public String deleteBuybackInvoiceDetail(@PathVariable("buybackInvoiceId") int buybackInvoiceId,
            @PathVariable("productId") int productId) {
        BuybackInvoiceDetailId buybackInvoiceDetailId = new BuybackInvoiceDetailId(buybackInvoiceId, productId);
        buybackInvoiceDetailService.deleteBuybackInvoiceDetail(buybackInvoiceDetailId);
        return "redirect:/admin/buyback-invoice-details";
    }
}