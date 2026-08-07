package de.schulte.smartbar.orderclient.login;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.redis.client.RedisAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisLoginService implements LoginService {

	private final RedisAPI redisAPI;

	@Inject
	public RedisLoginService(RedisAPI redisAPI) {
		this.redisAPI = redisAPI;
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		String token = UUID.randomUUID().toString();
		return redisAPI.set(List.of(tableId, token, "EX", "20"))
			.map(r -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		return redisAPI.get(tableId)
			.map(Objects::nonNull);
	}

}
