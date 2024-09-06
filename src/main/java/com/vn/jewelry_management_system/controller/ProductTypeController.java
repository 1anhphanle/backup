package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.ProductType;
import com.vn.jewelry_management_system.service.ProductTypeService;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product-types")
public class ProductTypeController {
    private final ProductTypeService productTypeService;

    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public String showAllProductTypes(Model model) {
        model.addAttribute("productTypes", productTypeService.getAllProductTypes());
        return "admin/product-type/table-product-type";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("productType", new ProductType());
        return "admin/product-type/create";
    }

    @PostMapping("/create")
    public String createProductType(@ModelAttribute("productType") ProductType productType) {
        productTypeService.saveProductType(productType);
        return "redirect:/admin/product-types";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<ProductType> productType = productTypeService.getProductTypeById(id);
        model.addAttribute("productType", productType.orElse(null));
        return "admin/product-type/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProductType(@PathVariable("id") int id,
            @ModelAttribute("productType") ProductType productType) {
        productType.setProductTypeId(id);
        productTypeService.saveProductType(productType);
        return "redirect:/admin/product-types";
    }

    @GetMapping("/delete/{id}")
    public String deleteProductType(@PathVariable("id") int id) {
        productTypeService.deleteProductType(id);
        return "redirect:/admin/product-types";
    }
}