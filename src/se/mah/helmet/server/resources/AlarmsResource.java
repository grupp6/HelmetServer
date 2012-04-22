package se.mah.helmet.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.Alarm;
import se.mah.helmet.server.storage.DAO;

public class AlarmsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final String userName;

	public AlarmsResource(UriInfo uriInfo, Request request, String userName) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.userName = userName;
	}
	
	@Path("{alarm}")
	public AlarmResource getAlarmResource(@PathParam("alarm") String alarm) {
		return new AlarmResource(uriInfo, request, userName, alarm);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		// TODO Implementera
		return "Inte färdigt";
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void newAlarm(Alarm newAlarm) {
		DAO.insertUserAlarm(userName, newAlarm);
	}
}
