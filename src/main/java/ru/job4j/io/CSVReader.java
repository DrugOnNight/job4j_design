package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        validate(argsName);
        String delimiter = argsName.get("delimiter");
        try (FileInputStream fis = new FileInputStream(argsName.get("path"));
             PrintStream writer = new PrintStream("stdout".equals(argsName.get("out"))
                     ? new BufferedOutputStream(System.out)
                     : new FileOutputStream(argsName.get("out")));
             Scanner scanner = new Scanner(fis)
                     .useDelimiter(delimiter)) {

            ArrayList<String> data = new ArrayList<>();
            ArrayList<String> titles = new ArrayList<>(
                    List.of(scanner.nextLine().split(delimiter)));
            ArrayList<String> argFilter = new ArrayList<>(
                    List.of(argsName.get("filter").split(",")));
            HashSet<Integer> needColumns = new HashSet<>();

            int titlesCount = 0;
            for (int i = 0; i < titles.size(); i++) {
                if (argFilter.contains(titles.get(i))) {
                    needColumns.add(i);
                    data.add(titles.get(i));
                    titlesCount++;
                }
            }


            String[] line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().split(delimiter);
                for (int i = 0; i < line.length; i++) {
                    if (needColumns.contains(i))  {
                        data.add(line[i]);
                    }
                }
            }

            StringBuilder rsl = new StringBuilder();
            for (int i = 0; i < data.size(); i += titlesCount) {
                for (int j = 0; j < titlesCount; j++) {
                    rsl.append(data.get(i + j));
                    if (j != titlesCount - 1) {
                        rsl.append(delimiter);
                    }
                }
                rsl.append(System.lineSeparator());
            }
            writer.print(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName arguments) {
        File argPath = new File(arguments.get("path"));
        if (!argPath.exists() || !argPath.isFile()) {
            throw new IllegalArgumentException("Argument PATH is incorrect."
                    + " It must be an existing file");
        }
        String argOut = arguments.get("out");
        if (!"stdout".equals(argOut)
                && (!(new File(argOut).exists()) || !(new File(argOut).isFile()))) {
            throw new IllegalArgumentException("Argument OUT must be an existing file or stdout");
        }
    }

    public static void main(String[] args) {
        handle(ArgsName.of(args));
    }
}
