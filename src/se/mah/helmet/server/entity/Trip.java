package se.mah.helmet.server.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Trip {
	private long id;
	private String name;
	private User user;
	private List<AccData> accData = new ArrayList<AccData>();
	private List<Position> locData = new ArrayList<Position>();
	
	public Trip() { }
	
	public Trip(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany (cascade=CascadeType.ALL)
	public List<AccData> getAccData() {
		return accData;
	}
	public void setAccData(List<AccData> accData) {
		this.accData = accData;
	}
	
	@OneToMany (cascade=CascadeType.ALL)
	public List<Position> getLocData() {
		return locData;
	}
	public void setLocData(List<Position> locData) {
		this.locData = locData;
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
