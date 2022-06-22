package ru.job4j.serialization;

import java.io.Serializable;

public class Engine implements Serializable {
    private String type;
    private int power;

    public Engine(String type, int power) {
        this.type = type;
        this.power = power;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "type='" + type + '\''
                + ", power=" + power
                + '}';
    }
}
