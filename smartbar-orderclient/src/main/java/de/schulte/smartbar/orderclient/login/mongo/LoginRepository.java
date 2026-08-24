package de.schulte.smartbar.orderclient.login.mongo;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class LoginRepository implements ReactivePanacheMongoRepository<Login> {

	public Uni<Login> findByTableId(String tableId) {
		return find("tableNumber", tableId).firstResult();
	}

	public Uni<Login> findByLoginToken(String loginToken) {
		return find("token", loginToken).firstResult();
	}
}
