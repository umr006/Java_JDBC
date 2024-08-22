package edu.school21.chat.Chat;

import java.util.List;
import java.util.Objects;
import edu.school21.chat.Chat.Message;

public class Chatroom {
    private Long id;
    private String name;
    private Long owner_id;
    private List<Message> messages;

    public Chatroom(long id, String name, long ownerId, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner_id = ownerId;
        this.messages = messages;
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

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return Objects.equals(id, chatroom.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner_id=" + owner_id +
                ", messages=" + messages +
                '}';
    }
}
