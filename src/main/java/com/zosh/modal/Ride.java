package com.zosh.modal;
import java.time.LocalDateTime;
import com.zosh.ride.domain.RideStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Ride {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Driver driver; 
	
	private double pickupLatitude;
	
	private double pickupLongitude;
	
	private double destinationLatitude;
	
	private double destinationLongitude;
	
	private double distence;
	
	private long duration;
	
	private RideStatus status;
	
	private LocalDateTime startTime;
	
	private LocalDateTime endTime;
	
	private double fare;
	
	public Ride() {
		// TODO Auto-generated constructor stub
	}
	
	public Ride(Integer id, User user, Driver driver, double pickupLatitude, double pickupLongitude,
			double destinationLatitude, double destinationLongitude, double distence, long duration, RideStatus status,
			LocalDateTime startTime, LocalDateTime endTime, double fare) {
		super();
		this.id = id;
		this.user = user;
		this.driver = driver;
		this.pickupLatitude = pickupLatitude;
		this.pickupLongitude = pickupLongitude;
		this.destinationLatitude = destinationLatitude;
		this.destinationLongitude = destinationLongitude;
		this.distence = distence;
		this.duration = duration;
		this.status = status;
		this.startTime = startTime;
		this.endTime = endTime;
		this.fare = fare;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public double getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(double pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public double getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(double pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public double getDestinationLatitude() {
		return destinationLatitude;
	}

	public void setDestinationLatitude(double destinationLatitude) {
		this.destinationLatitude = destinationLatitude;
	}

	public double getDestinationLongitude() {
		return destinationLongitude;
	}

	public void setDestinationLongitude(double destinationLongitude) {
		this.destinationLongitude = destinationLongitude;
	}

	public double getDistence() {
		return distence;
	}

	public void setDistence(double distence) {
		this.distence = distence;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public RideStatus getStatus() {
		return status;
	}

	public void setStatus(RideStatus status) {
		this.status = status;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}
	
}
