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

import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.storage.DAO;

public class TripsResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final String userName;

	public TripsResource(UriInfo uriInfo, Request request, String userName) {
		this.userName = userName;
		this.uriInfo = uriInfo;
		this.request = request;
	}
	
	@Path("{trip}")
	public TripResource getTripResource(@PathParam("trip") String trip) {
		return new TripResource(uriInfo, request, userName, trip);
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		// TODO Implementera
		return "Inte färdigt";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String newAlarm(Trip newTrip) {
		DAO.insertUserTrip(userName, newTrip);
		return "Created new trip.";
	}
}
