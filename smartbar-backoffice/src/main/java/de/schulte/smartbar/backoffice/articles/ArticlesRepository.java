package de.schulte.smartbar.backoffice.articles;

import java.util.List;

import de.schulte.smartbar.backoffice.categories.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ArticlesRepository implements PanacheRepositoryBase<Article, Long> {

	public List<Article> listAllInCategory(final Category category) {
		return list("category", Sort.by("price", Sort.Direction.Descending), category);
	}
}
