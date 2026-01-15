package com.bloodbank.backend.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodbank.backend.entity.BloodStock;
import com.bloodbank.backend.repository.BloodStockRepository;
import com.bloodbank.backend.service.BloodStockService;

@Service
public class BloodStockServiceImpl implements BloodStockService {

    private final BloodStockRepository bloodStockRepository;

    public BloodStockServiceImpl(BloodStockRepository bloodStockRepository) {
        this.bloodStockRepository = bloodStockRepository;
    }

    @Override
    public BloodStock createStock(BloodStock stock) {

        if (stock.getUnitsAvailable() < 0) {
            throw new IllegalArgumentException("Units cannot be negative");
        }

        if (stock.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Cannot add expired blood");
        }

        // createdAt & updatedAt handled by @PrePersist
        return bloodStockRepository.save(stock);
    }

    @Override
    public List<BloodStock> getAllStock() {
        return bloodStockRepository.findAll();
    }

    @Override
    public BloodStock getStockByBloodGroup(String bloodGroup) {
        return bloodStockRepository.findByBloodGroup(bloodGroup)
                .orElseThrow(() -> new RuntimeException("Blood group not found"));
    }

    @Override
    public BloodStock updateUnits(String bloodGroup, int units) {

        if (units < 0) {
            throw new IllegalArgumentException("Units cannot be negative");
        }

        BloodStock stock = getStockByBloodGroup(bloodGroup);
        stock.setUnitsAvailable(units);

        // updatedAt handled by @PreUpdate
        return bloodStockRepository.save(stock);
    }

    @Override
    public void deleteStock(Long id) {
        bloodStockRepository.deleteById(id);
    }
}
