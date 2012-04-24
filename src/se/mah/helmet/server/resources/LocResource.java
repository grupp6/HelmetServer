package se.mah.helmet.server.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import se.mah.helmet.server.entity.Position;
import se.mah.helmet.server.storage.DAO;

public class LocResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	private final long locId;

	public LocResource(UriInfo uriInfo, Request request, long tripId, String loc) {
		this.uriInfo = uriInfo;
		this.request = request;
		if (loc.equals("last"))
			locId = DAO.getLastLocId(tripId);
		else
			locId = Long.valueOf(loc);
	}
	
	@Path("source-id")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSourceId() {
		return String.valueOf(DAO.getById(Position.class, locId).getSourceId());
	}
}
