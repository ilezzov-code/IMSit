package ru.ilezzov.iMSit.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityMountEvent;

public class PlayerSatDownEvent implements Listener {
    @EventHandler
    public void onEntityMount(EntityMountEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            player.sendActionBar("");
        }
    }
}
