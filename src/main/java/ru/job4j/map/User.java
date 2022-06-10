package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
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
        System.out.println("Номер бакета для объекта 1: " + (user1.hashCode() & (capacity - 1)));
        System.out.println("Номер бакета для объекта 2: " + (user2.hashCode() & (capacity - 1)));
        System.out.println(map);
    }
}
