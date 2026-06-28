package de.schulte.smartbar.backoffice.articles;

import java.util.List;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import io.smallrye.common.annotation.NonBlocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@ResourceProperties(hal = true)
@NonBlocking
public interface ArticlesResource extends PanacheEntityResource<Article, Long> {

    @GET
    @Path("/name")
    @Produces({ MediaType.APPLICATION_JSON })
    default Uni<List<PanacheEntityBase>> getByNameContaining(@QueryParam("s") String fragment) {
        return Article.list("#Article.nameContaining", fragment);
    }
}
