package de.schulte.smartbar.orderclient.orders;

import de.schulte.smartbar.orderclient.api.OrdersApi;
import de.schulte.smartbar.orderclient.api.model.PlaceOrderRequest;
import de.schulte.smartbar.orderclient.login.LoginService;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@NonBlocking
public class OrderResource implements OrdersApi {
	private final LoginService loginService;

	@Inject
	public OrderResource(Instance<LoginService> loginServiceInstance) {
		this.loginService = loginServiceInstance.get();
	}

	@Override
	public Uni<Response> getOrderDetails(String loginToken, String orderId) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getOrderDetails'");
	}

	@Override
	public Uni<Response> listOrders(String loginToken) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'listOrders'");
	}

	@Override
	public Uni<Response> placeOrder(String loginToken, @Valid PlaceOrderRequest placeOrderRequest) {
		return this.loginService.getTableIdByToken(loginToken)
			.chain(tableId -> {
				if(tableId == null) {
					return this.notAllowed();
				}
				return this.mapAndStore(placeOrderRequest, tableId);
			});
	}

	@SuppressWarnings("null")
	private Uni<Response> mapAndStore(PlaceOrderRequest placeOrderRequest, String tableId) {
		final var order = new Order();
		order.orderPositions = placeOrderRequest.getItems().stream()
			.map(item -> new OrderPosition(item.getArticleId(), item.getQuantity(), item.getPrice()))
			.toList();
		order.tableId = tableId;
		return order.persist().map(_ -> Response.ok().build());
	}

	private Uni<Response> notAllowed() {
		return Uni.createFrom().item(Response.status(Status.UNAUTHORIZED).build());
	}

}
