package ru.ilezzov.iMSit.maanger;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.config.PluginFile;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComponentManager {
    private final PluginFile LANGUAGE = IMSit.getLanguageFile();
    private final Pattern COLORS_PATERN = Pattern.compile("§[0-9a-fk-orA-FK-OR]");
    private final Pattern PLACEHOLDER = Pattern.compile("%(.){1,10}%");
    private final String pluginPrefix = LANGUAGE.getString("Plugin.plugin-prefix");
    private final Map<String, String> COLORS = new HashMap<>();

    public ComponentManager() {
        COLORS.put("§0", "#000000");
        COLORS.put("§1", "#0000AA");
        COLORS.put("§2", "#00AA00");
        COLORS.put("§3", "#00AAAA");
        COLORS.put("§4", "#AA0000");
        COLORS.put("§5", "#AA00AA");
        COLORS.put("§6", "#FFAA00");
        COLORS.put("§7", "#AAAAAA");
        COLORS.put("§8", "#555555");
        COLORS.put("§9", "#5555FF");
        COLORS.put("§a", "#55FF55");
        COLORS.put("§b", "#55FFFF");
        COLORS.put("§c", "#FF5555");
        COLORS.put("§d", "#FF55FF");
        COLORS.put("§e", "#FFFF55");
        COLORS.put("§f", "#FFFFFF");
        COLORS.put("§k", "obf");
        COLORS.put("§l", "b");
        COLORS.put("§m", "st");
        COLORS.put("§n", "u");
        COLORS.put("§o", "i");
        COLORS.put("§r", "reset");
    }

    public Component getComponentByKey(String key, Map<String, Object> values) {
        return legacySerialize(translateAlternateCodeColor('&', replacePlaceholders(LANGUAGE.getString(key), values)));
    }

    private String replacePlaceholders(String s, Map<String, Object> values) {
        Matcher matcher = PLACEHOLDER.matcher(s);
        StringBuilder result = new StringBuilder();
        int lastIndex = 0;

        values.put("%prefix%", pluginPrefix);

        while (matcher.find()) {
            result.append(s, lastIndex, matcher.start()).append(values.get(matcher.group()));
            System.out.println(matcher.group());
            lastIndex = matcher.end();
        }

        result.append(s, lastIndex, s.length());
        return result.toString();
    }

    private Component legacySerialize(String s) {
        return MiniMessage.miniMessage().deserialize(replaceLegacyColor(s));
    }

    private String replaceLegacyColor(String s) {
        Matcher matcher = COLORS_PATERN.matcher(s);
        StringBuilder result = new StringBuilder(s.length());
        int lastIndex = 0;

        while (matcher.find()) {
            result.append(s, lastIndex, matcher.start()).append("<").append(COLORS.get(matcher.group())).append(">");
            lastIndex = matcher.end();
        }

        result.append(s, lastIndex, s.length());
        return result.toString();
    }

    private String translateAlternateCodeColor(char code, String s) {
        char[] b = s.toCharArray();

        for(int i = 0; i < b.length - 1; ++i) {
            if (b[i] == code && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(b[i + 1]) > -1) {
                b[i] = 167;
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }

        return new String(b);
    }

}
