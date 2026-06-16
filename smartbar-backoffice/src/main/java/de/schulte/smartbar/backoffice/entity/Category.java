package de.schulte.smartbar.backoffice.entity;

import jakarta.persistence.Entity;

@Entity
@jakarta.persistence.Table(name = "sbo_category")
public class Category extends BaseEntity {

    private String name;
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