package edu.school21.chat.Main;

import java.sql.*;
import java.time.LocalDateTime;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;

public class Program {

    public static void main(String[] args) {
        try {
            MessagesRepositoryJdbcImpl mesRepCon = new MessagesRepositoryJdbcImpl(ConnectionDB.connectToDb());
            long id = 6;
            Message message = new Message(7L, 1L, 1L, "id 7 Update Message", LocalDateTime.now());
            mesRepCon.updateMessage(message);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
