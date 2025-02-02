package ru.ilezzov.iMSit.event;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.util.Vector;

public class PlayerGetUpEvent implements Listener {

    @EventHandler
    public void onEntityDismount(EntityDismountEvent event) {
        if (event.getEntity() instanceof Player) {
            Entity entity = event.getDismounted();
            Player player = (Player) event.getEntity();
            if (entity.getScoreboardTags().contains("imsit_entity")) {
                entity.remove();
                player.setVelocity(new Vector(0, 0.5, 0));
            }
        }
    }
}
