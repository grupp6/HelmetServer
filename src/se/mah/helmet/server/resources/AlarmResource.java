package se.mah.helmet.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.storage.DAO;

import com.sun.jersey.api.NotFoundException;

public class AlarmResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final Long alarmId;
	
	public AlarmResource(UriInfo uriInfo, Request request, String userName,
			String alarm) {
		this.uriInfo = uriInfo;
		this.request = request;
		if (alarm.equals("last"))
			alarmId = DAO.getLastAlarmId(userName);
		else
			alarmId = Long.valueOf(alarm);
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alarm getAlarmData() {
		return getAlarm(alarmId);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getContactHtml() {
		return getAlarm(alarmId).toString();
	}
	
	private Alarm getAlarm(long alarmId) {
		Alarm alarm = DAO.getById(Alarm.class, alarmId);
		if (alarm == null)
			throw new NotFoundException("No such Alarm.");
		return alarm; 
	}
}
