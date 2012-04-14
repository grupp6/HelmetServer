package se.mah.helmet.server.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getText() {
		StringBuilder sb = new StringBuilder();
		for (User user : DAO.getAll(User.class)) {
			sb.append("<a href=\"" + uriInfo.getPath() + "/" + user.getLoginName() + "\">");
			sb.append(user.getLoginName());
			sb.append("</a><br>");
		}
		return sb.toString();
	}
	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public void newUser(User newUser) {
		DAO.insertUser(newUser);
	}
}
