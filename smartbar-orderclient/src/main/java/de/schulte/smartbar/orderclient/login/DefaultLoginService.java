package de.schulte.smartbar.orderclient.login;

import java.util.UUID;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "default", lookupIfMissing = true)
public class DefaultLoginService implements LoginService {

	@Override
	public Uni<String> createNewLogin(String tableId) {
		Log.info("Creating new Login via default method");
		return Uni.createFrom().item(tableId);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		Log.info("Check Login via default method");
		return Uni.createFrom().item(false);
	}

	@Override
	public Uni<String> getTableIdByToken(String loginToken) {
		Log.info("Get TableId via default method");
		return Uni.createFrom().item(UUID.randomUUID().toString());
	}
}
