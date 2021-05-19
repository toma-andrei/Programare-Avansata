package entities;

import javax.persistence.*;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @Column(name = "id_song")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSong;

    @Column(name = "name")
    private String name;

    public void setIdSong(Integer idSong) {
        this.idSong = idSong;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdSong() {
        return idSong;
    }

    public String getName() {
        return name;
    }
}
