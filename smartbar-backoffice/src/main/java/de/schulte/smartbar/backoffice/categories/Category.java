package de.schulte.smartbar.backoffice.categories;

import de.schulte.smartbar.backoffice.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "sbo_category", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name" })
})
public class Category extends BaseEntity {

    @NotNull
    private String name;
    @NotNull
    private String description;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}