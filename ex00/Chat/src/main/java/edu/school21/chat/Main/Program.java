package edu.school21.chat.Chat;
import java.sql.*;
import java.util.ArrayList;


public class Program {
//    private static String db_url = "jdbc:postgresql://localhost:5432/s21db";
//    private static String db_login = "s21user";
//    private static String db_passwd = "s21user";

    private static String db_url = GetDbProperties.getProperties("db.url");
    private static String db_login = GetDbProperties.getProperties("db.login");;
    private static String db_passwd = GetDbProperties.getProperties("db.password");;
    private static final String query = "select * from s21_chat.message";


    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(db_url, db_login, db_passwd)){
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("message_text") + " ---- id ---- " + resultSet.getString("id"));
            }
            resultSet.close();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }
    }

}
