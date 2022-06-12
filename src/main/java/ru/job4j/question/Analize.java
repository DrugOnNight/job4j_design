package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, User> map = new HashMap<>();
        int added = 0, changed = 0;
        for (User user : previous) {
            map.put(user.getId(), user);
        }
        User foundUser;
        for (User user : current) {
            foundUser = map.get(user.getId());
            if (foundUser == null) {
                added++;
            } else if (!user.equals(foundUser)) {
                changed++;
            }
        }
        return new Info(added, changed, previous.size() + added - current.size());
    }
}