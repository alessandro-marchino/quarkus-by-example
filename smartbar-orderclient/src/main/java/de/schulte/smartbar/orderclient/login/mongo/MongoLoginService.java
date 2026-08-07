package de.schulte.smartbar.orderclient.login.mongo;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import de.schulte.smartbar.orderclient.login.LoginService;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "mongo")
public class MongoLoginService implements LoginService {

	@Override
	public Uni<String> createNewLogin(String tableId) {
		Log.info("Creating new Login via Mongo");
		var token = UUID.randomUUID().toString();
		var expiresAt = Instant.now().plusSeconds(20);
		final var login = new Login(tableId, token, expiresAt);
		return login.persist()
			.map(_ -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		return Login.findByTableId(tableId).map(Objects::nonNull);
	}

}
