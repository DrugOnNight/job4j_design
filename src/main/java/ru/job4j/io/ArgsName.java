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

    private void validate(String[] arg) {
        if (arg.length < 2 || arg[0].isEmpty() || arg[0].equals("-") || arg[1].isEmpty()) {
            throw new IllegalArgumentException("Argument must match pattern: -key=value");
        }
        if (!arg[0].startsWith("-")) {
            throw new IllegalArgumentException("Argument must start with -");
        }
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Missing required arguments!");
        }
        String[] splitedParams;
        for (String arg : args) {
            splitedParams = arg.split("=", 2);
            validate(splitedParams);
            splitedParams[0] = splitedParams[0].substring(1);
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