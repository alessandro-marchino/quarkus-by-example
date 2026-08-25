package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UppercaseConsumer {

	@Incoming("uppercase-channel")
	public void consume(String message) {
		Log.infof("Message %s consumed in %s", message, getClass().getSimpleName());
	}

}
