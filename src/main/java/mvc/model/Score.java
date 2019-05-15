package mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;

/**
 * Egy adott játékos által játszott játék eredményét reprezentáló osztály.
 */
@Data
@AllArgsConstructor
@Entity
public class Score {

    /**
     * Az eredmény egyedi azonosítója.
     */
    @Id
    @GeneratedValue
    private long id;

    /**
     * Az eredményhez tartozó játékos neve.
     */
    @Column(nullable = false)
    private String player;

    /**
     * Az eredményhez tartozó játék időtartama.
     */
    @Column(nullable = false)
    private Duration duration;

    /**
     * Az eredményhez tartozó játék nehézségi szintje.
     */
    @Column(name = "level")
    private String level;

    /**
     * Az eredményhez tartozó mentés időtartama.
     */
    @Column(nullable = false)
    private ZonedDateTime created;

    /**
     * Paraméter nélküli konstruktor egy Score objektum létrehozásához.
     */
    public Score() {
    }

    /**
     * Három paraméteres konstruktor egy Score objektum létrehozásához.
     *
     * @param player
     *            az eredményhez tartozó játékos neve
     * @param duration
     *            az eredményhez tartozó játék időtartama
     * @param level
     *            az eredményhez tartozó játék nehézségi szintje
     */
    public Score(String player, Duration duration, String level) {
        this.player = player;
        this.duration = duration;
        this.level = level;
    }

    /**
     * Visszaadja az eredmény egyedi azonosítóját.
     *
     * @return az eredmény egyedi azonosítója
     */
    public long getId() {
        return id;
    }

    /**
     * Beállítja az eredmény egyedi azonosítóját.
     *
     * @param id
     *            az eredmény egyedi azonosítója
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Visszaadja az eredményhez tartozó játékos nevét.
     *
     * @return az eredményhez tartozó játékos neve
     */
    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    /**
     * Visszaadja az eredményhez tartozó játék időtartamát.
     *
     * @return az eredményhez tartozó játék időtartama
     */
    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    /**
     * Visszaadja az eredményhez tartozó játék nehézségi szintjét.
     *
     * @return az eredményhez tartozó játék nehézségi szintje
     */
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * Visszaadja az eredményhez tartozó mentés időpontját.
     *
     * @return az eredményhez tartozó mentés időpontja
     */
    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @PrePersist
    protected void onPersist() {
        created = ZonedDateTime.now();
    }
}
