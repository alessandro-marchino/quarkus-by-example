package de.schulte.smartbar.orderclient.login.mongo;

import java.time.Instant;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(database = "logins", collection = "logins-timed")
public class Login {

	@BsonProperty("tableNumber")
	private String tableId;
	private String token;
	private Instant expiresAt;

	public Login() {
		// Empty constructor
	}

	public Login(String tableId, String token, Instant expiresAt) {
		this.tableId = tableId;
		this.token = token;
		this.expiresAt = expiresAt;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Instant expiresAt) {
		this.expiresAt = expiresAt;
	}

}
