package ru.job4j.io;

import java.io.*;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream file = new FileInputStream("data/even.txt")) {
            StringBuilder builder = new StringBuilder();
            int read;
            while ((read = file.read()) != -1) {
                builder.append((char) read);
            }
            String[] lines = builder.toString().split(System.lineSeparator());
            for (String line : lines) {
                System.out.println(Integer.parseInt(line) % 2 == 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
