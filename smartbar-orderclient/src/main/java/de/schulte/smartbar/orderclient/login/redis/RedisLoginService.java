package de.schulte.smartbar.orderclient.login.redis;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import de.schulte.smartbar.orderclient.login.LoginService;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.RedisAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "redis")
public class RedisLoginService implements LoginService {

	private final RedisAPI redisAPI;

	@Inject
	public RedisLoginService(RedisAPI redisAPI) {
		this.redisAPI = redisAPI;
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		Log.info("Creating new Login via Redis");
		String token = UUID.randomUUID().toString();
		return redisAPI.set(List.of(tableId, token, "EX", "20"))
			.map(r -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		Log.info("Check Login via Redis");
		return redisAPI.get(tableId)
			.map(Objects::nonNull);
	}

}
