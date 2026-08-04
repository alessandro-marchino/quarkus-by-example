package de.schulte.smartbar.orderclient.login;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import com.fasterxml.jackson.databind.json.JsonMapper;

import de.schulte.smartbar.backoffice.api.model.ApiMenu;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MenuApiClientImpl implements MenuApiClient {

	private final String apiUrl;
	private final JsonMapper jsonMapper;

	public MenuApiClientImpl(@ConfigProperty(name = "backoffice.menuapi.url") String apiUrl) {
		this.apiUrl = apiUrl;
		this.jsonMapper = new JsonMapper();
	}

	@Override
	public ApiMenu getMenu() {
		final var request = new HttpGet(apiUrl);
		try(CloseableHttpClient client = HttpClients.createDefault()) {
			return client.execute(request, response -> jsonMapper.readValue(response.getEntity().getContent(), ApiMenu.class));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
