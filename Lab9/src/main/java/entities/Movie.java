package entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movies")
@NamedQueries({@NamedQuery(name = "Movie.findByName", query = "Select m from Movie m where m.title=:name")})

public class Movie {

    public Movie(Integer id, String title, Date releaseDate, Integer duration, Float score) {
        this.id = id;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.score = score;
        this.title = title;
    }

    public Movie(){

    }

    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "title")
    private String title;

    @Basic(optional = false)
    @Column(name = "release_date")
    private Date releaseDate;

    @Basic(optional = false)
    @Column(name = "duration")
    private Integer duration;

    @Basic(optional = false)
    @Column(name = "score")
    private Float score;

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Float getScore() {
        return score;
    }

    public Integer getDuration() {
        return duration;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setScore(Float score) {
        this.score = score;
    }

}
