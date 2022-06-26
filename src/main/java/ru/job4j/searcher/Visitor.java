package ru.job4j.searcher;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Visitor extends SimpleFileVisitor<Path> {
    private final Predicate<Path> predicate;
    private final ArrayList<Path> paths = new ArrayList<>();

    public Visitor(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (predicate.test(file)) {
            paths.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public ArrayList<Path> getPaths() {
        return paths;
    }
}
