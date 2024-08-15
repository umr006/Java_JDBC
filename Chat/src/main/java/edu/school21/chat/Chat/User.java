package edu.school21.chat.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String name;
    private String passwd;
    private List<ChatRoom> created_rooms;
    private List<ChatRoom> socializeRooms;

    public User(Long id, String name, String passwd, List<ChatRoom> created_rooms, List<ChatRoom> socializeRooms) {
        this.id = id;
        this.name = name;
        this.passwd = passwd;
        this.created_rooms = created_rooms;
        this.socializeRooms = socializeRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public List<ChatRoom> getCreated_rooms() {
        return created_rooms;
    }

    public void setCreated_rooms(List<ChatRoom> created_rooms) {
        this.created_rooms = created_rooms;
    }

    public List<ChatRoom> getSocializeRooms() {
        return socializeRooms;
    }

    public void setSocializeRooms(List<ChatRoom> socializeRooms) {
        this.socializeRooms = socializeRooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", created_rooms=" + created_rooms +
                ", socializeRooms=" + socializeRooms +
                '}';
    }
}
