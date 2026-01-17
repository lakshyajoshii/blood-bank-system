package com.bloodbank.backend.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class BloodStockRequestDto {

	@NotBlank(message = "Blood group is reuired")
	private String bloodGroup;

	@NotNull(message = "Units available is required")
	@Min(value = 1, message = "Units must be at least 1")
	private Integer unitsAvailable;

	@NotNull(message = "Expiry date is required")
	@Future(message = "Expiry date must be in the future")
	private LocalDate expiryDate;

	// getters & setters
	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public Integer getUnitsAvailable() {
		return unitsAvailable;
	}

	public void setUnitsAvailable(Integer unitsAvailable) {
		this.unitsAvailable = unitsAvailable;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
}
