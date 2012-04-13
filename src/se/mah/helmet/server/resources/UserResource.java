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

import se.mah.helmet.server.entity.User;
import se.mah.helmet.server.storage.DAO;

import com.sun.jersey.api.NotFoundException;

@Path("/users/{user}")
public class UserResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User getContactData(@PathParam("user") String userName) {
		return getUser(userName);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getContactHtml(@PathParam("user") String userName) {
		return getUser(userName).toString();
	}
	
	private User getUser(String userName) {
		User user = DAO.getUser(userName);
		if (user == null)
			throw new NotFoundException("No such User.");
		return user; 
	}
	
	@PUT
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public User updateUser(User updatedUser) {
		return null; // TODO Update user in database, throw appropriate WebApplicationException if fail
	}
	
	@DELETE
	public void deleteUser(@PathParam("user") String userName) {
		// TODO Delete user in database
		// TODO If user didn't exist, throw NotFoundException
	}
}
