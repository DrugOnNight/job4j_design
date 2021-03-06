package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1]))
                .forEach(System.out::println);
    }

    public static void validate(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Use 2 params:"
                    + " java -jar Search.jar ROOT_FOLDER FILE_EXTENSION");
        }
        File firstArg = new File(args[0]);
        if (!firstArg.exists() || !firstArg.isDirectory()) {
            throw new IllegalArgumentException("First argument must be an existing directory");
        }
        if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("File extension must start with a dot.");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}