package mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.Duration;
import java.time.ZonedDateTime;


@Data
@AllArgsConstructor
@Entity
public class Score {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String player;

    @Column(nullable = false)
    private Duration duration;

    @Column(name = "level")
    private String level;

    @Column(nullable = false)
    private ZonedDateTime created;

    public Score() {
    }

    public Score(String player, Duration duration, String level) {
        this.player = player;
        this.duration = duration;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

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
