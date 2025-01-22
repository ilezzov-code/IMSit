package ru.ilezzov.iMSit.config;

import org.yaml.snakeyaml.Yaml;
import ru.ilezzov.iMSit.model.Error;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class PluginFile {
    private final Yaml yaml = new Yaml();
    private Map<String, Object> file;
    private final Path path;

    public PluginFile(Path path) {
        this.path = path;

        try {
            file = yaml.load(new FileInputStream(String.valueOf(path)));
        } catch (IOException e) {

        }
    }

    public Error save() {
        try {
            file = yaml.load(new FileInputStream(String.valueOf(path)));
        } catch (IOException e) {
            return new Error(e);
        }
        return new Error();
    }

    public String getString(String key) {
        return String.valueOf(getValue(key));
    }

    public int getInt(String key) {
        Object value = getValue(key);
        if (value instanceof Integer) {
            return (int) value;
        }
        throw new ClassCastException("The returned object is not an int");
    }

    public boolean getBoolean(String key) {
        Object value = getValue(key);
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        throw new ClassCastException("The returned object is not an boolean");
    }

    public List<Object> getList(String key) {
        Object value = getValue(key);
        if (value instanceof List<?>) {
            return (List<Object>) value;
        }
        throw new ClassCastException("The returned object is not an List<Object>");
    }

    private Object getValue(String keyPath) {
        String[] keys = keyPath.split("\\.");
        Map<String, Object> finalMap = file;

        for (int i = 0; i < keys.length -1; i++) {
            if (finalMap.get(keys[i]) instanceof Map) {
                finalMap = (Map<String, Object>) finalMap.get(keys[i]);
            }
        }

        return finalMap.get(keys[keys.length - 1]);
    }
}
