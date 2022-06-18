package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private HashMap<FileProperty, List<Path>> filesMap = new HashMap<>();

    public List<Path> getDuplicates() {
        List<Path> rsl = new ArrayList<>();
        filesMap.values()
                .stream()
                .filter(list -> list.size() > 1)
                .forEach(rsl::addAll);
        return rsl;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProperty = new FileProperty(file.toFile().getTotalSpace(), file.getFileName().toString());
        if (filesMap.containsKey(fileProperty)) {
            filesMap.get(fileProperty).add(file);
        } else {
            filesMap.put(fileProperty, new ArrayList<>(List.of(file)));
        }
        return super.visitFile(file, attrs);
    }
}