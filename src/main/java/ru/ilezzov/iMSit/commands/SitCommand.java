package ru.ilezzov.iMSit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.api.IMSitApi;
import ru.ilezzov.iMSit.message.PluginMessages;

public class SitCommand implements CommandExecutor {

    private PluginMessages pluginMessages = IMSit.pluginMessages();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage(pluginMessages.onlyUser());
            return true;
        }

        if(!commandSender.hasPermission("imsit.sit")) {
            commandSender.sendMessage(pluginMessages.noHasPerms());
            return true;
        }

        Player p = ((Player) commandSender).getPlayer();

        if(!p.isOnGround()) {
            p.sendMessage(pluginMessages.commandSitGroundError());
            return true;
        }

        IMSitApi.createSit(p);

        p.sendMessage(pluginMessages.commandSitEnableChat());
        p.sendActionBar(pluginMessages.commandSitEnableActionBar());

        return true;
    }
}
