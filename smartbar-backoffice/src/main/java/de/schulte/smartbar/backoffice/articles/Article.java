package de.schulte.smartbar.backoffice.articles;

import java.math.BigDecimal;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@jakarta.persistence.Table(name = "sbo_article", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name", "category_id" })
})
@NamedQuery(name = "Article.byCategory", query = "FROM Article WHERE category.id = :id ORDER BY price DESC")
@NamedQuery(name = "Article.nameContaining", query = "FROM Article WHERE name LIKE CONCAT('%', CONCAT(?1, '%'))")
public class Article extends PanacheEntity {

    @NotNull
    public String name;
    @NotNull
    @Positive
    public BigDecimal price;
    @NotNull
    public String description;
    @NotNull
    public String pictureBase64;

    @ManyToOne
    @JoinColumn(name = "category_id")
    public Category category;

}