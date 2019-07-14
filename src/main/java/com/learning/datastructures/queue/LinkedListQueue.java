package com.learning.datastructures.queue;

import com.learning.datastructures.linked_list.SinglyLinkedList;

public class LinkedListQueue<T> implements Queue<T> {
    private final SinglyLinkedList<T> elements;

    public LinkedListQueue() {
        this.elements = new SinglyLinkedList<>();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public void insert(T e) {
        elements.addLast(e);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T remove() {
        if (elements.isEmpty()) throw new IllegalStateException("can not remove from empty queue");

        return elements.removeFirst();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T peek() {
        if (elements.isEmpty()) throw new IllegalStateException("can not peek from empty queue");

        return elements.getFirst();
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
