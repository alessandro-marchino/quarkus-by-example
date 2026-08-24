package de.schulte.smartbar.orderclient.login.mongo;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import de.schulte.smartbar.orderclient.login.LoginService;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "mongo")
public class MongoLoginService implements LoginService {

	private final LoginRepository loginRepository;

	@Inject
	public MongoLoginService(LoginRepository loginRepository) {
		this.loginRepository = loginRepository;
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		Log.info("Creating new Login via Mongo");
		var token = UUID.randomUUID().toString();
		var expiresAt = Instant.now().plusSeconds(20);
		final var login = new Login(tableId, token, expiresAt);
		return loginRepository.persist(login)
			.map(_ -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		Log.info("Creating new Login via Redis");
		return loginRepository.findByTableId(tableId).map(Objects::nonNull);
	}

	@Override
	public Uni<String> getTableIdByToken(String loginToken) {
		return loginRepository.findByLoginToken(loginToken).map(this::getTableIdIfValid);
	}

	private String getTableIdIfValid(Login login) {
		return login != null && login.getExpiresAt().isAfter(Instant.now()) ? login.getTableId() : null;
	}
}
