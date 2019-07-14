package com.learning.datastructures.stack;

import com.learning.datastructures.array.Array;

public class ArrayStack<T> implements Stack<T> {
    public static final int DEFAULT_CAPACITY = 100;
    private final Array<T> elements;

    public ArrayStack(Class<T> clazz) {
        this(DEFAULT_CAPACITY, clazz);
    }

    public ArrayStack(int capacity, Class<T> clazz) {
        this.elements = new Array<T>(capacity, clazz);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public void push(T e) {
        elements.addLast(e);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T pop() {
        if (elements.isEmpty()) throw new IllegalStateException("can not peek from empty stack");

        return elements.removeLast();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T peek() {
        if (elements.isEmpty()) throw new IllegalStateException("can not peek from empty stack");

        return elements.getLast();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public int size() {
        return elements.size();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }
}
