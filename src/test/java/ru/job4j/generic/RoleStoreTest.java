package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Developer"));
    }

    @Test
    public void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Manager"));
        store.add(new Role("1", "Developer"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenReplaceThenRoleIsManager() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.replace("1", new Role("1", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Manager"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeRole() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.replace("10", new Role("10", "Manager"));
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Developer"));
    }

    @Test
    public void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteRoleThenRoleIsDeveloper() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Developer"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRole(), is("Developer"));
    }
}