package com.learning.datastructures.stack;

public class Stack<T> {
    public static final int DEFAULT_CAPACITY = 100;

    private final Class<T> clazz;
    private T[] elements;
    private int capacity;
    private int size;
    private int top;

    public Stack(Class<T> clazz) {
        this(DEFAULT_CAPACITY, clazz);
    }

    @SuppressWarnings("unchecked")
    public Stack(int capacity, Class<T> clazz) {
        this.capacity = capacity;
        this.clazz = clazz;
        this.size = 0;
        this.top = -1;
        this.elements = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void push(T e) {
        tryExpand();

        size++;
        elements[++top] = e;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("can not pop from empty stack");

        T e = elements[top];
        top--;
        size--;

        tryShrink();

        return elements[top--];
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("can not peek from empty stack");

        return elements[top];
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public int size() {
        return size;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(n)
     */
    @SuppressWarnings("unchecked")
    private void tryExpand() {
        if (size < capacity) return;

        int newCapacity = capacity * 2;
        T[] newElements = (T[]) java.lang.reflect.Array.newInstance(clazz, newCapacity);

        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);

        capacity = newCapacity;
        elements = newElements;
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(n)
     */
    @SuppressWarnings("unchecked")
    private void tryShrink() {
        if (size > capacity / 3) return;

        int newCapacity = capacity / 3;
        T[] newElements = (T[]) java.lang.reflect.Array.newInstance(clazz, newCapacity);

        if (size >= 0) System.arraycopy(elements, 0, newElements, 0, size);

        capacity = newCapacity;
        elements = newElements;
    }
}
