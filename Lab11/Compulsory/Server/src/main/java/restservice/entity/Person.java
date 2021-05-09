package restservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * use javalab;
 * CREATE TABLE persons(id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20));
 * CREATE TABLE friends(idPerson INT, idFriend INT, FOREIGN KEY (idFriend) REFERENCES persons(id));
 * CREATE TABLE messages (senderId int, message text, FOREIGN KEY (senderId) REFERENCES persons(id));
 */
@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
