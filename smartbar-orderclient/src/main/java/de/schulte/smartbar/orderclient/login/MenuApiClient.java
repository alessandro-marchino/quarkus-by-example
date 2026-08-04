package de.schulte.smartbar.orderclient.login;

import java.util.List;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/menu")
@RegisterRestClient
public interface MenuApiClient {

	@GET
	@Retry(maxRetries = 10)
	@Timeout(50)
	@Fallback(fallbackMethod = "getFallbackMenu")
	Uni<ApiMenu> getMenu();

	default Uni<ApiMenu> getFallbackMenu() {
		final var apiMenu = new ApiMenu();
		apiMenu.setCategories(List.of());
		return Uni.createFrom().item(apiMenu);
	}
}
