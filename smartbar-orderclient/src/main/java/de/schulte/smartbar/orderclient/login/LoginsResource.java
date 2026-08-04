package de.schulte.smartbar.orderclient.login;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import io.smallrye.mutiny.Uni;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;

@NonBlocking
public class LoginsResource implements LoginsApi {

	private final MenuApiClient menuApiClient;
	private final MenuMapper menuMapper;

	@Inject
	public LoginsResource(@RestClient MenuApiClient menuApiClient, MenuMapper menuMapper) {
		this.menuApiClient = menuApiClient;
		this.menuMapper = menuMapper;
	}

	@Override
	@CacheResult(cacheName = "menu-cache")
	public Uni<LoginResponseBody> login(String tableId) {
		return menuApiClient.getMenu().map(menuMapper::mapToLoginResponse);
	}

	// @CacheInvalidate(cacheName = "menu-cache")
	// @DELETE
	// public Uni<String> invalidateCacheByKey(String tableId) {
	// 	return Uni.createFrom().item("Ok");
	// }
}
