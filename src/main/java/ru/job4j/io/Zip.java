package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

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
        if (arguments.get("e") == null
                || arguments.get("d") == null
                || arguments.get("o") == null) {
            throw new IllegalArgumentException("Usage 3 params: -d=ROOT_DIR"
                    + " -e=IGNORE_FILE_EXTENSION -o=OUTPUT_ZIP");
        }
        Zip zip = new Zip();
        List<Path> paths = new ArrayList<>();
        try {
            String argD = arguments.get("d");
            paths = Search.search(Paths.get(argD),
                    elem -> !elem.getFileName().toString().endsWith(arguments.get("e")))
                    .stream()
                    .map(path -> Paths.get(argD).relativize(path))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        zip.packFiles(paths, Paths.get(arguments.get("o")));
    }
}