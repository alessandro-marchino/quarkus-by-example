package de.schulte.smartbar.orderclient.login;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import de.schulte.smartbar.orderclient.api.LoginsApi;
import de.schulte.smartbar.orderclient.api.model.LoginResponseBody;
import io.smallrye.mutiny.Uni;
import io.smallrye.common.annotation.NonBlocking;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@NonBlocking
public class LoginsResource implements LoginsApi {

	private final MenuApiClient menuApiClient;
	private final MenuMapper menuMapper;
	private final LoginService loginService;

	@Inject
	public LoginsResource(@RestClient MenuApiClient menuApiClient, MenuMapper menuMapper, LoginService loginService) {
		this.menuApiClient = menuApiClient;
		this.menuMapper = menuMapper;
		this.loginService = loginService;
	}

	@Override
	public Uni<Response> login(String tableId) {
		final var chain = menuApiClient.getMenu()
			.map(menuMapper::mapToLoginResponse)
			.flatMap(response -> this.loginService.createNewLogin(tableId)
				.onItem()
				.invoke(response::setLoginToken)
				.onItem()
				.transform(token -> Response.ok(response).build()));
		return this.loginService.hasLogin(tableId)
			.chain(hasLogin -> hasLogin ? getLoginAlreadyExists() : chain);
	}

	private Uni<Response> getLoginAlreadyExists() {
		return Uni.createFrom().item(Response.status(Status.CONFLICT).build());
	}

}
