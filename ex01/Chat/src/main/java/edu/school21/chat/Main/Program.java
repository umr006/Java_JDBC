package edu.school21.chat.Main;

import java.sql.*;
import java.util.ArrayList;
import edu.school21.chat.Chat.Chatroom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;
import edu.school21.chat.Repository.MessagesRepositoryJdbcImpl;

public class Program {

    public static void main(String[] args) {
        try {
            MessagesRepositoryJdbcImpl mesRepCon = new MessagesRepositoryJdbcImpl(ConnectionDB.connectToDb());
            long id = 1;
            mesRepCon.findById(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
