package com.zosh.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zosh.modal.Driver;
import com.zosh.modal.Ride;
import com.zosh.modal.User;
import com.zosh.repository.RideRepository;
import com.zosh.request.RideRequest;

@Service
public class RideServiceImplementation implements RideService {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private RideRepository rideRepository;

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
		
		
		return rideRepository.save(ride);
	}

}
