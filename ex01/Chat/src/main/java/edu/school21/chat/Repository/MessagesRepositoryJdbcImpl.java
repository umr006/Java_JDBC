package edu.school21.chat.Repository;

import edu.school21.chat.Chat.Chatroom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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

            String message_id = resultSet.getString("id");
            Long userid = resultSet.getLong("message_author");
            Long roomid = resultSet.getLong("room_id");
            String message = resultSet.getString("message_text");
            LocalDateTime dateTime = resultSet.getObject("date_time", LocalDateTime.class);

            System.out.println("Message: {\n" + "id=" + message_id);
            findByAuthorId(userid);
            findByRoomId(roomid);
            System.out.println(resultSet.getString("message_text"));
            System.out.println(dateTime + "\n}");
            //return new Message(id, userid, roomid, message, (String)dateTime);
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
        return Optional.empty();
    }

    private void findByAuthorId(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from s21_chat.user where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("id=" + resultSet.getString("id")  +",login" + resultSet.getString("login") + ",password=" + resultSet.getString("passwd") + ",CreatedRooms=null,rooms=null");
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    private void findByRoomId(Long id) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from s21_chat.chatroom where id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            System.out.println("id=" + resultSet.getString("id")  +",name" + resultSet.getString("chatroom_name") + ",creater=" + resultSet.getString("chatroom_owner_id") + ",messages=null");
        } catch (SQLException e) {
            System.out.println(e.fillInStackTrace());
        }
    }
}