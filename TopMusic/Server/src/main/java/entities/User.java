package entities;

import jdk.jfr.Name;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "User.findById", query = "Select u from User u where u.id=:id"),
        @NamedQuery(name = "User.findByUsername", query = "Select u from User u where u.username=:username"),
        @NamedQuery(name = "User.correctLogin", query = "Select u from User u where u.username=:username and u.password=:password")})
public class User {

    public User() {
        addComment = 1;
        addSong = 1;
        admin = 0;
        vote = 1;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name="locale")
    private String locale;

    @Basic(optional = false)
    @Column(name = "username")
    private String username;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = true)
    @Column(name = "vote")
    private Integer vote;

    @Basic(optional = true)
    @Column(name = "add_song")
    private Integer addSong;

    @Basic(optional = true)
    @Column(name = "add_comment")
    private Integer addComment;

    @Basic(optional = true)
    @Column(name = "admin")
    private Integer admin;

    public Integer getAddComment() {
        return addComment;
    }

    public String getLocale() {
        return locale;
    }

    public Integer getAddSong() {
        return addSong;
    }

    public Integer getVote() {
        return vote;
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

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
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
