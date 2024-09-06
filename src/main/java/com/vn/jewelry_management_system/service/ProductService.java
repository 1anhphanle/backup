package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;

import com.vn.jewelry_management_system.domain.Product;
import com.vn.jewelry_management_system.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final GoldPriceService goldPriceService; // Inject GoldPriceService

    public ProductService(ProductRepository productRepository, GoldPriceService goldPriceService) {
        this.productRepository = productRepository;
        this.goldPriceService = goldPriceService;
    }

    public BigDecimal calculateSellingPrice(Product product) {
        // Lấy giá bán vàng hiện tại
        BigDecimal goldSellingPrice = goldPriceService.getLatestSellingPrice();

        // Tính giá vốn sản phẩm
        BigDecimal costPrice = goldSellingPrice.multiply(product.getWeight())
                .add(product.getMakingFee())
                .add(product.getGemstonePrice());

        // Tính giá bán
        BigDecimal sellingPrice = costPrice.multiply(product.getMarkupRatio().add(BigDecimal.ONE));

        return sellingPrice;
    }

    public BigDecimal calculateBuybackPrice(Product product) {
        // Lấy giá mua vàng hiện tại
        BigDecimal goldBuyingPrice = goldPriceService.getLatestBuyingPrice();

        // Tính giá vốn sản phẩm
        BigDecimal costPrice = goldBuyingPrice.multiply(product.getWeight())
                .add(product.getMakingFee())
                .add(product.getGemstonePrice());

        // Tính giá mua lại (90% giá vốn)
        BigDecimal buybackPrice = costPrice.multiply(new BigDecimal("0.9"));

        return buybackPrice;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }
}