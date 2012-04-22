package se.mah.helmet.server.resources;

import java.util.List;

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
import se.mah.helmet.server.storage.DAO;

@Path("/users")
public class UsersResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	
	@Path("{userName}")
	public UserResource getUserResource(@PathParam("userName") String userName) {
		return new UserResource(uriInfo, request, userName);
	}
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getHtml() {
		StringBuilder sb = new StringBuilder();
		sb.append("Users:<br>");
		for (User user : DAO.getAll(User.class)) {
			sb.append("<a href=\"" + uriInfo.getPath() + "/" + user.getLoginName() + "\">");
			sb.append(user.getLoginName());
			sb.append("</a><br>");
		}
		return sb.toString();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public List<User> getData() {
		return DAO.getAll(User.class);
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String newUser(User newUser) {
		DAO.insertUser(newUser);
		return "Created user";
	}
}
