package com.zosh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zosh.modal.Ride;

public interface RideRepository extends JpaRepository<Ride, Integer> {
	
	@Query("SELECT R FROM RIDE R WHERE R.STATUS=REQUESTED AND R.DRIVER.ID=:driverId")
	public Ride getDriversCurrentRide(@Param("driverId") Integer driverId);

}
