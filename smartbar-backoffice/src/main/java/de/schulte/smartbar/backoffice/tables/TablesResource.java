package de.schulte.smartbar.backoffice.tables;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.smallrye.common.annotation.NonBlocking;

@NonBlocking
public interface TablesResource extends PanacheRepositoryResource<TablesRepository, Table, Long> {
    // Empty?
}
