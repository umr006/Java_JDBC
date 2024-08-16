package chat.Chat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import edu.school21.chat.Chat.Chatroom;

public class User {
    private Long id;
    private String name;
    private String passwd;
    private List<Chatroom> created_rooms;
    private List<Chatroom> socializeRooms;

    public User(Long id, String name, String passwd, List<Chatroom> created_rooms, List<Chatroom> socializeRooms) {
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

    public List<Chatroom> getCreated_rooms() {
        return created_rooms;
    }

    public void setCreated_rooms(List<Chatroom> created_rooms) {
        this.created_rooms = created_rooms;
    }

    public List<Chatroom> getSocializeRooms() {
        return socializeRooms;
    }

    public void setSocializeRooms(List<Chatroom> socializeRooms) {
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
