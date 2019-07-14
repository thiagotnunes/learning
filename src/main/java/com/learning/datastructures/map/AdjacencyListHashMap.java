package com.learning.datastructures.map;

public class AdjacencyListHashMap<K, V> implements Map<K, V> {
    public static int DEFAULT_CAPACITY = 101;
    private final int capacity;
    private final Node<K, V>[] elements;
    private int size;

    public AdjacencyListHashMap() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public AdjacencyListHashMap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.elements = (Node<K, V>[]) new Node[capacity];
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public void put(K key, V value) {
        int i = key.hashCode() % capacity;

        if (elements[i] == null) {
            elements[i] = new Node<>(key, value);
            size++;
        } else {
            Node<K, V> current = elements[i];
            while (!current.key.equals(key) && current.next != null) current = current.next;

            if (current.key.equals(key)) {
                current.value = value;
            } else {
                current.next = new Node<>(key, value);
                size++;
            }
        }
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public V get(K key) {
        int i = key.hashCode() % capacity;

        if (elements[i] == null) {
            return null;
        } else {
            Node<K, V> current = elements[i];
            while (current != null && !current.key.equals(key)) current = current.next;

            return current != null ? current.value : null;
        }
    }

    /**
     * Time complexity - O(1) - amortized
     * Space complexity - O(1)
     */
    @Override
    public V remove(K key) {
        int i = key.hashCode() % capacity;
        V value = null;

        if (elements[i] != null) {
            Node<K, V> current = elements[i];
            if (current.key.equals(key)) {
                elements[i] = current.next;
                value = current.value;
                size--;
            } else {
                while (current.next != null && !current.next.key.equals(key)) current = current.next;

                if (current.next != null) {
                    value = current.next.value;
                    current.next = current.next.next;
                    size--;
                }
            }
        }

        return value;
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

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
}
