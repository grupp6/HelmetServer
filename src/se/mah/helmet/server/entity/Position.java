package se.mah.helmet.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Position {
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private long id;
	private Date time;
	private double longitude;
	private double latitude;
	
	@SuppressWarnings("unused")
	private Position() { }
	
	public Position(Date time, double longitude, double latitude) {
		this.time = time;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
