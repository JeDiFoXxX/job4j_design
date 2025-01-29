package ru.job4j.generic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoleStoreTest {

    @Test
    void whenAddAndFindRoleAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        assertThat(store.findById("1").getRole()).isEqualTo("Admin");
    }

    @Test
    void whenAddAndFindIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        assertThat(store.findById("2")).isNull();
    }

    @Test
    void whenAddDuplicateAndFindAdmin() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.add(new Role("1", "Manager"));
        assertThat(store.findById("1").getRole()).isEqualTo("Admin");
    }

    @Test
    void whenReplaceIsTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role newRole =  new Role("1", "Manager");
        assertThat(store.replace("1", newRole)).isTrue();
    }

    @Test
    void whenReplaceIsFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        Role newRole =  new Role("1", "Manager");
        assertThat(store.replace("2", newRole)).isFalse();
    }

    @Test
    void whenAddAndDeleteAndFindIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Admin"));
        store.delete("1");
        assertThat(store.findById("1")).isNull();
    }

}