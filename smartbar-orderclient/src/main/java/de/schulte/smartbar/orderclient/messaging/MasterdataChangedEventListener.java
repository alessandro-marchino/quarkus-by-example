package de.schulte.smartbar.orderclient.messaging;

import java.lang.reflect.Method;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import io.quarkus.arc.Unremovable;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheKeyGenerator;
import io.quarkus.cache.DefaultCacheKey;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Unremovable
public class MasterdataChangedEventListener {

	@Incoming("menu-update-events")
	@CacheInvalidate(cacheName = "menu-cache", keyGenerator = MasterdataChangedCacheKeyGenerator.class)
	public Uni<Void> onMasterdataChanged(final Message<MasterdataChangedEvent> message) {
		Log.infof("onMasterdataChanged %s", message);
		return Uni.createFrom().completionStage(message.ack());
	}

	public static class MasterdataChangedCacheKeyGenerator implements CacheKeyGenerator {
		@Override
		public Object generate(Method method, Object... methodParams) {
			return new DefaultCacheKey("menu-cache");
		}
	}
}
