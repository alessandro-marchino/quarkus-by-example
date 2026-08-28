package de.schulte.smartbar.backoffice.messaging;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(@JsonProperty("orderPositions") List<OrderPosition> positions) {

}
