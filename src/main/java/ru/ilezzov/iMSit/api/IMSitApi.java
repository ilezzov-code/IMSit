package ru.ilezzov.iMSit.api;

import org.bukkit.entity.Player;
import ru.ilezzov.iMSit.manager.LayManager;
import ru.ilezzov.iMSit.manager.SitManager;

public class IMSitApi {
    public static void createSit(Player player) {
        SitManager.createSit(player);
    }

    public static void createLay(Player player) {
        LayManager.createLay(player);
    }
}
