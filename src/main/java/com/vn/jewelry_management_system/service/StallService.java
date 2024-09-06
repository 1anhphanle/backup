package com.vn.jewelry_management_system.service;

import org.springframework.stereotype.Service;
import com.vn.jewelry_management_system.domain.Stall;
import com.vn.jewelry_management_system.repository.StallRepository;
import java.util.List;
import java.util.Optional;

@Service
public class StallService {

    private final StallRepository stallRepository;

    public StallService(StallRepository stallRepository) {
        this.stallRepository = stallRepository;
    }

    public List<Stall> getAllStalls() {
        return stallRepository.findAll();
    }

    public Optional<Stall> getStallById(int id) {
        return stallRepository.findById(id);
    }

    public Stall saveStall(Stall stall) {
        return stallRepository.save(stall);
    }

    public void deleteStall(int id) {
        stallRepository.deleteById(id);
    }

    public Optional<Stall> findByStallName(String stallName) {
        return stallRepository.findByStallName(stallName);
    }
}
