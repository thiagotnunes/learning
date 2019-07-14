package com.learning.datastructures.queue;

public class PointerCircularArrayQueue<T> implements BoundedQueue<T> {
    private final int capacity;
    private final Class<T> clazz;
    private final T[] elements;
    private int start;
    private int end;

    @SuppressWarnings("unchecked")
    public PointerCircularArrayQueue(int capacity, Class<T> clazz) {
        this.capacity = capacity + 1;
        this.clazz = clazz;
        this.elements = (T[]) java.lang.reflect.Array.newInstance(clazz, capacity + 1);
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

        return e;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("can not remove from empty queue");

        return elements[start];
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public int size() {
        return (end - start + capacity) % capacity;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public boolean isEmpty() {
        return start == end;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public boolean isFull() {
        return (end + 1) % capacity == start;
    }
}
