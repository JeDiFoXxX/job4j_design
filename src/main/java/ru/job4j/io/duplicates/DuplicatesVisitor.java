package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private final Map<FileProperty, List<Path>> listDuplicate = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty fileProperty = new FileProperty(attributes.size(), file.getFileName().toString());
        listDuplicate.computeIfAbsent(fileProperty, k -> new ArrayList<>()).add(file);
        return FileVisitResult.CONTINUE;
    }

    public void printDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : listDuplicate.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.printf("%s - %.7f Mb%n", entry.getKey().getName(),
                        (float) entry.getKey().getSize() / 1048576);
                for (Path path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}