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

@Path("/users/{user}/alarms")
public class AlarmsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		// TODO Implementera
		return "Inte färdigt";
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void newAlarm(Alarm newAlarm, @PathParam("user") String userName) {
		DAO.insertUserAlarm(userName, newAlarm);
	}
}
