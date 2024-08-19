package edu.school21.chat.Repository;

import edu.school21.chat.Chat.Chatroom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private static Connection connection;

    public  MessagesRepositoryJdbcImpl(DataSource dataSource) throws SQLException {
        MessagesRepositoryJdbcImpl.connection = dataSource.getConnection();
    }

    @Override
    public Optional<Message> findById(Long id) throws SQLException {

        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from s21_chat.message where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("id") + " " + resultSet.getString("message_text"));
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }


        return Optional.empty();
    }

}