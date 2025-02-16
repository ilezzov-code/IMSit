package ru.ilezzov.iMSit;

import org.bukkit.plugin.java.JavaPlugin;
import ru.ilezzov.iMSit.commands.IMSitCommand;
import ru.ilezzov.iMSit.commands.LayCommand;
import ru.ilezzov.iMSit.commands.SitCommand;
import ru.ilezzov.iMSit.config.PluginFile;
import ru.ilezzov.iMSit.event.PlayerGetUpEvent;
import ru.ilezzov.iMSit.event.PlayerSatDownEvent;
import ru.ilezzov.iMSit.manager.ComponentManager;
import ru.ilezzov.iMSit.manager.DBManager;
import ru.ilezzov.iMSit.manager.FileManager;
import ru.ilezzov.iMSit.message.ConsoleMessages;
import ru.ilezzov.iMSit.message.PluginMessages;

public final class IMSit extends JavaPlugin {

    private static IMSit instance;
    private static PluginFile config;
    private static PluginFile language;
    private static PluginFile database;
    private static PluginFile gui;

    private static DBManager dBmanager;
    private static ComponentManager componentManager;
    private static PluginMessages pluginMessages;

    @Override
    public void onEnable() {
        instance = this;
        config = new FileManager().getFile("", "config.yml");
        language = new FileManager().getFile("lang", config.getString("lang") + ".yml");
        database = new FileManager().getFile("data", "database.yml");
        gui = new FileManager().getFile("gui", "settings.yml");

        componentManager = new ComponentManager();
        dBmanager = new DBManager();
        pluginMessages = new PluginMessages();

        registerCommand();
        registerEvent();

        ConsoleMessages.successEnable();
    }

    @Override
    public void onDisable() {
        ConsoleMessages.successDisable();
    }

    public static IMSit getInstance() {
        return instance;
    }

    public static PluginFile getConfigurationFile() {
        return config;
    }

    public static PluginFile getLanguageFile() {
        return language;
    }

    public static PluginFile getDatabaseFile() {
        return database;
    }

    public static PluginFile getGuiFile() {
        return gui;
    }

    public static ComponentManager getComponentManager() {
        return componentManager;
    }

    public static DBManager getdBmanager() {
        return dBmanager;
    }

    public static PluginMessages pluginMessages() {
        return pluginMessages;
    }

    public static void reloadManagers() {
        language = new FileManager().getFile("lang", config.getString("lang") + ".yml");

        componentManager = new ComponentManager();
        dBmanager = new DBManager();
        pluginMessages = new PluginMessages();
    }

    private void registerCommand() {
        getCommand("imsit").setExecutor(new IMSitCommand());
        getCommand("sit").setExecutor(new SitCommand());
        getCommand("lay").setExecutor(new LayCommand());
    }

    private void registerEvent() {
        getServer().getPluginManager().registerEvents(new PlayerGetUpEvent(), this);
        getServer().getPluginManager().registerEvents(new PlayerSatDownEvent(), this);
    }
}
