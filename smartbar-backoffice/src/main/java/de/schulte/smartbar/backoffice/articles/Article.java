package de.schulte.smartbar.backoffice.articles;

import java.math.BigDecimal;

import de.schulte.smartbar.backoffice.BaseEntity;
import de.schulte.smartbar.backoffice.categories.Category;
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
public class Article extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @NotNull
    private String description;
    @NotNull
    private String pictureBase64;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureBase64() {
        return pictureBase64;
    }

    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}