package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NaturalId;

@Entity
public class User {
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private long id;
	private String loginName;
	private Contact contactInfo;
	private List<Contact> emergencyContacts = new ArrayList<Contact>();
	private List<Alarm> alarms = new ArrayList<Alarm>();
	private List<Trip> trips = new ArrayList<Trip>();
	
	@SuppressWarnings("unused")
	private User() { }
	
	public User(String loginName) {
		this.loginName = loginName;
	}
	
	public void addAlarm(Alarm alarm) {
		this.alarms.add(alarm);
		alarm.setUser(this);
	}
	
	public void addTrip(Trip trip) {
		trip.setUser(this);
		this.trips.add(trip);
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Contact getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(Contact contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	@OneToMany (cascade=CascadeType.ALL)
	public List<Alarm> getAlarms() {
		return alarms;
	}
	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	@OneToMany (cascade=CascadeType.ALL)
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> trips) {
		this.trips = trips;
	}

	@NaturalId
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@OneToMany (cascade=CascadeType.ALL)
	public List<Contact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<Contact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
}
