package de.schulte.smartbar.backoffice.tables;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheRepositoryResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.smallrye.common.annotation.NonBlocking;

@NonBlocking
@ResourceProperties(rolesAllowed = { "admin" })
public interface TablesResource extends PanacheRepositoryResource<TablesRepository, Table, Long> {
    // Empty?
}
