package de.schulte.smartbar.orderclient.login.mongo;

import java.time.Instant;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.smallrye.mutiny.Uni;

@MongoEntity(database = "logins", collection = "logins-timed")
public class Login extends ReactivePanacheMongoEntity {

	@BsonProperty("tableNumber")
	public String tableId;
	public String token;
	public Instant expiresAt;

	public Login() {
		// Empty constructor
	}

	public Login(String tableId, String token, Instant expiresAt) {
		this.tableId = tableId;
		this.token = token;
		this.expiresAt = expiresAt;
	}

	public static Uni<Login> findByTableId(String tableId) {
		return find("tableNumber", tableId).firstResult();
	}
}
