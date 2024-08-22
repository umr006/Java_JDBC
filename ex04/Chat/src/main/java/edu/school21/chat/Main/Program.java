package edu.school21.chat.Main;

import java.sql.*;
import java.time.LocalDateTime;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;

public class Program {

    public static void main(String[] args) {
        try {
            MessagesRepositoryJdbcImpl mesRepCon = new MessagesRepositoryJdbcImpl(ConnectionDB.connectToDb());
            System.out.println(mesRepCon.findAll(2,2));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
