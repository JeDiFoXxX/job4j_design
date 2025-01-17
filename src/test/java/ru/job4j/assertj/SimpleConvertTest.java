package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "first", "second", "second", "three");
        assertThat(list).hasSize(5)
                .startsWith("first")
                .endsWith("three")
                .containsExactly("first", "first", "second", "second", "three")
                .containsSequence("first", "second", "second");

    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> list = simpleConvert.toSet("first", "first", "second", "second", "three");
        assertThat(list).hasSize(3)
                .containsExactlyInAnyOrder("three", "first", "second")
                .filteredOn(e -> e.equals("first")).hasSize(1);
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> list = simpleConvert.toMap("first", "second", "three", "first", "second");
        list.put("first", 1);
        list.put("second", 2);
        list.put("three", 3);
        assertThat(list).hasSize(3)
                .containsValues(3, 2, 1)
                .containsEntry("second", 2)
                .doesNotContainEntry("four", 4);
    }
}