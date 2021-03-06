package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            String line;
            String[] splitLine;
            while ((line = read.readLine()) != null) {
                if (line.matches("^[^#\\n]*\\S=\\S.*$")) {
                    splitLine = line.split("=", 2);
                    values.put(splitLine[0], splitLine[1]);
                } else if (!line.matches("^\\s*#.*$") && !line.matches("^$")) {
                    throw new IllegalArgumentException("Строка не соответствует шаблону Ключ=Значение");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config cfg = new Config("app.properties");
        System.out.println(cfg);
        cfg.load();
        System.out.println(cfg.values);
    }
}