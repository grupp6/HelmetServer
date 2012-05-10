package se.mah.helmet.server.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.Trip;
import se.mah.helmet.server.storage.DAO;

import com.sun.jersey.api.NotFoundException;

public class TripResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final long tripId;
	
	public TripResource(UriInfo uriInfo, Request request, String userName,
			String trip) {
		this.uriInfo = uriInfo;
		this.request = request;
		if (trip.equals("last"))
			tripId = DAO.getLastTripId(userName);
		else
			tripId = Long.valueOf(trip);
	}
	
	@Path("data/loc")
	public LocsResource getLocsResrouce() {
		return new LocsResource(uriInfo, request, tripId);
	}
	
	@Path("data/g")
	public AccsResource getAccsResrouce() {
		return new AccsResource(uriInfo, request, tripId);
	}
	
	@Path("map")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response getMap(@Context HttpServletResponse servletResponse) throws IOException {
		File file = new File("C:/Users/stud/java/projects/HelmetServer/WebContent/pages/trip_map.html");
		return Response.ok().entity(new FileInputStream(file)).build();
		//servletResponse.sendRedirect("../../../../pages/test.html");
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Trip getTripData() {
		return getTrip(tripId);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getTripHtml() {
		// TODO Implementera
		return getTrip(tripId).toString();
	}
	
	private Trip getTrip(long tripId) {
		Trip trip = DAO.getById(Trip.class, tripId);
		if (trip == null)
			throw new NotFoundException("No such Trip.");
		return trip; 
	}
		
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void updateTrip(Trip updatedTrip) {
		DAO.update(updatedTrip);
	}
	
	@DELETE
	public void deleteTrip() {
		DAO.deleteById(Trip.class, tripId);
	}
	
	@GET
	@Path("source-id")
	@Produces(MediaType.TEXT_PLAIN)
	public String getSourceId() {
		return String.valueOf(getTrip(tripId).getSourceId());
	}
	
	@GET
	@Path("coords")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCoords() {
		StringBuilder json = new StringBuilder();
		json.append('[');
		for (double[] coord : DAO.getCoordsInTrip(tripId)) {
			json.append('[');
			json.append(coord[0]);
			json.append(',');
			json.append(coord[1]);
			json.append("],");
		}
		json.deleteCharAt(json.length() - 1);
		json.append(']');
		return json.toString();
	}
}
