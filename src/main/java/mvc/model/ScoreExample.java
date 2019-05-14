package mvc.model;

import com.google.inject.Guice;
import com.google.inject.Injector;

import java.time.Duration;

public class ScoreExample {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new PersistenceModule("jpa-persistence-unit-1"));
        ScoreDao scoreResultDao = injector.getInstance(ScoreDao.class);
        Score scoreResult = new Score("tuba",Duration.ofMinutes(3),Level.HARD.name);
        scoreResultDao.persist(scoreResult);
        System.out.println(scoreResult);
        System.out.println(scoreResultDao.findBest(5));
    }
}
