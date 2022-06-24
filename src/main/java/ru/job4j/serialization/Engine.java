package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class Engine {
    @XmlAttribute
    private String type;
    @XmlAttribute
    private int power;

    public Engine() {

    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

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

    public static void main(String[] args) {
        JSONObject jsonEngine = new JSONObject("{\"type\":\"V6\", \"power\":360}");

        List<String> owners = new ArrayList<>();
        owners.add("Ivan");
        owners.add("Oleg");
        JSONArray jsonOwners = new JSONArray(owners);

        Car car = new Car("Mark 2", 2004, true,
                new Engine("V6", 360), new String[]{"Ivan", "Oleg"});
        JSONObject jsonCar = new JSONObject();
        jsonCar.put("name", car.getName());
        jsonCar.put("year", car.getYear());
        jsonCar.put("registered", car.isRegistered());
        jsonCar.put("engine", jsonEngine);
        jsonCar.put("owners", jsonOwners);

        System.out.println(jsonCar);
        System.out.println(new JSONObject(car));
    }
}
