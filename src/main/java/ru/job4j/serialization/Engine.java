package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Engine {
    @XmlAttribute
    private String type;
    private int power;

    public Engine() {

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
}
