package se.mah.helmet.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.AccData;
import se.mah.helmet.server.storage.DAO;

public class AccsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final long tripId;

	public AccsResource(UriInfo uriInfo, Request request, long tripId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.tripId= tripId; 
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
	public void newAccData(AccData newAccData) {
		DAO.insertTripAccData(tripId, newAccData);
	}
}
