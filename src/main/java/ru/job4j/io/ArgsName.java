package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        String rsl = values.get(key);
        if (rsl == null) {
            throw new IllegalArgumentException("Parameter not found!");
        }
        return rsl;
    }

    private void parse(String[] args) {
        String[] splitedParams;
        for (String arg : args) {
            splitedParams = arg.substring(1).split("=", 2);
            if (splitedParams.length != 2
                    || splitedParams[0].isEmpty()
                    || splitedParams[1].isEmpty()) {
                throw new IllegalArgumentException("Incorrect argument. Usage key=value pattern");
            }
            values.put(splitedParams[0], splitedParams[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}