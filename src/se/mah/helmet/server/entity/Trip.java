package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Trip {
	private long id;
	private Collection<AccData> accData = new ArrayList<AccData>();
	private Collection<Position> locData = new ArrayList<Position>();
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@OneToMany
	public Collection<AccData> getAccData() {
		return accData;
	}
	public void setAccData(Collection<AccData> accData) {
		this.accData = accData;
	}
	
	@OneToMany
	public Collection<Position> getLocData() {
		return locData;
	}
	public void setLocData(Collection<Position> locData) {
		this.locData = locData;
	}
}