package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.Promotion;
import com.vn.jewelry_management_system.repository.PromotionRepository;
import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionService(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Optional<Promotion> getPromotionById(int id) {
        return promotionRepository.findById(id);
    }

    public Promotion savePromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    public void deletePromotion(int id) {
        promotionRepository.deleteById(id);
    }

    public Optional<Promotion> findByPromotionName(String promotionName) {
        return promotionRepository.findByPromotionName(promotionName);
    }
}