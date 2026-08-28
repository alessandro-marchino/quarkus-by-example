package de.schulte.smartbar.backoffice;

import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Metadata;

import io.quarkus.arc.Unremovable;
import io.smallrye.reactive.messaging.MutinyEmitter;
import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
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
		final var event = new EntityChangedEvent(entity.getId(), entity.getClass().getSimpleName());
		final var kafkaMetaData = OutgoingKafkaRecordMetadata.builder()
			.withHeaders(new RecordHeaders().add("my-header", "my-value".getBytes()))
			.withKey(entity.getClass().getSimpleName())
			.build();
		this.emitter.sendMessageAndAwait(Message.of(event, Metadata.of(kafkaMetaData)));
	}
}
