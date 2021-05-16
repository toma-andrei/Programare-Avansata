package entities;

import javax.persistence.*;

@Entity
@Table(name = "genres")
public class Genre {

    @Id
    @Column(name = "id_song")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSong;

    @Column(name = "gen_name")
    public String name;

    public String getName() {
        return name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    private Song song;

    public Integer getIdSong() {
        return idSong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIdSong(Integer idSong) {
        this.idSong = idSong;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre )) return false;
        return idSong != null && idSong.equals(((Genre) o).getIdSong());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public Song getSong() {
        return song;
    }
}
