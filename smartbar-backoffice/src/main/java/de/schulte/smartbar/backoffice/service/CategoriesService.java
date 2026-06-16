package de.schulte.smartbar.backoffice.service;

import de.schulte.smartbar.backoffice.entity.Category;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class CategoriesService extends CrudService<Category> {

    public CategoriesService() {
        // Just for CDI requirements
        super(null);
    }

    @Inject
    public CategoriesService(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Category> getEntityClass() {
        return Category.class;
    }
}
