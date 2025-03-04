package ru.job4j.io;

import java.io.*;
import java.nio.file.*;
import java.util.*;

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
        validate(path);
        validate(botAnswers);
        final List<String> log = new ArrayList<>();
        final List<String> listAnswers = readPhrases();
        boolean active = true;
        boolean onOffBot = true;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (active) {
                String userMessage = reader.readLine();
                log.add("User: ".concat(userMessage).concat("\n"));
                switch (userMessage) {
                    case STOP -> onOffBot = false;
                    case CONTINUE -> onOffBot = true;
                    case OUT -> active = false;
                    default -> {
                        if (onOffBot && !listAnswers.isEmpty()) {
                            String botMessage = listAnswers.get((int) (Math.random() * listAnswers.size()));
                            log.add("Bot: ".concat(botMessage).concat("\n"));
                            System.out.println(botMessage);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> list = Collections.emptyList();
        try (BufferedReader read = new BufferedReader(new FileReader("./data/".concat(botAnswers)))) {
            list = read.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
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
            throw new IllegalArgumentException(String.format("The file %s must have a .txt extension.", fileName));
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("log.txt", "botAnswers.txt");
        consoleChat.run();
    }
}
