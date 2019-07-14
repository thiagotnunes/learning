package com.learning.datastructures.queue;

public class SizedCircularArrayQueue<T> implements BoundedQueue<T> {
    private final int capacity;
    private final Class<T> clazz;
    private final T[] elements;
    private int size;
    private int start;
    private int end;

    @SuppressWarnings("unchecked")
    public SizedCircularArrayQueue(int capacity, Class<T> clazz) {
        this.capacity = capacity;
        this.clazz = clazz;
        this.elements = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity);
        this.size = 0;
        this.start = 0;
        this.end = 0;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public void insert(T e) {
        if (isFull()) throw new IllegalStateException("can not insert into full queue");

        elements[end] = e;
        end = (end + 1) % capacity;
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T remove() {
        if (isEmpty()) throw new IllegalStateException("can not remove from empty queue");

        T e = elements[start];
        start = (start + 1) % capacity;
        size--;

        return e;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("can not peek from empty queue");

        return elements[start];
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }
}
