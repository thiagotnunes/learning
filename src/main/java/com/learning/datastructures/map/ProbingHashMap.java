package com.learning.datastructures.map;

import java.util.function.Function;

public class ProbingHashMap<K, V> implements Map<K, V> {
    public static final int DEFAULT_CAPACITY = 101;

    public static <K, V> ProbingHashMap<K, V> linearProbingHashMap(final int capacity) {
        return new ProbingHashMap<>(capacity, (Function<Integer, Integer>) i -> (i + 1) % capacity);
    }

    public static <K, V> ProbingHashMap linearProbingHashMap() {
        return ProbingHashMap.<K, V>linearProbingHashMap(DEFAULT_CAPACITY);
    }

    private final int capacity;
    private final Node<K, V>[] elements;
    private final Function<Integer, Integer> probe;
    private int size;


    @SuppressWarnings("unchecked")
    private ProbingHashMap(int capacity, Function<Integer, Integer> probe) {
        this.capacity = capacity;
        this.elements = (Node<K, V>[]) new Node[capacity];
        this.probe = probe;
        this.size = 0;
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public void put(K key, V value) {
        int i = key.hashCode() % capacity;

        int seen = 0;
        Node<K, V> current = elements[i];
        while (!isNullOrDeleted(current) && !current.hasKey(key) && seen < size) {
            i = probe.apply(i);
            current = elements[i];
            seen++;
        }
        if (seen >= capacity) {
            throw new IllegalStateException("can not put into full map");
        } else if (isNullOrDeleted(current)) {
            elements[i] = new Node<>(key, value);
            size++;
        } else {
            current.value = value;
        }
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public V get(K key) {
        int i = key.hashCode() % capacity;

        int seen = 0;
        Node<K, V> current = elements[i];
        while (isNullOrDeleted(current) || !current.hasKey(key) && seen < size) {
            i = probe.apply(i);
            current = elements[i];
            seen++;
        }
        if (isNullOrDeleted(current) || !current.hasKey(key)) {
            return null;
        } else {
            return current.value;
        }
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public V remove(K key) {
        int i = key.hashCode() % capacity;

        int seen = 0;
        Node<K, V> current = elements[i];
        while (isNullOrDeleted(current) || !current.hasKey(key) && seen < size) {
            i = probe.apply(i);
            current = elements[i];
            seen++;
        }
        if (isNullOrDeleted(current) || !current.hasKey(key)) {
            return null;
        } else {
            V value = current.value;
            elements[i].isDeleted = true;
            size--;
            return value;
        }
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
    public boolean isFull() {
        return size == capacity;
    }

    private boolean isNullOrDeleted(Node<K, V> node) {
        return node == null || node.isDeleted;
    }

    private static class Node<K, V> {
        private final K key;
        private V value;
        private boolean isDeleted;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }

        boolean hasKey(K key) {
            return !isDeleted && this.key.equals(key);
        }
    }
}
