package com.learning.datastructures.queue;

public interface Queue<T> {
    void insert(T e);

    T remove();

    T peek();

    int size();

    boolean isEmpty();
}
