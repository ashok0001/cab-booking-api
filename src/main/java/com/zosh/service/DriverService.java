package com.zosh.service;

import java.util.List;

import com.zosh.modal.Driver;

public interface DriverService {
	
	
	public List<Driver> getAvailableDrivers(double pickupLatitude,double picupLongitude,double radius);
	
	public Driver findNearestDriver(List<Driver> availableDrivers, double picupLatitude, double picupLongitude);
	
	



}
