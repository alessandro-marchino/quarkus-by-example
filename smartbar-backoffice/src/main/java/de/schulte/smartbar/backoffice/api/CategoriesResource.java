package de.schulte.smartbar.backoffice.api;

import java.net.URI;
import java.util.List;

import de.schulte.smartbar.backoffice.api.model.Category;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class CategoriesResource implements CategoriesApi {

    @Override
    public Response categoriesCategoryIdDelete(String categoryId) {
        return Response.ok().build();
    }

    @Override
    public Response categoriesCategoryIdGet(String categoryId) {
        return Response.ok(new Category().name("drinks")).build();
    }

    @Override
    public Response categoriesCategoryIdPut(String categoryId, @Valid Category category) {
        return Response.ok().build();
    }

    @Override
    public Response categoriesGet() {
        return Response.ok(List.of(new Category().name("drinks"))).build();
    }

    @Override
    public Response categoriesPost(@Valid Category category) {
        return Response.created(URI.create("todo")).build();
    }

}
