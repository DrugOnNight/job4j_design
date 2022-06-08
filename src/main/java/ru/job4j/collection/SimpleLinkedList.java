package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int modCount = 0;
    private int size = 0;
    private Node<E> first;

    @Override
    public void add(E value) {
        if (size == 0) {
            first = new Node<>(value, null);
        } else {
            Node<E> rsl = first;
            for (int i = 0; i < size - 1; i++) {
                rsl = rsl.next;
            }
            rsl.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> rsl = first;
        for (int i = 0; i < index; i++) {
            rsl = rsl.next;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int step = 0;
            private final int exceptedModCount = modCount;
            private Node<E> nowItem = first;

            @Override
            public boolean hasNext() {
                if (exceptedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return step < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                nowItem = step == 0 ? first : nowItem.next;
                step++;
                return nowItem.item;
            }
        };
    }
}