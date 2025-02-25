package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.assertj.core.api.Assertions.*;

import java.io.*;
import java.nio.file.Path;
import java.util.StringJoiner;

class AnalysisTest {

    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        String separator = System.lineSeparator();
        StringJoiner result = new StringJoiner(separator);
        Analysis analysis = new Analysis();
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.txt").toFile();
        try (PrintWriter input = new PrintWriter(source)) {
            input.print("500 10:57:01" + separator + "200 10:58:01"
                    + separator + "500 10:59:01" + separator + "300 11:02:02");
        }
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        try (BufferedReader output = new BufferedReader(new FileReader(target))) {
            output.lines().forEach(result::add);
        }
        String expected = "10:57:01;10:58:01;" + separator + "10:59:01;11:02:02;";
        assertThat(result.toString()).hasToString(expected);
    }
}