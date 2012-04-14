package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.NaturalId;

@XmlRootElement
@Entity
public class User {
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
	
	@XmlTransient
	@OneToOne
	public Contact getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(Contact contactInfo) {
		this.contactInfo = contactInfo;
	}
	
	@XmlTransient
	@OneToMany (mappedBy="user", cascade=CascadeType.ALL)
	public List<Alarm> getAlarms() {
		return alarms;
	}
	public void setAlarms(List<Alarm> alarms) {
		this.alarms = alarms;
	}
	
	@XmlTransient
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

	@XmlTransient
	@OneToMany (cascade=CascadeType.ALL)
	public List<Contact> getEmergencyContacts() {
		return emergencyContacts;
	}

	public void setEmergencyContacts(List<Contact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
