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

import se.mah.helmet.server.entity.User;

@Path("/users")
public class UsersResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		// TODO Implementera
		return null;
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void newUser(User newUser) {
		// TODO Validate and save user in database
	}
}