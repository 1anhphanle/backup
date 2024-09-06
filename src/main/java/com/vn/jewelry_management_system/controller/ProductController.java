package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.domain.ProductType;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.ProductTypeService;
import java.util.Optional;

import java.math.BigDecimal;

@Controller
@RequestMapping("/admin/products")
public class ProductController {
    private final ProductService productService;
    private final ProductTypeService productTypeService;

    public ProductController(ProductService productService, ProductTypeService productTypeService) {
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    @GetMapping
    public String showAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/table-product";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes", productTypeService.getAllProductTypes());
        return "admin/product/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute("product") Product product) {
        // Lấy productTypeId từ product object
        int productTypeId = product.getProductType().getProductTypeId();

        // Tìm ProductType object từ productTypeId
        Optional<ProductType> productType = productTypeService.getProductTypeById(productTypeId);

        // Set ProductType object cho product nếu tìm thấy
        productType.ifPresent(product::setProductType);

        // Tính toán sellingPrice
        BigDecimal sellingPrice = productService.calculateSellingPrice(product);
        product.setSellingPrice(sellingPrice); // Set giá trị sellingPrice cho product

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Optional<Product> product = productService.getProductById(id);
        model.addAttribute("product", product.orElse(null));
        model.addAttribute("productTypes", productTypeService.getAllProductTypes());
        return "admin/product/edit";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable("id") int id, @ModelAttribute("product") Product product) {
        product.setProductId(id);
        // Tương tự như createProduct, bạn cũng cần xử lý productType trong
        // updateProduct
        int productTypeId = product.getProductType().getProductTypeId();
        Optional<ProductType> productType = productTypeService.getProductTypeById(productTypeId);
        productType.ifPresent(product::setProductType);

        // Tính toán sellingPrice
        BigDecimal sellingPrice = productService.calculateSellingPrice(product);
        product.setSellingPrice(sellingPrice); // Set giá trị sellingPrice cho product

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/price/{productId}")
    @ResponseBody
    public BigDecimal getProductPrice(@PathVariable("productId") int productId) {
        Optional<Product> product = productService.getProductById(productId);
        if (product.isPresent()) {
            return productService.calculateSellingPrice(product.get());
        } else {
            // Xử lý trường hợp không tìm thấy sản phẩm
            return BigDecimal.ZERO; // Hoặc throw exception
        }
    }
}