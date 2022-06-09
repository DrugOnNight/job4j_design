package ru.job4j.iterator;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;


public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 4, 5, 6, 2, 3));
        ListUtils.removeIf(input, x -> x < 3);
        assertThat(input, is(Arrays.asList(4, 5, 6, 3)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 3, 4, 1));
        ListUtils.replaceIf(input, x -> x == 1, -1);
        assertThat(input, is(Arrays.asList(0, -1, 5, 3, 4, -1)));
    }

    @Test
    public void whenRemoveAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 5, 3, 4, 1));
        List<Integer> elements = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 5, 4)));
    }

}