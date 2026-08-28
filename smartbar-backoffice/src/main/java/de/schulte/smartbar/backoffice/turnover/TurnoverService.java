package de.schulte.smartbar.backoffice.turnover;

import java.math.BigDecimal;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import de.schulte.smartbar.backoffice.messaging.Order;
import de.schulte.smartbar.backoffice.messaging.OrderPosition;
import io.quarkus.arc.Unremovable;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Unremovable
public class TurnoverService {

	@Incoming("turnover-db-channel")
	@Transactional
	public void onArticleOrdered(final Order order) {
		Log.info("Write turnover");
		order.positions().forEach(this::writeTurnover);
	}

	private void writeTurnover(OrderPosition position) {
		Log.infof("Write turnover for position %s", position);
		final Turnover turnover = new Turnover();
		turnover.articleId = position.articleId();
		turnover.quantity = position.quantity();
		turnover.turnoverTotalValue = position.price().multiply(new BigDecimal(position.quantity()));
		turnover.persist();
	}
}
