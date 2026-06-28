package de.schulte.smartbar.backoffice.articles;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(hal = true)
public interface ArticlesResource extends PanacheEntityResource<Article, Long> {
    // Empty
}
