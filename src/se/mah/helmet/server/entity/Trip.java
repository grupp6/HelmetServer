package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@Entity
public class Trip {
	private long id;
	private long sourceId;
	private String name;
	private List<AccData> accData = new ArrayList<AccData>();
	private List<Position> locData = new ArrayList<Position>();
	private User user;
	
	public Trip() { }
	
	public Trip(String name) {
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

	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void addAccData(AccData accData) {
		this.accData.add(accData);
	}
	
	public void addPosData(Position position) {
		locData.add(position);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	@OneToMany (cascade=CascadeType.ALL)
	public List<AccData> getAccData() {
		return accData;
	}
	public void setAccData(List<AccData> accData) {
		this.accData = accData;
	}
	
	@XmlTransient
	@OneToMany (cascade=CascadeType.ALL)
	public List<Position> getLocData() {
		return locData;
	}
	public void setLocData(List<Position> locData) {
		this.locData = locData;
	}

	public long getSourceId() {
		return sourceId;
	}

	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public String toString() {
		return "Trip [id=" + id + ", sourceId=" + sourceId + ", name=" + name + "]";
	}
}
