package de.schulte.smartbar.backoffice.categories;

import java.net.URI;
import java.util.Optional;

import de.schulte.smartbar.backoffice.api.CategoriesApi;
import de.schulte.smartbar.backoffice.api.model.ApiCategory;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

public class CategoriesResource implements CategoriesApi {

    private final CategoriesService categoriesService;

    @Inject
    public CategoriesResource(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @Override
    public Response categoriesCategoryIdDelete(Long categoryId) {
        final Optional<Category> table = categoriesService.deleteById(categoryId);
        if (table.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().build();
    }

    @Override
    public Response categoriesCategoryIdGet(Long categoryId) {
        final Optional<Category> category = categoriesService.getById(categoryId);
        if (category.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(mapCategoryToApiCategory(category.get())).build();
    }

    @Override
    public Response categoriesCategoryIdPut(Long categoryId, @Valid ApiCategory apiCategory) {
        final Optional<Category> existingCategory = categoriesService.getById(categoryId);
        if (existingCategory.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        final Category category = existingCategory.get();
        mapApiCategoryToCategory(apiCategory, category);
        categoriesService.update(category);
        return Response.ok().build();
    }

    @Override
    public Response categoriesGet() {
        return Response.ok(categoriesService.listAll().stream().map(this::mapCategoryToApiCategory).toList()).build();
    }

    @Override
    public Response categoriesPost(@Valid ApiCategory apiCategory) {
        final Category category = new Category();
        mapApiCategoryToCategory(apiCategory, category);
        final Category persitedCategory = categoriesService.persist(category);
        return Response.created(URI.create("/categories/" + persitedCategory.getId())).build();
    }

    private void mapApiCategoryToCategory(ApiCategory apiCategory, Category category) {
        category.setName(apiCategory.getName());
        category.setDescription(apiCategory.getDescription());
    }

    private ApiCategory mapCategoryToApiCategory(Category category) {
        final ApiCategory apiCategory = new ApiCategory();
        apiCategory.setDescription(category.getDescription());
        apiCategory.setName(category.getName());
        apiCategory.setId(category.getId());
        return apiCategory;
    }

}
