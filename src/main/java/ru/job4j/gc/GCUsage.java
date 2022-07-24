package ru.job4j.gc;

public class GCUsage {
    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            new User(String.valueOf(i), i);
        }
        System.out.println("Finish");
    }
}
