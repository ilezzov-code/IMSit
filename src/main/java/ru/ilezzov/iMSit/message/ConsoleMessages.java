package ru.ilezzov.iMSit.message;

import ru.ilezzov.iMSit.IMSit;

import java.nio.file.Path;
import java.util.logging.Logger;

public class ConsoleMessages {
    private static final IMSit INSTANCE = IMSit.getInstance();
    private static final Logger LOGGER = INSTANCE.getLogger();

    public static void successEnable() {
        LOGGER.info("IMSit is enabled");
        LOGGER.info("Version: " + INSTANCE.getPluginMeta().getVersion());
        LOGGER.info("Developer: ILeZzoV");
    }

    public static void successDisable() {
        LOGGER.info("IMSit is §cdisabled");
        LOGGER.info("Version: " + INSTANCE.getPluginMeta().getVersion());
        LOGGER.info("Developer: §6ILeZzoV");
    }

    public static void errorCreateFile(String fileName) {
        LOGGER.info("An error occurred when creating the file: " + fileName);
    }

    public static void errorCreateDirectory(Path directoryPath) {
        LOGGER.info("An error occurred when creating the file" + directoryPath);
    }
}


