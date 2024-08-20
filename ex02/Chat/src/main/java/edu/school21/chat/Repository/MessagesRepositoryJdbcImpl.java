package edu.school21.chat.Repository;

import edu.school21.chat.Chat.Chatroom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;
import edu.school21.chat.Exception.NotSavedSubEntityException;

import javax.sql.DataSource;
import java.sql.*;
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

    @Override
    public void saveMessage(Message message) throws NotSavedSubEntityException, SQLException {
        String quary = "insert into s21_chat.message values (DEFAULT, ?, ?, ?, ?)";
        checkMessage(message);
        PreparedStatement preparedStatement = connection.prepareStatement(quary);
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("select * from user where id = " + message.getAuthor_id());
        if(!resultSet.next()) {
            throw new NotSavedSubEntityException("Author id doesn't exists");
        }
        resultSet = statement.executeQuery("select * from user where id = " + message.getRoom_id());
        if(!resultSet.next()) {
            throw new NotSavedSubEntityException("Room id doesn't exists");
        }
        preparedStatement.setLong(1, message.getAuthor_id());
        preparedStatement.setLong(2, message.getRoom_id());
        preparedStatement.setString(3, message.getMessage_text());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getData_time()));
        preparedStatement.executeUpdate();
    }

    public void checkMessage(Message message) {
        if (message.getAuthor_id() == null || message.getRoom_id() == null) {
            throw new NotSavedSubEntityException("Author_id or Room_id can't not be NULL");
        } else if (message.getMessage_text() == null || message.getMessage_text().length() < 1) {
            throw new NotSavedSubEntityException("Message_text can't not be NULL or empty");
        } else if (message.getData_time() == null) {
            throw new NotSavedSubEntityException("Data_time can't not be NULL");
        }
    }
}