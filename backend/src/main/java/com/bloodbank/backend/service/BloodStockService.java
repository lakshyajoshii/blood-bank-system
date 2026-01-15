package com.bloodbank.backend.service;

import java.util.List;

import com.bloodbank.backend.entity.BloodStock;

public interface BloodStockService {

	BloodStock createStock(BloodStock stock);

	List<BloodStock> getAllStock();

	BloodStock getStockByBloodGroup(String bloodGroup);

	BloodStock updateUnits(String bloodGroup, int units);

	void deleteStock(Long id);
}
