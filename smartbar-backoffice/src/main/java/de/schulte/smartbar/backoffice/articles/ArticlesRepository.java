package de.schulte.smartbar.backoffice.articles;

import java.util.List;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticlesRepository implements PanacheRepository<Article> {

    public List<Article> listByCategory(Category category) {
        return find("category", Sort.by("price", Sort.Direction.Descending), category)
            .page(Page.ofSize(3))
            .list();
    }
    public List<Article> listByCategoryNamedQuery(Category category) {
        return find("#Article.byCategory", category)
            .page(Page.ofSize(3))
            .list();
    }
}
