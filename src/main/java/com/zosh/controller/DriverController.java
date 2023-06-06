package com.zosh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zosh.exception.DriverException;
import com.zosh.modal.Driver;
import com.zosh.modal.Ride;
import com.zosh.service.DriverService;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
	
	@Autowired
	private DriverService driverService;
	
	@GetMapping("/profile")
	public ResponseEntity<Driver> getReqDriverProfileHandler(@RequestHeader("Authorization") String jwt) throws DriverException {
		
		Driver driver = driverService.getReqDriverProfile(jwt);
		
		return new ResponseEntity<Driver>(driver,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<Ride> getDriversCurrentRideEntity(@PathVariable Integer driverId) throws DriverException{
		Ride ride=driverService.getDriversCurrentRide(driverId);
		
		return new ResponseEntity<Ride>(ride,HttpStatus.ACCEPTED);
	}

}
