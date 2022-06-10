package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) {
        User user1 = new User("Alla", 1,
                new Calendar.Builder().setTimeOfDay(10, 11, 12, 13).build());
        User user2 = new User("Alla", 1,
                new Calendar.Builder().setTimeOfDay(10, 11, 12, 13).build());
        int capacity = 16;
        Map<User, Object> map = new HashMap<>(capacity);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println("Hashcode объекта 1: " + user1.hashCode());
        System.out.println("Hashcode объекта 2: " + user2.hashCode());
        System.out.println();
        System.out.println("Номер бакета для объекта 1: " + (user1.hashCode() & (capacity - 1)));
        System.out.println("Номер бакета для объекта 2: " + (user2.hashCode() & (capacity - 1)));
        System.out.println(map);
    }
}
