package se.mah.helmet.server.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Contact {
	@SuppressWarnings("unused")
	@Id
	@GeneratedValue
	private long id;
	String name;
	String phoneNbr;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNbr() {
		return phoneNbr;
	}
	public void setPhoneNbr(String phoneNbr) {
		this.phoneNbr = phoneNbr;
	}

}
