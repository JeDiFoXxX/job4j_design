package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void namesArrayIsEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageMatching("^.+");
    }

    @Test
    void nameDoesNotContainEqualsSymbol() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Jojo"))
                .hasMessageContaining("Jojo");
    }

    @Test
    void nameDoesNotContainKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Jojo"))
                .hasMessageContaining("=Jojo");
    }

    @Test
    void nameDoesNotContainValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Jojo="))
                .hasMessageContaining("Jojo=");
    }
}