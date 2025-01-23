package ru.ilezzov.iMSit.manager;

import ru.ilezzov.iMSit.IMSit;
import ru.ilezzov.iMSit.config.PluginFile;
import ru.ilezzov.iMSit.message.ConsoleMessages;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    private final Path pluginPath = IMSit.getInstance().getDataPath();

    public PluginFile getFile(String path, String fileName) {
        Path directoryPath = Paths.get(String.valueOf(pluginPath), path);
        Path filePath = Paths.get(String.valueOf(pluginPath), path, fileName);

        if(!existsDirectory(directoryPath)) {
            try {
                createDirectory(directoryPath);
            } catch (IOException e) {
                ConsoleMessages.errorCreateDirectory(directoryPath);
                throw new RuntimeException(e);
            }
        }

        if(!existsFile(filePath))
            try {
                copyFile(fileName, filePath.toString());

                return new PluginFile(filePath);
            } catch (IOException e) {
                ConsoleMessages.errorCreateFile(fileName);
                throw new RuntimeException(e);
            }

        return new PluginFile(filePath);
    }

    private boolean existsDirectory(Path directoryPath) {
        return Files.exists(directoryPath);
    }

    private boolean existsFile(Path filePath) {
        return Files.exists(filePath);
    }

    private void createDirectory(Path directoryPath) throws IOException {
        Files.createDirectory(directoryPath);
    }

    private void copyFile(String fileName, String filePath) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        OutputStream out = new FileOutputStream(filePath);

        int data;
        while ((data = in.read()) != -1) {
            out.write(data);
        }

        out.flush();
    }
}
