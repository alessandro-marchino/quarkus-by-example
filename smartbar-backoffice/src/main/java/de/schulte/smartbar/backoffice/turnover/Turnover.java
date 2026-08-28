package de.schulte.smartbar.backoffice.turnover;

import java.math.BigDecimal;

import de.schulte.smartbar.backoffice.BaseEntity;
import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "sbo_turnover")
public class Turnover extends BaseEntity {

	public Long articleId;
	public Integer quantity;
	public BigDecimal turnoverTotalValue;
}
