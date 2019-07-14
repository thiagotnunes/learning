package com.learning.datastructures.stack;

public interface Stack<T> {
    void push(T e);

    T pop();

    T peek();

    int size();

    boolean isEmpty();
}
