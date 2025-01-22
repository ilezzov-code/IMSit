package ru.ilezzov.iMSit.message;

import net.kyori.adventure.text.Component;
import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.maanger.ComponentManager;

import java.util.HashMap;
import java.util.Map;

public class PluginMessages {
    private final ComponentManager COMPONENT_MANAGER = IMSit.getComponentManager();
    private final Map<String, Object> VALUES = new HashMap<>();

    public Component commandHelp() {
        return COMPONENT_MANAGER.getComponentByKey("Messages.command-help", VALUES);
    }


    public Component support() {
        return COMPONENT_MANAGER.getComponentByKey("Messages.command-support", VALUES);
    }

    public Component pluginError() {
        return COMPONENT_MANAGER.getComponentByKey("Plugin.plugin-error", VALUES);
    }

    public Component pluginReload() {
        return COMPONENT_MANAGER.getComponentByKey("Plugin.plugin-reload", VALUES);
    }

    public Component commandSitEnableChat() {
        return COMPONENT_MANAGER.getComponentByKey("command-sit-enable-chat", VALUES);
    }

    public Component commandSitEnableActionBar() {
        return COMPONENT_MANAGER.getComponentByKey("command-sit-enable-actionbar", VALUES);
    }

    public Component commandLayEnableChat() {
        return COMPONENT_MANAGER.getComponentByKey("command-lay-enable-chat", VALUES);
    }

    public Component commandLayEnableActionBar() {
        return COMPONENT_MANAGER.getComponentByKey("command-lay-enable-actionbar", VALUES);
    }

    public Component commandCrawlEnableChat() {
        return COMPONENT_MANAGER.getComponentByKey("command-crawl-enable-chat", VALUES);
    }

    public Component commandCrawlEnableActionBar() {
        return COMPONENT_MANAGER.getComponentByKey("command-crawl-enable-actionbar", VALUES);
    }

    public Component commandSpinEnableChat() {
        return COMPONENT_MANAGER.getComponentByKey("command-spin-enable-chat", VALUES);
    }

    public Component commandSpinEnableActionBar() {
        return COMPONENT_MANAGER.getComponentByKey("command-spin-enable-actionbar", VALUES);
    }

}
