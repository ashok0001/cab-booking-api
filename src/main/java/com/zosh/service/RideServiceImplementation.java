package com.zosh.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.modal.Driver;
import com.zosh.modal.Ride;
import com.zosh.modal.User;
import com.zosh.repository.DriverRepository;
import com.zosh.repository.RideRepository;
import com.zosh.request.RideRequest;
import com.zosh.ride.domain.RideStatus;

@Service
public class RideServiceImplementation implements RideService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private RideRepository rideRepository;
	
	@Autowired
	private Calculaters calculaters;
	
	@Autowired
	private DriverRepository driverRepository;

	@Override
	public Ride requestRide(RideRequest rideRequest) {
		
		double picupLatitude=rideRequest.getPikupLatitude();
		double picupLongitude=rideRequest.getPickupLongitude();
		double destinationLatitude=rideRequest.getDestinationLatitude();
		double destinationLongitude=rideRequest.getDestinationLongitude();
		User user=rideRequest.getUser();
		
		List<Driver> availableDrivers=driverService.getAvailableDrivers(picupLatitude, picupLongitude, 5);
		
		Driver nearestDriver=driverService.findNearestDriver(availableDrivers, picupLatitude, picupLongitude);
		
		  // Create a new ride request
        Ride ride = createRideRequest(user, nearestDriver, picupLatitude, picupLongitude, destinationLatitude, destinationLongitude);

        // Send a notification to the driver
//        rideService.sendNotificationToDriver(nearestDriver, ride);

        return ride;
        
		
	}

	@Override
	public Ride createRideRequest(User user, Driver nearesDriver, double picupLatitude, double pickupLongitude,
			double destinationLatitude, double destinationLongitude) {
		
		Ride ride=new Ride();
		
		RideRequest rideRequest=new RideRequest();
		rideRequest.setDestinationLatitude(destinationLatitude);
		rideRequest.setDestinationLongitude(destinationLongitude);
		rideRequest.setPickupLongitude(pickupLongitude);
		rideRequest.setPikupLatitude(picupLatitude);
		
		ride.setDriver(nearesDriver);
		ride.setUser(user);
		ride.setPickupLatitude(picupLatitude);
		ride.setPickupLongitude(pickupLongitude);
		ride.setDestinationLatitude(destinationLatitude);
		ride.setDestinationLongitude(destinationLongitude);
		ride.setStatus(RideStatus.REQUESTED);
		
		
		return rideRepository.save(ride);
	}

	@Override
	public void acceptRide(Ride ride) {
		
		ride.setStatus(RideStatus.ACCEPTED);
		
		Driver driver = ride.getDriver();
		
		driver.setCurrentRide(ride);
		
		driverRepository.save(driver);
		
		rideRepository.save(ride);
		// TODO Auto-generated method stub
		
	}

	@Override
	public void startRide(Ride ride) {
		
		ride.setStatus(RideStatus.STARTED);
		rideRepository.save(ride);
	
		
	}

	

	@Override
	public void completeRide(Ride ride) {
		
		ride.setStatus(RideStatus.COMPLETED);
		ride.setEndTime(LocalDateTime.now());;
		
		double distence=calculaters.calculateDistance(ride.getDestinationLatitude(), ride.getDestinationLongitude(), ride.getPickupLatitude(), ride.getPickupLongitude());
		
		long duration=calculaters.calculateDuration(ride.getStartTime(), ride.getEndTime());
		
		double fare=calculaters.calculateFare(distence);
		
		ride.setDistence(distence);
		ride.setFare(fare);
		ride.setDuration(duration);
		
		
		Driver driver =ride.getDriver();
		driver.getRides().add(ride);
		driver.setCurrentRide(null);
		
		driverRepository.save(driver);
		rideRepository.save(ride);
		
	}
	
	@Override
	public void cancleRide(Ride ride) {
		ride.setStatus(RideStatus.CANCELLED);
		rideRepository.save(ride);
		
		
	}

}
