package chat.Main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Program {
//    private static String db_url = "jdbc:postgresql://localhost:5432/s21db";
//    private static String db_login = "s21user";
//    private static String db_passwd = "s21user";

    private static String db_url = null;
    private static String db_login = null;
    private static String db_passwd = null;



    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(db_url, db_login, db_passwd)){

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
