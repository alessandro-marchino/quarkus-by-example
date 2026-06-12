package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

import de.schulte.smartbar.backoffice.api.model.Article;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class ArticlesResource implements ArticlesApi {

    @Override
    public Response articlesArticleIdDelete(String articleId) {
        return Response.ok().build();
    }

    @Override
    public Response articlesArticleIdGet(String articleId) {
        return Response.ok(new Article().name("Chardonnay")).build();
    }

    @Override
    public Response articlesArticleIdPut(String articleId, @Valid Article article) {
        return Response.ok().build();
    }

    @Override
    public Response articlesGet() {
        return Response.ok(List.of(new Article().name("Chardonnay"))).build();
    }

    @Override
    public Response articlesPost(@Valid Article article) {
        return Response.created(URI.create("todo")).build();
    }

}
