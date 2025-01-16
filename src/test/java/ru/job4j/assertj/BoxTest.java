package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        assertThat(box.whatsThis()).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        assertThat(box.whatsThis()).isEqualTo("Tetrahedron");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        assertThat(box.whatsThis()).isEqualTo("Cube");
    }

    @Test
    void whenVertexInvalidIsThisUnknownObject() {
        Box box = new Box(3, 10);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void whenEdgeInvalidIsThisUnknownObject() {
        Box box = new Box(4, 0);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void whenVertex4AndEdgeValidThenVertex4() {
        Box box = new Box(4, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(4);
    }

    @Test
    void whenVertexInvalidAndEdgeValidThenVertexMinus1() {
        Box box = new Box(3, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void whenVertexValidAndEdgeInvalidThenVertexMinus1() {
        Box box = new Box(4, 0);
        assertThat(box.getNumberOfVertices()).isEqualTo(-1);
    }

    @Test
    void whenIsExistTrue() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void whenIsExistFalse() {
        Box box = new Box(3, -10);
        assertThat(box.isExist()).isFalse();
    }

    @Test
    void isSphereArea() {
        Box box = new Box(0, 10);
        assertThat(box.getArea()).isEqualTo(1256.64D, withPrecision(0.01));
    }

    @Test
    void isTetrahedronArea() {
        Box box = new Box(4, 10);
        assertThat(box.getArea()).isEqualTo(173.20D, withPrecision(0.01));
    }

    @Test
    void isCubeArea() {
        Box box = new Box(8, 10);
        assertThat(box.getArea()).isEqualTo(600D);
    }

    @Test
    void whenVertexMinus1ThenArea0() {
        Box box = new Box(3, 10);
        assertThat(box.getArea()).isEqualTo(0);
    }
}