package com.learning.datastructures.array;

public class Array<T> {
    public static final int DEFAULT_CAPACITY = 100;
    private final Class<T> clazz;
    private int capacity;
    private int size;
    private T[] elements;

    public Array(Class<T> clazz) {
        this(DEFAULT_CAPACITY, clazz);
    }

    @SuppressWarnings("unchecked")
    public Array(int initialCapacity, Class<T> clazz) {
        this.clazz = clazz;
        this.capacity = initialCapacity;
        this.size = 0;
        this.elements = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void add(T e) {
        addLast(e);
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    public void addFirst(T e) {
        tryExpand();

        int i = size - 1;
        while (i >= 0) {
            elements[i + 1] = elements[i];
            i--;
        }
        elements[0] = e;
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void addLast(T e) {
        tryExpand();

        elements[size] = e;
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T get(int i) {
        if (i >= size) throw new ArrayIndexOutOfBoundsException(i);

        return elements[i];
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T getFirst() {
        return get(0);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T getLast() {
        return get(size - 1);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void set(int i, T e) {
        if (i >= size) throw new ArrayIndexOutOfBoundsException(i);

        elements[i] = e;
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    public T remove() {
        return removeLast();
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    public T removeFirst() {
        if (size == 0) return null;

        T value = elements[0];
        if (size - 1 >= 0) System.arraycopy(elements, 1, elements, 0, size - 1);
        size--;

        return value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T removeLast() {
        if (size == 0) return null;

        T value = elements[size - 1];
        size--;
        tryShrink();

        return value;
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
        return size() == 0;
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
        if (size >= capacity / 3) return;

        int newCapacity = capacity / 3;
        T[] newElements = (T[]) java.lang.reflect.Array.newInstance(clazz, newCapacity);

        System.arraycopy(elements, 0, newElements, 0, size);

        capacity = newCapacity;
        elements = newElements;
    }
}
