package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        while (data[row].length == 0 && row < data.length - 1) {
            row++;
        }
        return column < data[row].length || row < data.length - 1;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int rsl = data[row][column];
        if (column < data[row].length - 1) {
            column++;
        } else {
            row++;
            column = 0;
        }
        return rsl;
    }

    public static void main(String[] args) {
        int[][] in = {
                {}, {}, {}
        };
        MatrixIt it = new MatrixIt(in);
        boolean r = it.hasNext();
        System.out.println(r);
    }
}