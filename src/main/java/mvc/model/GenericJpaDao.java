package mvc.model;

import com.google.inject.persist.Transactional;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Generikus JPA DAO osztály, amely JPA támogatást nyújt a megadott entitás osztály számára.
 *
 * @param <T> az entitás osztály típusa
 */
public abstract class GenericJpaDao<T> {

    protected Class<T> entityClass;
    protected EntityManager entityManager;

    /**
     * Konstruktor egy {@code GenericJpaDao} objektum létrehozásához.
     *
     * @param entityClass az {@link Class} objektumot reprezentáló entitás osztály
     */
    public GenericJpaDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Visszaadja az alapul szolgáló {@link EntityManager} példányt.
     *
     * @return az alapul szolgáló {@link EntityManager} példány
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Beállítja az alapul szolgáló {@link EntityManager} példányt.
     *
     * @param entityManager az alapul szolgáló {@link EntityManager} példány
     */
    @Inject
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Megtartja a megadott entitás példányt az adatbázisba.
     *
     * @param entity az entitás példány, amit megtartott az adatbázis
     */
    @Transactional
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Visszaadja az entitás példányt az elsődleges kulcsával az adatbázisból.
     * A metódus egy üres {@link Optional} objektumot ad vissza, ha a példány nem létezik.
     *
     * @param primaryKey a keresett elsődleges kulcs
     * @return egy {@link Optional} objektum, amely az entitás példányt a megadott elsődleges kulcshoz csomagolja
     */
    @Transactional
    public Optional<T> find(Object primaryKey) {
        return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
    }

    /**
     * Visszaadja az összes entitás osztály példányának listáját az adatbázisból.
     *
     * @return az összes entitás osztály példányának listája az adatbázsból
     */
    @Transactional
    public List<T> findAll() {
        TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
        return typedQuery.getResultList();
    }

    /**
     * Törli a megadott entitás példányt az adatbázisból.
     *
     * @param entity az adatbázisból törlendő entitás példány
     */
    @Transactional
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Frissíti a megadott entitás példányt az adatbázisban.
     *
     * @param entity az adatbázisban frissítendő entitás példány
     */
    @Transactional
    public void update(T entity) {
        entityManager.merge(entity);
    }

}
