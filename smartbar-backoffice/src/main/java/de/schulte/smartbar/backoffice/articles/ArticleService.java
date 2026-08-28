package de.schulte.smartbar.backoffice.articles;

import java.time.LocalDateTime;
import java.util.Optional;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import de.schulte.smartbar.backoffice.messaging.Order;
import de.schulte.smartbar.backoffice.messaging.OrderPosition;
import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Unremovable
public class ArticleService {

	@Incoming("order-placed-events")
	@Transactional
	public void onArticleOrdered(final Order order) {
		Log.info("Update Article");
		order.positions().forEach(this::processOrderPosition);
	}

	private void processOrderPosition(OrderPosition position) {
		Article.<Article>findByIdOptional(position.articleId())
			.ifPresent(a -> this.updateArticleData(a, position.quantity()));
	}

	private void updateArticleData(Article a, Integer quantity) {
		a.lastOrdered = LocalDateTime.now();
		a.timesOrdered = Optional.ofNullable(a.timesOrdered).orElse(0) + quantity;
		a.persist();
	}
}
