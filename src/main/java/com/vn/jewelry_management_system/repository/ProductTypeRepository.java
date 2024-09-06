package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.ProductType;
import java.util.Optional;

@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Integer> {
    Optional<ProductType> findByProductTypeName(String productTypeName);
}
