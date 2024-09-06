package com.vn.jewelry_management_system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.vn.jewelry_management_system.domain.Inventory;
import com.vn.jewelry_management_system.domain.InventoryId;
import com.vn.jewelry_management_system.service.InventoryService;
import com.vn.jewelry_management_system.service.ProductService;
import com.vn.jewelry_management_system.service.StallService;

import java.util.Optional;

@Controller
@RequestMapping("/admin/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    private final StallService stallService;
    private final ProductService productService;

    public InventoryController(InventoryService inventoryService, StallService stallService,
            ProductService productService) {
        this.inventoryService = inventoryService;
        this.stallService = stallService;
        this.productService = productService;
    }

    @GetMapping
    public String showAllInventories(Model model) {
        model.addAttribute("inventories", inventoryService.getAllInventories());
        model.addAttribute("stalls", stallService.getAllStalls());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/inventory/table-inventory";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("inventory", new Inventory());
        model.addAttribute("stalls", stallService.getAllStalls());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/inventory/create";
    }

    @PostMapping("/create")
    public String createInventory(@ModelAttribute("inventory") Inventory inventory) {
        // Lấy stallId và productId từ form
        int stallId = inventory.getStall().getStallId();
        int productId = inventory.getProduct().getProductId();

        // Tạo InventoryId
        InventoryId inventoryId = new InventoryId(stallId, productId);

        // Kiểm tra xem InventoryId đã tồn tại chưa
        Optional<Inventory> existingInventory = inventoryService.getInventoryById(inventoryId);
        if (existingInventory.isPresent()) {
            // Nếu InventoryId đã tồn tại, cập nhật quantity
            existingInventory.get().setQuantity(inventory.getQuantity());
            inventoryService.saveInventory(existingInventory.get());
        } else {
            // Nếu InventoryId chưa tồn tại, tạo mới
            inventory.setId(inventoryId);
            inventoryService.saveInventory(inventory);
        }

        return "redirect:/admin/inventories";
    }

    @GetMapping("/edit/{stallId}/{productId}") // Sử dụng ID composite cho edit
    public String showEditForm(@PathVariable("stallId") int stallId, @PathVariable("productId") int productId,
            Model model) {
        InventoryId inventoryId = new InventoryId(stallId, productId);
        Optional<Inventory> inventory = inventoryService.getInventoryById(inventoryId);
        model.addAttribute("inventory", inventory.orElse(null));
        model.addAttribute("stalls", stallService.getAllStalls());
        model.addAttribute("products", productService.getAllProducts());
        return "admin/inventory/edit";
    }

    @PostMapping("/edit/{stallId}/{productId}")
    public String updateInventory(@PathVariable("stallId") int stallId, @PathVariable("productId") int productId,
            @ModelAttribute("inventory") Inventory inventory) {
        // Tạo InventoryId
        InventoryId inventoryId = new InventoryId(stallId, productId);
        // Set InventoryId cho inventory
        inventory.setId(inventoryId);

        inventoryService.saveInventory(inventory);
        return "redirect:/admin/inventories";
    }

    @GetMapping("/delete/{stallId}/{productId}") // Sử dụng ID composite cho delete
    public String deleteInventory(@PathVariable("stallId") int stallId, @PathVariable("productId") int productId) {
        InventoryId inventoryId = new InventoryId(stallId, productId);
        inventoryService.deleteInventory(inventoryId);
        return "redirect:/admin/inventories";
    }
}