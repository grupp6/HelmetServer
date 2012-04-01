package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	private long id;
	private String name;
	private Collection<Alarm> alarms = new ArrayList<Alarm>();
	private Collection<Trip> trips = new ArrayList<Trip>();
	
	public User() { }
	
	public User(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column (unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany
	public Collection<Alarm> getAlarms() {
		return alarms;
	}
	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	@OneToMany
	public Collection<Trip> getTrips() {
		return trips;
	}
	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}

}
