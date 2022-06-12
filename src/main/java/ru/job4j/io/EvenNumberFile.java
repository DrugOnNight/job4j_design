package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream inputFile = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = inputFile.read()) != -1) {
                if (Character.isDigit((char) read)) {
                    text.append((char) read);
                } else if (text.length() > 0) {
                    System.out.println(Integer.parseInt(text.toString()) % 2 == 0);
                    text = new StringBuilder();
                }
            }
            System.out.println(Integer.parseInt(text.toString()) % 2 == 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
