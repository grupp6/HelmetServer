package se.mah.helmet.server.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class AccData {
	private long id;
	private Date time;
	private double accX;
	private double accY;
	private double accZ;
	
	public AccData() { }
	
	public AccData(Date time, double accX, double accY, double accZ) {
		this.time = time;
		this.accX = accX;
		this.accY = accY;
		this.accZ = accZ;
	}

	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public double getAccX() {
		return accX;
	}
	public void setAccX(double accX) {
		this.accX = accX;
	}
	public double getAccY() {
		return accY;
	}
	public void setAccY(double accY) {
		this.accY = accY;
	}
	public double getAccZ() {
		return accZ;
	}
	public void setAccZ(double accZ) {
		this.accZ = accZ;
	}
}
