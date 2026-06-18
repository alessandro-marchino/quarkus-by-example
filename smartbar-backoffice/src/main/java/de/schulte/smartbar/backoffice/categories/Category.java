package de.schulte.smartbar.backoffice.categories;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@jakarta.persistence.Table(name = "sbo_category", uniqueConstraints = {
    @UniqueConstraint(columnNames = { "name" })
})
public class Category extends PanacheEntity {

    @NotNull
    public String name;
    @NotNull
    public String description;

}