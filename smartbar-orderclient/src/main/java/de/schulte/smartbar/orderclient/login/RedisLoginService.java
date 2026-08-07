package de.schulte.smartbar.orderclient.login;

import java.util.UUID;

import org.jspecify.annotations.NonNull;

import io.quarkus.redis.client.RedisClientName;
import io.quarkus.redis.datasource.ReactiveRedisDataSource;
import io.quarkus.redis.datasource.keys.ReactiveKeyCommands;
import io.quarkus.redis.datasource.value.ReactiveValueCommands;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class RedisLoginService implements LoginService {

	private ReactiveValueCommands<String, @NonNull String> tokens;
	private ReactiveKeyCommands<@NonNull String> keys;

	@Inject
	public RedisLoginService(@RedisClientName("logins") ReactiveRedisDataSource reactiveRedisDataSource) {
		tokens = reactiveRedisDataSource.value(String.class);
		keys = reactiveRedisDataSource.key(String.class);
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		String token = UUID.randomUUID().toString();
		return tokens.set(tableId, token)
			.chain(v -> keys.expire(tableId, 20))
			.map(v -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		return keys.exists(tableId);
	}

}
