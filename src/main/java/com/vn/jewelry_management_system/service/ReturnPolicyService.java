package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;

import com.vn.jewelry_management_system.domain.ReturnPolicy;
import com.vn.jewelry_management_system.repository.ReturnPolicyRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReturnPolicyService {
    private final ReturnPolicyRepository returnPolicyRepository;

    public ReturnPolicyService(ReturnPolicyRepository returnPolicyRepository) {
        this.returnPolicyRepository = returnPolicyRepository;
    }

    public List<ReturnPolicy> getAllReturnPolicies() {
        return returnPolicyRepository.findAll();
    }

    public Optional<ReturnPolicy> getReturnPolicyById(int id) {
        return returnPolicyRepository.findById(id);
    }

    public ReturnPolicy saveReturnPolicy(ReturnPolicy returnPolicy) {
        return returnPolicyRepository.save(returnPolicy);
    }

    public void deleteReturnPolicy(int id) {
        returnPolicyRepository.deleteById(id);
    }
}