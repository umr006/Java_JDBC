package edu.school21.chat.Main;

import java.sql.*;
import java.time.LocalDateTime;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;

public class Program {

    public static void main(String[] args) {
        try {
            MessagesRepositoryJdbcImpl mesRepCon = new MessagesRepositoryJdbcImpl(ConnectionDB.connectToDb());
            long id = 1;
            mesRepCon.findById(id);
            Message mes1 = new Message(1L, 2L, 1L, "Hello! It is new message", LocalDateTime.now());
            mesRepCon.saveMessage(mes1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
