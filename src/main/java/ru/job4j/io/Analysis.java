package ru.job4j.io;

import java.io.*;
import java.util.StringJoiner;

public class Analysis {
    public void unavailable(String source, String target) {
        StringJoiner list = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(source))) {
            String read;
            String start = null;
            while ((read = reader.readLine()) != null) {
                String[] line = read.split(" ");
                if (start == null && (read.startsWith("500") || read.startsWith("400"))) {
                    start = line[1].concat(";");
                } else if (start != null && !read.startsWith("500") && !read.startsWith("400")) {
                    list.add(start.concat(line[1]).concat(";"));
                    start = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(target)))) {
            write.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}