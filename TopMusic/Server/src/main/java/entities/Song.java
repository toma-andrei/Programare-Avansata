package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "songs")
@NamedQueries({@NamedQuery(name = "Song.findById", query = "Select s from Song s where s.id=:id")})
public class Song {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "artists")
    private String artists;

    @Column(name = "link")
    private String link;

    @Column(name = "addedBy")
    private Integer addedBy;

    @Column
    private Integer votes;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_song")
    private List<Genre> genreList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_song")
    private List<Artist> artistList = new ArrayList<>();


    public void addGen(Genre gen) {
        genreList.add(gen);
        gen.setSong(this);
    }

    public void addArtist(Artist artist) {
        artistList.add(artist);
    }

    public String getName() {
        return name;
    }

    public Integer getAddedBy() {
        return addedBy;
    }

    public Integer getId() {
        return id;
    }

    public String getArtists() {
        return artists;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public Integer getVotes() {
        return votes;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddedBy(Integer addedBy) {
        this.addedBy = addedBy;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setGenreList(List<Genre> genreList) {
        this.genreList = genreList;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }
}
