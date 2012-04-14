package se.mah.helmet.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.entity.User;
import se.mah.helmet.server.storage.DAO;

import com.sun.jersey.api.NotFoundException;

@Path("/users/{user}/alarms/{alarm}")
public class AlarmResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Alarm getAlarmData(@PathParam("alarm") long alarmId) {
		return getAlarm(alarmId);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getContactHtml(@PathParam("alarm") long alarmId) {
		return getAlarm(alarmId).toString();
	}
	
	private Alarm getAlarm(long alarmId) {
		Alarm alarm = DAO.getById(Alarm.class, alarmId);
		if (alarm == null)
			throw new NotFoundException("No such Alarm.");
		return alarm; 
	}
}
