package com.zosh.service;

import com.zosh.modal.Driver;
import com.zosh.modal.Ride;
import com.zosh.modal.User;
import com.zosh.request.RideRequest;

public interface RideService {
	
	
	public Ride requestRide(RideRequest rideRequest);
	
	public Ride createRideRequest(User user, Driver nearesDriver,double picupLatitude,double pickupLongitude,double destinationLatitude,double destinationLongitude);
	

}
