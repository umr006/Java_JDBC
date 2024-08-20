package edu.school21.chat.Main;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class ConnectionDB {
    private static String db_url = GetDbProperties.getProperties("db.url");
    private static String db_login = GetDbProperties.getProperties("db.login");
    private static String db_passwd = GetDbProperties.getProperties("db.password");

    public static DataSource connectToDb() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(db_url);
        hikariConfig.setUsername(db_login);
        hikariConfig.setPassword(db_passwd);
        return new HikariDataSource(hikariConfig);
    }



}
