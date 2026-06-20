package de.schulte.smartbar.backoffice.articles;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import de.schulte.smartbar.backoffice.api.ArticlesApi;
import de.schulte.smartbar.backoffice.api.model.ApiArticle;
import de.schulte.smartbar.backoffice.categories.Category;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

@Transactional
public class ArticlesResource implements ArticlesApi {

    private final ArticlesRepository repository;
    private final ArticleMapper mapper;

    @Inject
    public ArticlesResource(ArticlesRepository repository, ArticleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Response articlesArticleIdDelete(Long articleId) {
         final Optional<Article> article = repository.findByIdOptional(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.delete(article.get());
        return Response.ok().build();
    }

    @Override
    public Response articlesArticleIdGet(Long articleId) {
        final Optional<Article> article = repository.findByIdOptional(articleId);
        if (article.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapper.mapToApiArticle(article.get())).build();
    }

    @Override
    public Response articlesArticleIdPut(Long articleId, @Valid ApiArticle apiArticle) {
        final Optional<Article> existingArticle = repository.findByIdOptional(articleId);
        if (existingArticle.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = existingArticle.get();
        mapper.mapToArticle(apiArticle, article);
        return Response.ok().build();
    }

    @Override
    public Response articlesGet() {
        final List<Article> list = repository.listAll();
        return Response.ok(list.stream().map(mapper::mapToApiArticle).toList()).build();
    }

    @Override
    public Response articlesPost(@NotNull Long xCategoryId, @Valid ApiArticle apiArticle) {
        final Optional<Category> category = Category.findByIdOptional(xCategoryId);
        if(category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Article article = new Article();
        mapper.mapToArticle(apiArticle, article);
        article. setCategory(category.get());
        repository.persist(article);
        return Response.created(URI.create("/articles/" + article.getId())).build();
    }

    @GET
    @Path("/category/{categoryId}")
    @Produces({ "application/json" })
    public Response listByCategory(@PathParam("categoryId") Long categoryId) {
        final Optional<Category> category = Category.findByIdOptional(categoryId);
        if(category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final List<Article> articles = repository.listByCategoryNamedQuery(category.get());
        return Response.ok(articles.stream().map(mapper::mapToApiArticle).toList()).build();
    }
}
