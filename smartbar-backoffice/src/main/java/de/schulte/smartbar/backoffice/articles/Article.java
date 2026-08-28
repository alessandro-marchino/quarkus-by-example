package de.schulte.smartbar.backoffice.articles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import de.schulte.smartbar.backoffice.BaseEntity;
import de.schulte.smartbar.backoffice.MasterdataService;
import de.schulte.smartbar.backoffice.categories.Category;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@jakarta.persistence.Table(name = "sbo_article", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "name", "category_id" })
})
@NamedQuery(name = "Article.byCategory", query = "FROM Article WHERE category.id = :id ORDER BY price DESC")
@NamedQuery(name = "Article.nameContaining", query = "FROM Article WHERE name LIKE CONCAT('%', CONCAT(?1, '%'))")
public class Article extends BaseEntity {

	@NotNull
	public String name;
	@NotNull
	@Positive
	public BigDecimal price;
	@NotNull
	public String description;
	@NotNull
	public String pictureBase64;

	public Integer timesOrdered;
	public LocalDateTime lastOrdered;

	@ManyToOne
	@JoinColumn(name = "category_id")
	public Category category;

	@SuppressWarnings("null")
	@PostPersist
	@PostUpdate
	@PostRemove
	public void fireChangedEvent() {
		CDI.current().select(MasterdataService.class).get().fireEventChangedEvent(this);
	}
}
