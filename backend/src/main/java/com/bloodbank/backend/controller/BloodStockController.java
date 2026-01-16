package com.bloodbank.backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloodbank.backend.entity.BloodStock;
import com.bloodbank.backend.service.BloodStockService;

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:3000")
public class BloodStockController {

	private final BloodStockService bloodStockService;

	public BloodStockController(BloodStockService bloodStockService) {
		this.bloodStockService = bloodStockService;
	}

	@PostMapping
	public ResponseEntity<BloodStock> createStock(@RequestBody BloodStock stock) {
		BloodStock savedStock = bloodStockService.createStock(stock);
		return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<BloodStock>> getAllStock() {
		return ResponseEntity.ok(bloodStockService.getAllStock());
	}

	@GetMapping("/{bloodGroup}")
	public ResponseEntity<BloodStock> getStockByBloodGroup(@PathVariable String bloodGroup) {

		return ResponseEntity.ok(bloodStockService.getStockByBloodGroup(bloodGroup));
	}

	@PutMapping("/{bloodGroup}")
	public ResponseEntity<BloodStock> updateUnits(@PathVariable String bloodGroup, @RequestParam int units) {

		return ResponseEntity.ok(bloodStockService.updateUnits(bloodGroup, units));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStock(@PathVariable Long id) {
		bloodStockService.deleteStock(id);
		return ResponseEntity.ok("Blood stock deleted successfully");
	}
}
