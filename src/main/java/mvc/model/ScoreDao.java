package mvc.model;

import com.google.inject.persist.Transactional;

import java.util.List;


public class ScoreDao extends GenericJpaDao<Score> {

    public ScoreDao() {
        super(Score.class);
    }

    @Transactional
    public List<Score> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM Score r ORDER BY r.duration ASC, r.level ASC", Score.class)
                .setMaxResults(n)
                .getResultList();
    }
}