package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MessageConsumer {

	@Incoming("my-channel")
	@Outgoing("uppercase-channel")
	public String consume(String message) {
		Log.infof("Message %s consumed in %s", message, getClass().getSimpleName());
		return message.toUpperCase();
	}
}
