package ru.ilezzov.iMSit.maanger;

import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.config.PluginFile;
import ru.ilezzov.iMSit.model.Error;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBmanager {
    private final IMSit instance = IMSit.getInstance();
    private final PluginFile databaseFile = IMSit.getDatabaseFile();

    private final String HOST = databaseFile.getString("Database.host");
    private final String PORT = databaseFile.getString("Database.port");
    private final String DATABASE = databaseFile.getString("Database.database");
    private final String USER = databaseFile.getString("Database.user");
    private final String PASSWORD = databaseFile.getString("Database.password");

    private Connection connection;
    private String type = databaseFile.getString("Database.type");



    public DBmanager() {
        try {
            connection = createConnection(HOST, PORT, DATABASE, USER, PASSWORD);
        } catch (SQLException e) {
            }
    }

    public Connection getConnection() {
        return connection;
    }

    public Error reconnect() {
        switch (type.toLowerCase()) {
            case "mysql" -> {
                try {
                    connection = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?user=" + USER + "&password=" + PASSWORD + "&characterEncoding=UTF-8&autoReconnect=true&connectTimeout=5000");
                } catch (SQLException e) {
                    return new Error(e);
                }
            }
            default -> {
                try {
                    connection = DriverManager.getConnection("jdbc:sqlite:" + new File(instance.getDataFolder(), "data/database.db").getPath());
                } catch (SQLException e) {
                    return new Error(e);
                }
            }
        }
        return new Error();
    }

    private Connection createConnection(String host, String port, String database, String user, String password) throws SQLException {
        switch (type.toLowerCase()) {
            case "mysql" -> {
                return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + user + "&password=" + password + "&characterEncoding=UTF-8&autoReconnect=true");
            }
            default -> {
                type = "sqlite";
                return DriverManager.getConnection("jdbc:sqlite:" + new File(instance.getDataFolder(), "data/database.db").getPath());
            }
        }
    }

    private Statement getStatement() throws SQLException {
        return connection.createStatement();
    }

    private void checkTables(String type) throws SQLException {
         //TODO: checkTabled
    }
}
