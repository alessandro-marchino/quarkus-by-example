package de.schulte.smartbar.orderclient.login;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/menu")
@RegisterRestClient
public interface MenuApiClient {

	@GET
	ApiMenu getMenu();
}
