package com.vn.jewelry_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.vn.jewelry_management_system.domain.CustomerPolicy;
import java.util.Optional;

@Repository
public interface CustomerPolicyRepository extends JpaRepository<CustomerPolicy, Integer> {
    Optional<CustomerPolicy> findByPolicyName(String policyName);
}