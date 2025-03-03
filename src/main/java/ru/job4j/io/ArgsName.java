package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: 'Xms' is missing");
        }
        return values.get(key);
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        for (String pair : args) {
            if (!pair.startsWith("-")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not start with a '-' character", pair));
            }
            if (!pair.contains("=")) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain an equal sign", pair));
            }
            String[] argument = pair.substring(1).split("=", 2);
            if (argument[0].isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a key", pair));
            }
            if (argument[1].isEmpty()) {
                throw new IllegalArgumentException(String.format(
                        "Error: This argument '%s' does not contain a value", pair));
            }
            values.put(argument[0], argument[1]);
        }
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}