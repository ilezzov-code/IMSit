package ru.ilezzov.iMSit.manager;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import ru.ilezzov.iMSit.IMSit;

public class SitManager {
    public static void createSit(Player player) {
        Location location = player. getLocation();
        ArmorStand armorStand = player.getWorld().spawn(location, ArmorStand.class);

        armorStand.setVisible(false);
        armorStand.setGravity(false);
        armorStand.setSmall(true);
        armorStand.setBasePlate(false);
        armorStand.setArms(false);
        armorStand.setInvulnerable(true);
        armorStand.setMarker(true);
        armorStand.addScoreboardTag("imsit_entity");

        //TODO: createSit in Database

        armorStand.addPassenger(player);
    }
}
