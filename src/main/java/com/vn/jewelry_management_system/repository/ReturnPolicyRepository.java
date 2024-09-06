package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.ReturnPolicy;

@Repository
public interface ReturnPolicyRepository extends JpaRepository<ReturnPolicy, Integer> {
    // Không cần phương thức findByCdt vì cdt không phải là trường duy nhất
}