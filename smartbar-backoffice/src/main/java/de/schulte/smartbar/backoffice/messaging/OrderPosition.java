package de.schulte.smartbar.backoffice.messaging;

import java.math.BigDecimal;

public record OrderPosition(Long articleId, Integer quantity, BigDecimal price) {

}
