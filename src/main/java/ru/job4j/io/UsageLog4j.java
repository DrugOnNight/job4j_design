package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte age = 22;
        short growth = 181;
        int salary = 120_000;
        long capital = 1_000_000_000_000L;
        boolean isStudent = true;
        float vision = 0.9F;
        double averageSpeed = 6.689;
        char mark = 'A';
        LOG.debug("\nPerson info: {} years\n{} cm\n{} rubs\n{} capital rubs\nStudent? {}\n"
                        + "vision: {}\nAverage speed: {}\nMark: {}", age, growth, salary,
                capital, isStudent, vision, averageSpeed, mark);
    }
}