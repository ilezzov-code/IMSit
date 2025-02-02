package ru.ilezzov.iMSit.database;

import org.bukkit.entity.Player;
import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.model.IMSitPlayer;

import java.sql.*;
import java.util.UUID;

public class DBMethods {
    private static final Statement STATEMENT = IMSit.getdBmanager().getStatement();
    private static final Connection CONNECTION = IMSit.getdBmanager().getConnection();

    public static IMSitPlayer getPlayer(UUID uuid) {
        if(!checkPlayer(uuid)) {
            newPlayer(uuid);
        }
        return getPlayerByUUID(uuid);
    }

    private static boolean checkPlayer(UUID uuid) {
        try {
            ResultSet resultSet = STATEMENT.executeQuery("SELECT COUNT(*) FROM players WHERE uuid = " + uuid);
            return resultSet.getInt(1) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static IMSitPlayer newPlayer(UUID uuid) {
        try {
            PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO players VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setBoolean(2, true);
            preparedStatement.setBoolean(3, true);
            preparedStatement.setBoolean(4, true);

            preparedStatement.execute();

            return new IMSitPlayer(uuid, true, true, true, true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static IMSitPlayer getPlayerByUUID(UUID uuid) {
        //TODO Hibernate
        return null;
    }

}
