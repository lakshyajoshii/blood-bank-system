package com.bloodbank.backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloodbank.backend.entity.BloodStock;

@Repository
public interface BloodStockRepository extends JpaRepository<BloodStock, Long> {
	Optional<BloodStock> findByBloodGroup(String bloodGroup);

	List<BloodStock> findByUnitsAvailableGreaterThan(int units);

	List<BloodStock> findByExpiryDateAfter(LocalDate date);

	Optional<BloodStock> findByBloodGroupAndExpiryDateAfter(String bloodGroup, LocalDate date);
}
