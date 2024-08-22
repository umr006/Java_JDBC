package edu.school21.chat.Repository;

import edu.school21.chat.Chat.Chatroom;
import edu.school21.chat.Chat.Message;
import edu.school21.chat.Chat.User;
import edu.school21.chat.Exception.NotSavedSubEntityException;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("select * from s21_chat.users where id = ?")) {
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

        ResultSet resultSet = statement.executeQuery("select * from s21_chat.users where id = " + message.getAuthor_id());
        if(!resultSet.next()) {
            throw new NotSavedSubEntityException("Author id doesn't exists");
        }
        resultSet = statement.executeQuery("select * from s21_chat.chatroom where id = " + message.getRoom_id());
        if(!resultSet.next()) {
            throw new NotSavedSubEntityException("Room id doesn't exists");
        }
        preparedStatement.setLong(1, message.getAuthor_id());
        preparedStatement.setLong(2, message.getRoom_id());
        preparedStatement.setString(3, message.getMessage_text());
        preparedStatement.setTimestamp(4, Timestamp.valueOf(message.getData_time()));
        preparedStatement.executeUpdate();
        resultSet = statement.executeQuery("select id from s21_chat.message ORDER BY id DESC LIMIT 1;");
        message.setId(resultSet.getLong(1));
    }


    public void updateMessage(Message message) {
        //String query = "UPDATE s21_chat.message set message_author ? room_id ? message_text ? data_time ? where message_id = ?";
        checkMessage(message);
        String query = String.format("UPDATE s21_chat.message set message_author = %d, room_id = %d, message_text = '%s', date_time = '%s' where id = %d;", message.getAuthor_id(), message.getRoom_id(), message.getMessage_text(), message.getData_time(), message.getId());
        System.out.println(query);
        try (Statement checkMessageId = connection.createStatement(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = checkMessageId.executeQuery("select id from s21_chat.message order by id desc limit 1");
            resultSet.next();
            if (resultSet.getLong("id") >= message.getId()) {
                preparedStatement.executeQuery();
            } else {
                throw new NotSavedSubEntityException("Message with this id not found");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> findAll(int page, int size) {
        List<User> userList = new ArrayList<>();
        String query = String.format("SELECT users.id, users.login, users.passwd, list_of_created_rooms.id, chatroom_owner_id, chatroom_id, chatroom_name\n" +
                "    from s21_chat.users AS users\n" +
                "    join s21_chat.list_of_created_rooms as list_of_created_rooms\n" +
                "    on users.id = list_of_created_rooms.chatroom_owner_id\n" +
                "    order by users.id LIMIT %d OFFSET %d;", page, page * size);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             Statement created_rooms = connection.createStatement();
             Statement socializeRooms = connection.createStatement();
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            userList.add(new User(resultSet.getLong("id"),
                    resultSet.getString("login"),
                    resultSet.getString("passwd"),
                    findCreatedRooms(created_rooms.executeQuery(query)),
                    findSocializeRooms(socializeRooms.executeQuery(query))));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public List<Chatroom> findCreatedRooms(ResultSet resultSet) throws SQLException {
        List<Chatroom> created_rooms = new ArrayList<>();
        while (resultSet.next()) {
            created_rooms.add(new Chatroom(resultSet.getLong("id"),
                    resultSet.getString("chatroom_name"),
                    resultSet.getLong("chatroom_owner_id"),
                    new ArrayList<>()));
        }
        return created_rooms;
    }

    public List<Chatroom> findSocializeRooms(ResultSet resultSet) throws SQLException {
        List<Chatroom> SocializeRooms = new ArrayList<>();
        while (resultSet.next()) {
            if (resultSet.getLong("chatroom_owner_id") == resultSet.getLong("id")) {
                SocializeRooms.add(new Chatroom(resultSet.getLong("id"),
                        resultSet.getString("chatroom_name"),
                        resultSet.getLong("chatroom_owner_id"),
                        new ArrayList<>()));
            }
        }
        return SocializeRooms;
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