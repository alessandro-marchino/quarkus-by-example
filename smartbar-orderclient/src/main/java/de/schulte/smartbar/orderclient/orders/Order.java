package de.schulte.smartbar.orderclient.orders;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;

@MongoEntity(database = "smartbar-oc", collection = "orders")
public class Order extends ReactivePanacheMongoEntity {

	public String tableId;

	@BsonProperty("positions")
	public List<OrderPosition> orderPositions;
}
