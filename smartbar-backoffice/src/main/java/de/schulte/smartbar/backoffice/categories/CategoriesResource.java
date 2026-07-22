package de.schulte.smartbar.backoffice.categories;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.smallrye.common.annotation.NonBlocking;

@NonBlocking
@ResourceProperties(rolesAllowed = { "admin" })
public interface CategoriesResource extends PanacheEntityResource<Category, Long> {
    // Empty
}
