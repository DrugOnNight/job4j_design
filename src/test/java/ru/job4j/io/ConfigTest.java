package ru.job4j.io;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void whenPairWithTwoEqualsSymbols() {
        String path = "./data/pair_with_two_equals_symbols.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "oleg");
        assertEquals(config.value("city"), "new=york");
    }

    @Test
    public void whenSingleCharKey() {
        String path = "./data/single_char_key.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("d"), "6");
        assertEquals(config.value("name"), "oleg");
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Petr Arsentev");
        assertNull(config.value("surname"));
    }

    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Oleg");
        assertEquals(config.value("age"), "22");
    }

    @Test
    public void whenPairWithEmptyLine() {
        String path = "./data/pair_with_empty_line.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Oleg");
        assertEquals(config.value("age"), "22");
    }

    @Test
    public void whenCommentedPairThenNull() {
        String path = "./data/commented_pair.properties";
        Config config = new Config(path);
        config.load();
        assertNull(config.value("name"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenKeyOnly() {
        String path = "./data/key_only.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Oleg");
        assertEquals(config.value("age"), "22");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenValueOnly() {
        String path = "./data/value_only.properties";
        Config config = new Config(path);
        config.load();
        assertEquals(config.value("name"), "Oleg");
        assertEquals(config.value("age"), "22");
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenEqualsSymbolOnly() {
        String path = "./data/equals_symbol_only.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWithoutEqualsSymbol() {
        String path = "./data/without_equals_symbol.properties";
        Config config = new Config(path);
        config.load();
    }
}