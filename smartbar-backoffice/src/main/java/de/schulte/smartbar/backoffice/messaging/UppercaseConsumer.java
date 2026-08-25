package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.annotations.Merge;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UppercaseConsumer {

	@Incoming("uppercase-channel")
	@Merge
	public void consume(String message) {
		Log.infof("Message %s consumed in %s", message, getClass().getSimpleName());
	}

}
