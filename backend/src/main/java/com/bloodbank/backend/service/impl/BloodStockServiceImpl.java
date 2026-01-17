package com.bloodbank.backend.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bloodbank.backend.entity.BloodStock;
import com.bloodbank.backend.exception.ResourceNotFoundException;
import com.bloodbank.backend.repository.BloodStockRepository;
import com.bloodbank.backend.service.BloodStockService;

@Service
public class BloodStockServiceImpl implements BloodStockService {

	private final BloodStockRepository repository;

	public BloodStockServiceImpl(BloodStockRepository repository) {
		this.repository = repository;
	}

	@Override
	public BloodStock createStock(BloodStock stock) {

		if (stock.getUnitsAvailable() < 0) {
			throw new IllegalArgumentException("Units cannot be negative");
		}

		if (stock.getExpiryDate().isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Cannot add expired blood");
		}

		// normalize blood group
		stock.setBloodGroup(stock.getBloodGroup().trim().toUpperCase());

		return repository.save(stock);
	}

	@Override
	public List<BloodStock> getAllStock() {
		return repository.findAll();
	}

	@Override
	public BloodStock getStockByBloodGroup(String bloodGroup) {

		String normalized = bloodGroup.trim().toUpperCase();

		return repository.findByBloodGroup(normalized)
				.orElseThrow(() -> new ResourceNotFoundException("Blood group not found: " + normalized));
	}

	@Override
	public BloodStock updateUnits(String bloodGroup, int units) {

		if (units < 0) {
			throw new IllegalArgumentException("Units cannot be negative");
		}

		BloodStock stock = getStockByBloodGroup(bloodGroup);
		stock.setUnitsAvailable(units);

		return repository.save(stock);
	}

	@Override
	public void deleteStock(Long id) {

		if (!repository.existsById(id)) {
			throw new ResourceNotFoundException("Stock not found with id: " + id);
		}

		repository.deleteById(id);
	}
}
