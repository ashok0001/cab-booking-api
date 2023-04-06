package com.zosh.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
	
	public Optional<Driver> findByEmail(String email);

}
