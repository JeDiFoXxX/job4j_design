package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Нужно передать два аргумента! Используй <DIRECTORY> <EXTENSION>");
        }
        Path path = Paths.get(args[0]);
        String extension = args[1];
        if (!Files.exists(path) || !Files.isDirectory(path)) {
            throw new IllegalArgumentException("Указанная директория не существует или не является директорией: "
                    + args[0]);
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("Расширение должно начинаться с точки!");
        }
    }
}