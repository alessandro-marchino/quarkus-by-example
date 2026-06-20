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
@NamedQuery(name = "Article.byCategory", query = "FROM Article WHERE category = ?1 ORDER BY price DESC")
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

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the pictureBase64
     */
    public String getPictureBase64() {
        return pictureBase64;
    }

    /**
     * @param pictureBase64 the pictureBase64 to set
     */
    public void setPictureBase64(String pictureBase64) {
        this.pictureBase64 = pictureBase64;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(Category category) {
        this.category = category;
    }

}