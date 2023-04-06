package com.zosh.request;

public class RideRequest {
	
	private double pickupLongitude;
	private double pikupLatitude;
	private double destinationLongitude;
	private double destinationLatitude;
	
	public RideRequest() {
		// TODO Auto-generated constructor stub
	}
	
	public RideRequest(double pickupLongitude, double pikupLatitude, double destinationLongitude,
			double destinationLatitude) {
		super();
		this.pickupLongitude = pickupLongitude;
		this.pikupLatitude = pikupLatitude;
		this.destinationLongitude = destinationLongitude;
		this.destinationLatitude = destinationLatitude;
	}

	public double getPickupLongitude() {
		return pickupLongitude;
	}
	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}
	public double getPikupLatitude() {
		return pikupLatitude;
	}
	public void setPikupLatitude(double pikupLatitude) {
		this.pikupLatitude = pikupLatitude;
	}
	public double getDestinationLongitude() {
		return destinationLongitude;
	}
	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}
	public double getDestinationLatitude() {
		return destinationLatitude;
	}
	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	@Override
	public String toString() {
		return "RideRequest [pickupLongitude=" + pickupLongitude + ", pikupLatitude=" + pikupLatitude
				+ ", destinationLongitude=" + destinationLongitude + ", destinationLatitude=" + destinationLatitude
				+ "]";
	}
	
	

}
