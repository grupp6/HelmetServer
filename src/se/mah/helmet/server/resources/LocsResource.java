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

import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.storage.DAO;

public class LocsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final long tripId;

	public LocsResource(UriInfo uriInfo, Request request, long tripId) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.tripId = tripId;
	}
	
	@Path("{loc}")
	public LocResource getLocResource(@PathParam("loc") String loc) {
		return new LocResource(uriInfo, request, tripId, loc);
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
	public String newAccData(Position newPosition) {
		DAO.insertTripPosition(tripId, newPosition);
		return "Added new loc.";
	}
}
