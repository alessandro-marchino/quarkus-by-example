package de.schulte.smartbar.backoffice;

import java.util.Map;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import io.smallrye.reactive.messaging.MutinyEmitter;
import io.smallrye.reactive.messaging.rabbitmq.OutgoingRabbitMQMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
@Unremovable
public class MasterdataService {

	private final MutinyEmitter<EntityChangedEvent> emitter;

	@Inject
	public MasterdataService(@Channel("menu-update-events") MutinyEmitter<EntityChangedEvent> emitter) {
		this.emitter = emitter;
	}

	public void fireEventChangedEvent(final BaseEntity entity) {
		Log.infof("Firing event for %s", entity.getClass());
		final var event = new EntityChangedEvent(entity.getId(), entity.getClass().getSimpleName());
		final var metadata = OutgoingRabbitMQMetadata.builder()
			.withHeaders(Map.of("my-header", "my-value".getBytes()))
			.build();
		this.emitter.sendMessageAndAwait(Message.of(event, Metadata.of(metadata)));
	}
}
