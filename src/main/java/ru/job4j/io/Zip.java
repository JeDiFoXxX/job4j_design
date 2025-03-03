package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(path.toString()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toString()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args, Path source, String exclude, File target) {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "Нужно передать три аргумента! Используй <DIRECTORY> <EXCLUDE> <OUTPUT>");
        }
        if (!Files.exists(source) || !Files.isDirectory(source)) {
            throw new IllegalArgumentException("Указанная директория не существует или не является директорией: "
                    + args[0]);
        }
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Расширение должно начинаться с точки!");
        }
        if (!target.toString().endsWith(".zip")) {
            throw new IllegalArgumentException("Файл должен быть архивом с расширением .zip");
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        ArgsName argsName = ArgsName.of(args);
        Path source = Path.of(argsName.get("d"));
        String exclude = argsName.get("e");
        File target = new File(argsName.get("o"));
        zip.validate(args, source, exclude, target);
        zip.packFiles(Search.search(source, e -> !exclude.equals(e.toString())), target);
    }
}