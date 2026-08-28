package de.schulte.smartbar.backoffice.articles;

import java.util.List;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheRepositoryResource;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

public interface ArticlesResource extends PanacheRepositoryResource<ArticlesRepository, Article, Long> {

	@SuppressWarnings("null")
	@GET
	@Path("/name")
	@Produces({ MediaType.APPLICATION_JSON })
	default List<Article> getByNameContaining(@QueryParam("s") String fragment) {
		return CDI.current().select(ArticlesRepository.class).get().list("#Article.nameContaining", fragment);
	}
}
