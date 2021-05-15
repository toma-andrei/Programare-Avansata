package entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "User.findById", query = "Select u from User u where u.id=:id"),
               @NamedQuery(name = "User.findByUsername", query ="Select u from User u where u.username=:username")})
public class User {

    public User(){
        addComment = 1;
        addSong = 1;
        admin = 0;
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = true)
    @Column(name = "addSong")
    private Integer addSong;

    @Basic(optional = true)
    @Column(name = "addComment")
    private Integer addComment;

    @Basic(optional = true)
    @Column(name = "admin")
    private Integer admin;

    public Integer getAddComment() {
        return addComment;
    }

    public Integer getAddSong() {
        return addSong;
    }

    public Integer getAdmin() {
        return admin;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAddComment(Integer addComment) {
        this.addComment = addComment;
    }

    public void setAddSong(Integer addSong) {
        this.addSong = addSong;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
