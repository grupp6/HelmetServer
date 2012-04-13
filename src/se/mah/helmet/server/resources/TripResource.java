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

import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.entity.User;

import com.sun.jersey.api.NotFoundException;

@Path("/users/{user}/trips/{trip}")
public class TripResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Trip getTripData(@PathParam("trip") long tripId) {
		return getTrip(tripId);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getTripHtml(@PathParam("trip") long tripId) {
		return getTrip(tripId).toString();
	}
	
	private Trip getTrip(long tripId) {
		Trip trip = null; // TODO Hämta Trip från databasen
		if (trip == null)
			throw new NotFoundException("No such Trip.");
		return trip; 
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User updateTrip(Trip updatedTrip) {
		return null; // TODO Update trip in database, throw appropriate WebApplicationException if fail
	}
	
	@DELETE
	public void deleteTrip(@PathParam("trip") long tripId) {
		// TODO Delete trip in database
		// TODO If trip didn't exist, throw NotFoundException
	}
}
