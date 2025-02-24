package ru.job4j.io;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    public List<String> filter() {
        List<String> list = new ArrayList<>(Collections.emptyList());
        try (BufferedReader input = new BufferedReader(new FileReader(file))) {
            String read;
            while ((read = input.readLine()) != null) {
                String[] line = read.split(" ");
                if (line[line.length - 2].equals("404")) {
                    list.add(read);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void saveTo(String out) {
        var data = filter();
        if (!data.isEmpty()) {
            try (PrintWriter output = new PrintWriter(new BufferedOutputStream(new FileOutputStream(out)))) {
                for (String line : data) {
                    output.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new LogFilter("data/log.txt").saveTo("data/404.txt");
    }
}
