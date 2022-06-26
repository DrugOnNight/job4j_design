package ru.job4j.searcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Predicate;

public class Find {

    private static void validate(String[] args) {
        ArgsName arguments = ArgsName.of(args);
        if (args.length != 4) {
            throw new IllegalArgumentException("Usage 4 arguments: "
                    + "-d=SOURCE_DIR -t=TYPE_SEARCHER -n=MASK -o=OUTPUT_FILE");
        }
        File argD = new File(arguments.get("d"));
        if (!argD.exists() || !argD.isDirectory()) {
            throw new IllegalArgumentException("Argument -d must be existing directory");
        }
        if (!arguments.get("o").matches("^.+[.]...$")) {
            throw new IllegalArgumentException("Argument -o must be file");
        }
        String argT = arguments.get("t");
        if (!"mask".equals(argT) && !"name".equals(argT) && !"regex".equals(argT)) {
            throw new IllegalArgumentException("Argument -t must be equals to name,"
                    + " mask or regex");
        }
        arguments.get("n");
    }

    private static void findFiles(Path source, Predicate<Path> condition, String output) throws IOException {
        Visitor visitor = new Visitor(condition);
        try {
            Files.walkFileTree(source, visitor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(output)) {
            for (Path path : visitor.getPaths()) {
                writer.println(path);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName arguments = ArgsName.of(args);
        Predicate<Path> condition;
        String argN = arguments.get("n");
        switch (arguments.get("t")) {
            case ("name"):
                condition = path -> path.getFileName().toString().matches(argN + "[.].{3,4}");
                break;
            case ("mask"):
                condition = path -> path.getFileName().toString().matches(
                    argN.replace(".", "[.]")
                            .replace("?", ".")
                            .replace("*", ".*")
                );
                break;
            case ("regex"):
                condition = path -> path.getFileName().toString().matches(argN);
                break;
            default:
                throw new IllegalArgumentException("Argument T is incorrect");
        }
        Path source = Paths.get(arguments.get("d"));
        String output = arguments.get("o");
        findFiles(source, condition, output);
    }
}
