package de.schulte.smartbar.backoffice.service;

import de.schulte.smartbar.backoffice.entity.Article;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class ArticlesService extends CrudService<Article> {

    public ArticlesService() {
        // Just for CDI requirements
        super(null);
    }

    @Inject
    public ArticlesService(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    protected Class<Article> getEntityClass() {
        return Article.class;
    }
}
