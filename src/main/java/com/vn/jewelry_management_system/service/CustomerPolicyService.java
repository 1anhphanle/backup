package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.CustomerPolicy;
import com.vn.jewelry_management_system.repository.CustomerPolicyRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerPolicyService {
    private final CustomerPolicyRepository customerPolicyRepository;

    public CustomerPolicyService(CustomerPolicyRepository customerPolicyRepository) {
        this.customerPolicyRepository = customerPolicyRepository;
    }

    public List<CustomerPolicy> getAllCustomerPolicies() {
        return customerPolicyRepository.findAll();
    }

    public Optional<CustomerPolicy> getCustomerPolicyById(int id) {
        return customerPolicyRepository.findById(id);
    }

    public CustomerPolicy saveCustomerPolicy(CustomerPolicy customerPolicy) {
        return customerPolicyRepository.save(customerPolicy);
    }

    public void deleteCustomerPolicy(int id) {
        customerPolicyRepository.deleteById(id);
    }

    public Optional<CustomerPolicy> findByPolicyName(String policyName) {
        return customerPolicyRepository.findByPolicyName(policyName);
    }
}