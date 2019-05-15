package mvc.model;

import com.google.inject.persist.Transactional;

import java.util.List;

/**
 * DAO osztály a {@link Score} entitáshoz.
 */
public class ScoreDao extends GenericJpaDao<Score> {

    public ScoreDao() {
        super(Score.class);
    }

    /**
     * Visszaadja a legjobb {@code n} eredmény listáját az aknakereső kirakási idejének
     * és nehézségi szintjének függvényében.
     *
     * @param n a visszaadandó eredményke száma
     * @return a legjobb {@code n} eredmény listáját az aknakereső kirakási idejének
     * és nehézségi szintjének függvényében
     */
    @Transactional
    public List<Score> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM Score r ORDER BY r.duration ASC, r.level ASC", Score.class)
                .setMaxResults(n)
                .getResultList();
    }
}