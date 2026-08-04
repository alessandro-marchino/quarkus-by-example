package de.schulte.smartbar.backoffice;

import java.security.Principal;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;

@Path("/user")
public class UserResource {

	@GET
	@Path("/me")
	public Response me(@Context SecurityContext securityContext) {
		Principal principal = securityContext.getUserPrincipal();
		String user = principal.getName();
		return Response.ok(user).build();
	}
}
