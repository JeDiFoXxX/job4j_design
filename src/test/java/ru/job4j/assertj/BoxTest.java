package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
        assertThat(box.getNumberOfVertices()).isEqualTo(0);
        assertThat(box.isExist()).isTrue();
        assertThat(box.getArea()).isEqualTo(1256D, withPrecision(1.0));
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
        assertThat(box.isExist()).isTrue();
        assertThat(box.getArea()).isEqualTo(173D, withPrecision(1.0));

    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        assertThat(box.whatsThis()).isEqualTo("Cube");
        assertThat(box.getNumberOfVertices()).isEqualTo(8);
        assertThat(box.isExist()).isTrue();
        assertThat(box.getArea()).isEqualTo(600D, withPrecision(1.0));
    }

    @Test
    void isThisUnknownObject() {
        Box firstBox = new Box(3, 10);
        Box secondBox = new Box(4, 0);
        Box thirdBox = new Box(4, -10);
        assertThat(firstBox.whatsThis()).isEqualTo("Unknown object");
        assertThat(secondBox.whatsThis()).isEqualTo("Unknown object");
        assertThat(thirdBox.whatsThis()).isEqualTo("Unknown object");

        assertThat(firstBox.getNumberOfVertices()).isEqualTo(-1);
        assertThat(secondBox.getNumberOfVertices()).isEqualTo(-1);
        assertThat(thirdBox.getNumberOfVertices()).isEqualTo(-1);

        assertThat(firstBox.isExist()).isFalse();
        assertThat(secondBox.isExist()).isFalse();
        assertThat(thirdBox.isExist()).isFalse();

        assertThat(firstBox.getArea()).isEqualTo(0);
        assertThat(secondBox.getArea()).isEqualTo(0);
        assertThat(thirdBox.getArea()).isEqualTo(0);
    }
}