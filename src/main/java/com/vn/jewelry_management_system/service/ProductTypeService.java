package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.ProductType;
import com.vn.jewelry_management_system.repository.ProductTypeRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeRepository.findAll();
    }

    public Optional<ProductType> getProductTypeById(int id) {
        return productTypeRepository.findById(id);
    }

    public ProductType saveProductType(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public void deleteProductType(int id) {
        productTypeRepository.deleteById(id);
    }

    public Optional<ProductType> findByProductTypeName(String productTypeName) {
        return productTypeRepository.findByProductTypeName(productTypeName);
    }
}