package com.zosh.service;

import org.springframework.stereotype.Service;

@Service
public class DistenceCalculater {
	
	 private static final int EARTH_RADIUS = 6371; // Radius of the Earth in kilometers
	 
//	 a = sin²(Δlat/2) + cos(lat1).cos(lat2).sin²(Δlon/2)
//	 c = 2.atan2(√a, √(1−a))
//	 d = R.c

	 public double calculateDistance(double sourceLat, double sourceLng, double destLat, double destLng) {
		 
		 double dLat = Math.toRadians(destLat - sourceLat);
	     double dLng = Math.toRadians(destLng - sourceLng);
	     double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
	                + Math.cos(Math.toRadians(sourceLat)) * Math.cos(Math.toRadians(destLat))
	                * Math.sin(dLng / 2) * Math.sin(dLng / 2);
	     double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	     double distance = EARTH_RADIUS * c;
	     return distance;
	 }

}
