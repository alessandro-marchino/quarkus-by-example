package de.schulte.smartbar.orderclient.login.dynamo;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.schulte.smartbar.orderclient.login.LoginService;
import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "dynamo")
public class DynamoLoginService implements LoginService {

	private final DynamoDbAsyncClient dynamoDbAsyncClient;

	@Inject
	public DynamoLoginService(DynamoDbAsyncClient dynamoDbAsyncClient) {
		this.dynamoDbAsyncClient = dynamoDbAsyncClient;
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		final var token = UUID.randomUUID().toString();
		final var expiresAt = Instant.now().plusSeconds(60).toEpochMilli();

		Map<String, AttributeValue> login = new HashMap<>();
		login.put("tableId", AttributeValue.builder().s(tableId).build());
		login.put("token", AttributeValue.builder().s(token).build());
		login.put("expiresAt", AttributeValue.builder().n(Long.toString(expiresAt)).build());

		return Uni.createFrom()
			.completionStage(dynamoDbAsyncClient.putItem(PutItemRequest.builder().tableName("logins").item(login).build()))
			.map(_ -> token);
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		Map<String, AttributeValue> key = new HashMap<>();
		key.put("tableId", AttributeValue.builder().s(tableId).build());
		return Uni.createFrom()
			.completionStage(dynamoDbAsyncClient.getItem(GetItemRequest.builder().tableName("logins").key(key).build()))
			.map(response -> {
				boolean hasItem = response.hasItem();
				if(!hasItem) {
					return false;
				}
				Instant now = Instant.now();
				Instant expires = Instant.ofEpochMilli(Long.valueOf(response.item().get("expiresAt").n()));
				Log.infof("Now: %s", now.toString());
				Log.infof("Expiration: %s", expires.toString());
				return now.isBefore(expires);
			});
	}

	@Override
	public Uni<String> getTableIdByToken(String loginToken) {
		throw new UnsupportedOperationException("Unimplemented method 'getTableIdByToken'");
	}
}
