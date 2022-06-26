package ru.job4j.searcher;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FindTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void whenFindByName() throws IOException {
        File f1 = temporaryFolder.newFile("oleg.txt");
        File f2 = temporaryFolder.newFile("oleg.xlsx");
        temporaryFolder.newFile("file3.txt");
        temporaryFolder.newFile("file4.txt");
        File target = temporaryFolder.newFile("target.txt");
        String[] args =
                new String[]{
                        "-d=" + temporaryFolder.getRoot().getAbsolutePath(),
                        "-n=oleg",
                        "-t=name",
                        "-o=" + target.getAbsolutePath()};
        Find.main(args);
        String ln = System.lineSeparator();
        String expected = f1.getName() + ln
                + f2.getName() + ln;
        StringBuilder rsl = new StringBuilder();
        Files.lines(target.toPath()).forEach(file -> rsl.append(Paths.get(file).getFileName().toString()).append(ln));
        Assert.assertEquals(expected, rsl.toString());
    }

    @Test
    public void whenFindByMask() throws IOException {
        File f1 = temporaryFolder.newFile("file555.txt");
        File f2 = temporaryFolder.newFile("file555.doc");
        temporaryFolder.newFile("oleg.txt");
        temporaryFolder.newFile("oleg.xlsx");
        temporaryFolder.newFile("file3.txt");
        temporaryFolder.newFile("file4.txt");
        File target = temporaryFolder.newFile("target.txt");
        String[] args =
                new String[]{
                        "-d=" + temporaryFolder.getRoot().getAbsolutePath(),
                        "-n=file???.*t",
                        "-t=mask",
                        "-o=" + target.getAbsolutePath()};
        Find.main(args);
        String ln = System.lineSeparator();
        String expected = f1.getName() + ln;
        StringBuilder rsl = new StringBuilder();
        Files.lines(target.toPath()).forEach(file -> rsl.append(Paths.get(file).getFileName().toString()).append(ln));
        Assert.assertEquals(expected, rsl.toString());
    }

    @Test
    public void whenFindByRegex() throws IOException {
        File f1 = temporaryFolder.newFile("oleg.txt");
        temporaryFolder.newFile("oleg.xlsx");
        temporaryFolder.newFile("olega.txt");
        temporaryFolder.newFile("aoleg.txt");
        temporaryFolder.newFile("aolega.txt");
        File target = temporaryFolder.newFile("target.txt");
        String[] args =
                new String[]{
                        "-d=" + temporaryFolder.getRoot().getAbsolutePath(),
                        "-n=oleg[.].{3}",
                        "-t=regex",
                        "-o=" + target.getAbsolutePath()};
        Find.main(args);
        String ln = System.lineSeparator();
        String expected = f1.getName() + ln;
        StringBuilder rsl = new StringBuilder();
        Files.lines(target.toPath()).forEach(file -> rsl.append(Paths.get(file).getFileName().toString()).append(ln));
        Assert.assertEquals(expected, rsl.toString());
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenTArgumentsIsIllegal() throws IOException {
        File f1 = temporaryFolder.newFile("oleg.txt");
        File target = temporaryFolder.newFile("target.txt");
        String[] args =
                new String[]{
                        "-d=" + temporaryFolder.getRoot().getAbsolutePath(),
                        "-n=oleg[.].{3}",
                        "-t=expectedError",
                        "-o=" + target.getAbsolutePath()};
        Find.main(args);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenDArgumentIsNotFolder() throws IOException {
        File f1 = temporaryFolder.newFile("oleg.txt");
        File target = temporaryFolder.newFile("target.txt");
        String[] args =
                new String[]{
                        "-d=" + f1.getName(),
                        "-n=oleg[.].{3}",
                        "-t=regex",
                        "-o=" + target.getAbsolutePath()};
        Find.main(args);
    }

}