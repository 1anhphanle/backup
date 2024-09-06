package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.StallService;

import java.util.*;

@Controller
@RequestMapping("/admin/stalls")
public class StallController {
    private final StallService stallService;
    private final ProductService productService;

    public StallController(StallService stallService, ProductService productService) {
        this.stallService = stallService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllStalls(Model model) {
        model.addAttribute("stalls", stallService.getAllStalls());
        return "admin/stall/table-stall";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("stall", new Stall());
        return "admin/stall/create";
    }

    @PostMapping("/create")
    public String createStall(@ModelAttribute("stall") Stall stall) {
        stallService.saveStall(stall);
        return "redirect:/admin/stalls";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Stall> stall = stallService.getStallById(id);
        model.addAttribute("stall", stall.orElse(null));
        return "admin/stall/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStall(@PathVariable("id") int id, @ModelAttribute("stall") Stall stall) {
        stall.setStallId(id);
        stallService.saveStall(stall);
        return "redirect:/admin/stalls";
    }

    @GetMapping("/delete/{id}")
    public String deleteStall(@PathVariable("id") int id) {
        stallService.deleteStall(id);
        return "redirect:/admin/stalls";
    }

    @GetMapping("/{stallId}") // Thêm endpoint để hiển thị sản phẩm của quầy
    public String showStallProducts(@PathVariable("stallId") int stallId, Model model) {
        Optional<Stall> stallOptional = stallService.getStallById(stallId);
        if (stallOptional.isPresent()) {
            Stall stall = stallOptional.get();
            model.addAttribute("stall", stall);

            // Lấy danh sách sản phẩm (giả sử mỗi quầy có 10 sản phẩm mỗi loại)
            List<Product> products = new ArrayList<>();
            List<Product> allProducts = productService.getAllProducts();
            for (Product product : allProducts) {
                if (products.size() < 10) { // Giới hạn 10 sản phẩm mỗi loại
                    products.add(product);
                } else {
                    break;
                }
            }
            model.addAttribute("products", products);
        } else {
            // Xử lý trường hợp không tìm thấy quầy (ví dụ: hiển thị thông báo lỗi)
            model.addAttribute("errorMessage", "Stall not found!");
            return "admin/stall/table-stall";
        }
        return "admin/stall/stall-products"; // Tạo file view mới
    }

}
