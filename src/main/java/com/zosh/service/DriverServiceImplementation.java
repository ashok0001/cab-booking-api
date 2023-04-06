package com.zosh.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zosh.modal.Driver;
import com.zosh.repository.DriverRepository;
import com.zosh.request.DriversSignupRequest;
import com.zosh.ride.domain.RideStatus;
import com.zosh.ride.domain.UserRole;

@Service
public class DriverServiceImplementation implements DriverService {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private Calculaters distenceCalculator;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Driver> getAvailableDrivers(double pickupLatitude, double picupLongitude, double radius) {
		List<Driver> allDrivers=driverRepository.findAll();
		
		List<Driver> availableDriver=new ArrayList<>();
		
		for(Driver driver:allDrivers) {
			
			if(driver.getRide()!=null && driver.getRide().getStatus()!=RideStatus.COMPLETED) {
				continue;
			}
			
			double driverLatitude=driver.getLatitude();
			double driverLongitude=driver.getLongitude();
			
			
			
			double distence=distenceCalculator.calculateDistance(driverLatitude,driverLongitude, pickupLatitude, picupLongitude);
			
			if(distence<=radius) {
				availableDriver.add(driver);
			}
		}
		
		return availableDriver;
	}

	@Override
	public Driver findNearestDriver(List<Driver> availableDrivers, double picupLatitude, double picupLongitude) {
		
		double min=Double.MAX_VALUE;;
		Driver nearestDriver = null;
		
		for(Driver driver : availableDrivers) {
			double driverLatitude=driver.getLatitude();
			double driverLongitude=driver.getLongitude();
			
			double distence=distenceCalculator.calculateDistance(picupLatitude, picupLongitude, driverLatitude,driverLongitude);
			
			if(min>distence) {
				min=distence;
				nearestDriver=driver;
			}
		}
		// TODO Auto-generated method stub
		return nearestDriver;
	}

	@Override
	public Driver registerDriver(DriversSignupRequest driversSignupRequest) {
		

		
		Driver driver = new Driver();
		
		String encodedPassword=passwordEncoder.encode(driversSignupRequest.getPassword());
		
		driver.setEmail(driversSignupRequest.getEmail());
		driver.setName(driversSignupRequest.getName());
		driver.setMobile(driversSignupRequest.getMobile());
		driver.setPassword(encodedPassword);
		driver.setLicense(driversSignupRequest.getLicense());
		driver.setVehicle(driversSignupRequest.getVehicle());
		driver.setRole(UserRole.DRIVER);
		
		
		
		return driverRepository.save(driver);
	}

}
