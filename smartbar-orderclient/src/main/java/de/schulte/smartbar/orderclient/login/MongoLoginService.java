package de.schulte.smartbar.orderclient.login;

import java.time.Instant;
import java.util.UUID;

import org.bson.Document;

import io.quarkus.arc.lookup.LookupIfProperty;
import io.quarkus.logging.Log;
import io.quarkus.mongodb.reactive.ReactiveMongoClient;
import io.quarkus.mongodb.reactive.ReactiveMongoCollection;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@LookupIfProperty(name = "smartbar.orderclient.login", stringValue = "mongo")
public class MongoLoginService implements LoginService {

	private final ReactiveMongoClient mongoClient;

	@Inject
	public MongoLoginService(ReactiveMongoClient mongoClient) {
		this.mongoClient = mongoClient;
	}

	@Override
	public Uni<String> createNewLogin(String tableId) {
		Log.info("Creating new Login via Mongo");
		var token = UUID.randomUUID().toString();
		var expiresAt = Instant.now().plusSeconds(20);
		Document loginDocument = new Document()
			.append("tableId", tableId)
			.append("token", token)
			.append("expiresAt", expiresAt);
		return getLoginCollection()
			.insertOne(loginDocument)
			.map(_ -> token);
	}

	private ReactiveMongoCollection<Document> getLoginCollection() {
		return mongoClient.getDatabase("logins").getCollection("logins-timed");
	}

	@Override
	public Uni<Boolean> hasLogin(String tableId) {
		Log.info("Check Login via Mongo");
		return getLoginCollection().find(new Document().append("tableId", tableId))
			.collect()
			.asList()
			.map(documents -> !documents.isEmpty());
	}

}
