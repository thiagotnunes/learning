package com.learning.datastructures.stack;

import com.learning.datastructures.linkedlist.SinglyLinkedList;

public class LinkedListStack<T> implements Stack<T> {

    private final SinglyLinkedList<T> elements;

    public LinkedListStack() {
        this.elements = new SinglyLinkedList<T>();
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
