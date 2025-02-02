package ru.ilezzov.iMSit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.config.PluginFile;
import ru.ilezzov.iMSit.manager.DBManager;
import ru.ilezzov.iMSit.message.PluginMessages;
import ru.ilezzov.iMSit.model.Error;

public class IMSitCommand implements CommandExecutor {

    private final PluginFile config = IMSit.getConfigurationFile();
    private final PluginFile language = IMSit.getLanguageFile();
    private final PluginFile database = IMSit.getDatabaseFile();
    private final PluginFile gui = IMSit.getGuiFile();

    private PluginMessages pluginMessages = IMSit.pluginMessages();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {
        if(args.length == 0) {
            sender.sendMessage(pluginMessages.commandHelp());
            return true;
        }

        switch (args[0]) {
            case "reload" -> {
                if(sender.hasPermission("imsit.reload") || sender.hasPermission("imsit.*")) {
                    Error configError = config.save();
                    if(configError.isException()) {
                        sender.sendMessage(pluginMessages.pluginError());
                        throw new RuntimeException(configError.getExeption());
                    }

                    Error languageError = language.save();
                    if(languageError.isException()) {
                        sender.sendMessage(pluginMessages.pluginError());
                        throw new RuntimeException(languageError.getExeption());
                    }

                    Error databaseFile = database.save();
                    if(databaseFile.isException()) {
                        sender.sendMessage(pluginMessages.pluginError());
                        throw new RuntimeException(databaseFile.getExeption());
                    }

                    Error guiFile = gui.save();
                    if(guiFile.isException()) {
                        sender.sendMessage(pluginMessages.pluginError());
                        throw new RuntimeException(guiFile.getExeption());
                    }

                    IMSit.reloadManagers();
                    pluginMessages = IMSit.pluginMessages();
                    DBManager dBmanager = IMSit.getdBmanager();

                    Error dbManager = dBmanager.reconnect();
                    if(dbManager.isException()) {
                        sender.sendMessage(pluginMessages.pluginError());
                        throw new RuntimeException(guiFile.getExeption());
                    }

                    sender.sendMessage(pluginMessages.pluginReload());
                    return true;
                }
                sender.sendMessage(pluginMessages.noHasPerms());
                return true;
            }
            case "support" -> {
                sender.sendMessage(pluginMessages.support());
                return true;
            }
            default -> {
                sender.sendMessage(pluginMessages.commandHelp());
                return true;
            }
        }
    }
}
