package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Acknowledgment;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Acknowledgment.Strategy;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UppercaseConsumer {

	@Incoming("uppercase-channel")
	@Acknowledgment(Strategy.POST_PROCESSING)
	public Uni<Void> consume(final Message<String> message) {
		Log.infof("Message %s consumed in %s", message.getPayload(), getClass().getSimpleName());
		return Uni.createFrom().voidItem();
	}

}
