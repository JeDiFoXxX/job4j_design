package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value");
    }

    @Test
    void whenCommentWithoutPair() {
        String path = "./data/comment_without_pair.properties";
        Config config = new Config(path);
        config.load();
        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    config.value("name");
                });
        assertThat(exception.getMessage()).isEqualTo("Key name not found!");
    }

    @Test
    void whenPairWithoutKey() {
        String path = "./data/pair_without_key.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Invalid line: =value");
    }

    @Test
    void whenPairWithoutValue() {
        String path = "./data/pair_without_value.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Invalid line: key=");
    }

    @Test
    void whenPairWithoutEqualSign() {
        String path = "./data/pair_without_equal_sign.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Invalid line: keyvalue");
    }

    @Test
    void whenPairWithoutKeyAndValue() {
        String path = "./data/pair_without_key_and_value.properties";
        Config config = new Config(path);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, config::load);
        assertThat(exception.getMessage()).isEqualTo("Invalid line: =");
    }

    @Test
    void whenLineHas2EqualSignsAndCommentAndEmptyLine() {
        String path = "./data/line_has_2_equal_signs.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key")).isEqualTo("value=1");
    }
}