package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        final List<String> log = new ArrayList<>();
        final List<String> listAnswers = readPhrases();
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        boolean onOffBot = true;
        while (active) {
            String userMessage = scanner.nextLine().toLowerCase();
            log.add("User: ".concat(userMessage).concat("\n"));
            switch (userMessage) {
                case STOP -> onOffBot = false;
                case CONTINUE -> onOffBot = true;
                case OUT -> {
                    active = false;
                    scanner.close();
                }
                default -> {
                    if (onOffBot && !listAnswers.isEmpty()) {
                        String botMessage = listAnswers.get((int) (Math.random() * listAnswers.size()));
                        log.add("Bot: ".concat(botMessage).concat("\n"));
                        System.out.println(botMessage);
                    }
                }
            }
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        validate(botAnswers);
        List<String> list = Collections.emptyList();
        try (BufferedReader read = new BufferedReader(new FileReader("./data/".concat(botAnswers)))) {
            list = read.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        validate(path);
        try (PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter("./data/".concat(path))))) {
            for (String string : log) {
                write.print(string);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String fileName) {
        Path botFile = Path.of("./data/".concat(fileName));
        if (!Files.exists(botFile) || !Files.isRegularFile(botFile)) {
            throw new IllegalArgumentException(String.format("Path is incorrect or %s is not a file.", fileName));
        }
        if (!fileName.endsWith(".txt")) {
            throw new IllegalArgumentException("The file must have a .txt extension.");
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("log.txt", "botAnswers.txt");
        consoleChat.run();
    }
}
