package de.schulte.smartbar.orderclient.login;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;

public interface LoginService {
	Uni<String> createNewLogin(final String tableId);

	Uni<Boolean> hasLogin(final String tableId);

    Uni<String> getTableIdByToken(String loginToken);
}
