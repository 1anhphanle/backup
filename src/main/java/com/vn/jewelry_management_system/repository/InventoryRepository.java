package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.Inventory;
import com.vn.jewelry_management_system.domain.InventoryId;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, InventoryId> {
    // Không cần phương thức findByStallIdAndProductId vì InventoryId đã là khóa
    // chính
}