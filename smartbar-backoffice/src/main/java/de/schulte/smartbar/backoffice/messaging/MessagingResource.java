package de.schulte.smartbar.backoffice.messaging;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/message")
@NonBlocking
public class MessagingResource {

	private final MutinyEmitter<String> emitter;

	public MessagingResource(@Channel("my-channel") MutinyEmitter<String> emitter) {
		this.emitter = emitter;
	}

	@POST
	@Consumes("text/plain")
	public Uni<String> postMessage(final String text) {
		return this.emitter.send(text).map(_ -> "Message sent");
	}
}
