package de.schulte.smartbar.orderclient.login;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.json.JsonMapper;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MenuApiClientImpl implements MenuApiClient {

	private final String apiUrl;

	public MenuApiClientImpl(@ConfigProperty(name = "backoffice.menuapi.url") String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	public ApiMenu getMenu() {
		try {
			final var request = HttpRequest.newBuilder(new URI(apiUrl)).GET().build();
			final var httpClient = HttpClient.newBuilder().build();
			final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			return new JsonMapper().readValue(response.body(), ApiMenu.class);
		} catch (URISyntaxException | IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
