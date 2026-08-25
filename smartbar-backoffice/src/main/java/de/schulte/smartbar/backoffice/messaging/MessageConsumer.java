package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageConsumer {

	@Incoming("my-channel")
	@Outgoing("uppercase-channel")
	public Uni<Message<String>> consume(final Message<String> message) {
		final var payload = message.getPayload();
		final var currentMillis = message.getMetadata(Long.class);
		Log.infof("Message %s consumed in %s", payload, getClass().getSimpleName());
		currentMillis.ifPresent(millis -> Log.infof("Metadata: millis %d", millis));
		return Uni.createFrom().item(Message.of(payload.toUpperCase(), () -> message.nack(new RuntimeException("Something went wrong"))));
	}

}
