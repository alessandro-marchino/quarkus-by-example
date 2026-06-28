package de.schulte.smartbar.backoffice.categories;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.smallrye.common.annotation.NonBlocking;

@NonBlocking
public interface CategoriesResource extends PanacheEntityResource<Category, Long> {
    // Empty
}
