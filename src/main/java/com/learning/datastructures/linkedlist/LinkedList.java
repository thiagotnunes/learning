package com.learning.datastructures.linkedlist;

public interface LinkedList<T> {
    default void add(T e) {
        addFirst(e);
    }

    void addFirst(T e);

    void addLast(T e);

    default T get() {
        return getFirst();
    }

    T getFirst();

    T getLast();

    default T remove() {
        return removeFirst();
    }

    T removeFirst();

    T removeLast();

    int size();

    boolean isEmpty();
}
