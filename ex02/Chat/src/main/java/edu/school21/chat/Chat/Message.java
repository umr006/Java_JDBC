package edu.school21.chat.Chat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private Long id;
    private Long author_id;
    private Long room_id;
    private String message_text;
    private LocalDateTime data_time;

    public Message(Long id, Long author_id, Long room_id, String message_text, LocalDateTime data_time) {
        this.id = id;
        this.author_id = author_id;
        this.room_id = room_id;
        this.message_text = message_text;
        this.data_time = data_time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public String getMessage_text() {
        return message_text;
    }

    public void setMessage_text(String message_text) {
        this.message_text = message_text;
    }

    public LocalDateTime getData_time() {
        return data_time;
    }

    public void setData_time(LocalDateTime data_time) {
        this.data_time = data_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
