package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        StringJoiner result = new StringJoiner(System.lineSeparator());
        Path pathCsvFile = Path.of(argsName.get("path"));
        Path pathOutFile = Path.of(argsName.get("out"));
        String delimiter = argsName.get("delimiter");
        List<Integer> list = new ArrayList<>();
        if (!pathCsvFile.isAbsolute()) {
            pathCsvFile = Path.of("./data/".concat(pathCsvFile.toString()));
        }
        if (!pathOutFile.endsWith("stout") && !pathOutFile.isAbsolute()) {
            pathOutFile = Path.of("./data/".concat(pathOutFile.toString()));
        }

        try (Scanner scanner = new Scanner(pathCsvFile)) {
            StringJoiner stringJoiner = new StringJoiner(delimiter);
            int count = 0;
            while (scanner.hasNextLine()) {
                String[] line = scanner.nextLine().split(delimiter);
                if (count == 0) {
                    for (String filter : argsName.get("filter").split(",")) {
                        for (int index = 0; index < line.length; index++) {
                            if (line[index].equals(filter)) {
                                list.add(index);
                            }
                        }
                    }
                    count++;
                }
                for (Integer i : list) {
                    stringJoiner.add(line[i]);
                }
                result.add(stringJoiner.toString());
                stringJoiner = new StringJoiner(delimiter);
            }
            if ("stdout".equals(argsName.get("out"))) {
                System.out.println(result + System.lineSeparator());
            } else {
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(pathOutFile.toString())))) {
                    writer.print(result + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String string : args) {
            builder.append(string);
        }
        if (args.length != 4 || !builder.toString().matches("-path=.*-delimiter=.*-out=.*-filter=.*")) {
            throw new IllegalArgumentException(
                    "The required parameters [-path, -delimiter, -out, -filter] are missing or malformed.");
        }
        if (!args[0].endsWith(".csv")) {
            throw new IllegalArgumentException(
                    String.format("The file %s must have a .csv extension", args[0].substring(6)));
        }
        Path path = Path.of("./data/".concat(args[0].substring(6)));
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            throw new IllegalArgumentException(String.format("Path is incorrect or %s is not a file.", path));
        }
        if (!args[2].endsWith("stdout")) {
            path = Path.of("./data/".concat(args[2].substring(5)));
            if (!Files.exists(path) || !Files.isRegularFile(path)) {
                throw new IllegalArgumentException("The specified file path is either invalid or not a regular file");
            }
        }
    }

    public static void main(String[] args) {
        try {
            ArgsName argsName = ArgsName.of(args);
            validateArgs(args);
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}