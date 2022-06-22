package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class Car implements Serializable {
    private String name;
    private int year;
    private boolean isRegistered;
    private Engine engine;
    private String[] owners;

    public Car(String name, int year, boolean isRegistered, Engine engine, String[] owners) {
        this.name = name;
        this.year = year;
        this.isRegistered = isRegistered;
        this.engine = engine;
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Car{"
                + "name='" + name + '\''
                + ", year=" + year
                + ", isRegistered=" + isRegistered
                + ", engine=" + engine
                + ", owners=" + Arrays.toString(owners)
                + '}';
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();
        Car car = new Car("Mark 2", 2004, true,
                new Engine("V6", 360), new String[]{"Ivan", "Oleg"});
        String carToJson = gson.toJson(car);
        System.out.println(carToJson);

        Car jsonToCar = gson.fromJson(carToJson, Car.class);
        System.out.println(jsonToCar);
    }
}
