package de.schulte.smartbar.backoffice.service;

import de.schulte.smartbar.backoffice.entity.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Transactional
public abstract class CrudService<E extends BaseEntity> {
    private final EntityManager entityManager;

    protected CrudService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public E persist(E entity) {
        entityManager.persist(entity);
        return entity;
    }

}
