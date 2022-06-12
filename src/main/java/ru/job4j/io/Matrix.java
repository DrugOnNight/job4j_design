package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static void main(String[] args) {
        int n = 5;

        try (FileOutputStream file = new FileOutputStream("matrix.txt")) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    file.write((i * j + "  ").getBytes());
                }
                file.write(System.lineSeparator().getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
