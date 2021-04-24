package entities;

import javax.persistence.*;

@Entity
@Table(name = "genres")
@NamedQueries({@NamedQuery(name = "Genre.findByName", query = "Select g from Genre g where g.name=:name"),
               @NamedQuery(name = "Genre.findAll", query = "Select g from Genre g")})

public class Genre {
    @Id
    @Column(name = "id")
    @Basic(optional = false)
    int id;

    @Column(name = "name")
    @Basic(optional = false)
    String name;

    public Genre() {

    }

    public Genre(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
