package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private void validate(ArgsName arguments) {
        if (!arguments.get("e").startsWith(".")) {
            throw new IllegalArgumentException("Argument -e must start with dot!");
        }
        File argD = new File(arguments.get("d"));
        if (!argD.exists() || !argD.isDirectory()) {
            throw new IllegalArgumentException("Argument -d must be an existing folder!");
        }
        if (!arguments.get("o").matches(".+[.]zip")) {
            throw new IllegalArgumentException("Argument -o must match pattern *.zip");
        }
    }

    public void packFiles(List<Path> sources, Path target) {
        try (ZipOutputStream zip = new ZipOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(target.toString())))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(
                        new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        Zip zip = new Zip();
        zip.validate(arguments);
        List<Path> paths = new ArrayList<>();
        try {
            String argD = arguments.get("d");
            paths = Search.search(Paths.get(argD),
                    elem -> !elem.getFileName().toString().endsWith(arguments.get("e")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packFiles(paths, Paths.get(arguments.get("o")));
    }
}