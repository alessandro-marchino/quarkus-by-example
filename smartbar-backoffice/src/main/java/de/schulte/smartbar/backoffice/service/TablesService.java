package de.schulte.smartbar.backoffice.service;

import de.schulte.smartbar.backoffice.entity.Table;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

@ApplicationScoped
public class TablesService extends CrudService<Table> {

    public TablesService() {
        // Just for CDI requirements
        super(null);
    }

    /**
     * @param entityManager
     */
    @Inject
    public TablesService(EntityManager entityManager) {
        super(entityManager);
    }

    public Table get() {
        Table table = new Table();
        table.setName("Chile");
        return table;
    }
}
