package de.schulte.smartbar.orderclient.login;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import io.smallrye.mutiny.Uni;
import io.quarkus.cache.Cache;
import io.quarkus.cache.CacheName;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;

@NonBlocking
public class LoginsResource implements LoginsApi {

	private final MenuApiClient menuApiClient;
	private final MenuMapper menuMapper;
	private final Cache cache;

	@Inject
	public LoginsResource(@RestClient MenuApiClient menuApiClient, MenuMapper menuMapper, @CacheName("menu-cache") Cache cache) {
		this.menuApiClient = menuApiClient;
		this.menuMapper = menuMapper;
		this.cache = cache;
	}

	@Override
	public Uni<LoginResponseBody> login(String tableId) {
		return cache.getAsync(tableId, _ -> menuApiClient.getMenu().map(menuMapper::mapToLoginResponse));
	}

}
