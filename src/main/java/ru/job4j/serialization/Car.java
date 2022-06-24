package ru.job4j.serialization;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {

    @XmlAttribute
    private String name;
    @XmlAttribute
    private int year;
    @XmlAttribute
    private boolean isRegistered;
    private Engine engine;
    @XmlElementWrapper
    @XmlElement(name = "owner")
    private String[] owners;

    public Car() {

    }

    public Car(String name, int year, boolean isRegistered, Engine engine, String[] owners) {
        this.name = name;
        this.year = year;
        this.isRegistered = isRegistered;
        this.engine = engine;
        this.owners = owners;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public boolean isRegistered() {
        return isRegistered;
    }

    public Engine getEngine() {
        return engine;
    }

    public String[] getOwners() {
        return owners;
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
