package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zosh.modal.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {

}
