package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean serverRunning = true;
            String line;
            String[] splitedLine;
            while ((line = in.readLine()) != null) {
                splitedLine = line.split(" ", 2);
                if (("200".equals(splitedLine[0]) || "300".equals(splitedLine[0]))
                        && !serverRunning) {
                    out.printf("%s%s%n", splitedLine[1], ";");
                    serverRunning = true;
                } else if (("400".equals(splitedLine[0]) || "500".equals(splitedLine[0]))
                        && serverRunning) {
                    out.printf("%s%s", splitedLine[1], ";");
                    serverRunning = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizator = new Analizy();
        String inPath = "./data/unavailableServer/server.log";
        String outPath = "./data/unavailableServer/unavailable.csv";
        analizator.unavailable(inPath, outPath);
    }
}