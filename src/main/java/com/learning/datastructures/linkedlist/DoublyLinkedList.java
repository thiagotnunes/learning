package com.learning.datastructures.linkedlist;

public class DoublyLinkedList<T> implements LinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Time complexity - O(n)
     * Space complexity - O(n)
     */
    public static <T> DoublyLinkedList<T> of(T... elements) {
        DoublyLinkedList<T> list = new DoublyLinkedList<>();

        for (int i = elements.length - 1; i >= 0; i--) {
            list.addFirst(elements[i]);
        }

        return list;
    }

    public DoublyLinkedList() {
        this.size = 0;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public void addFirst(T e) {
        Node<T> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public void addLast(T e) {
        Node<T> newNode = new Node<>(e);
        if (tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T getFirst() {
        return head == null ? null : head.value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T getLast() {
        return tail == null ? null : tail.value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T removeFirst() {
        if (head == null) return null;

        T value = head.value;
        head = head.next;
        size--;
        if (size == 0) tail = null;

        return value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    @Override
    public T removeLast() {
        if (tail == null) return null;

        T value = tail.value;
        tail = tail.prev;
        size--;
        if (size == 0) head = null;

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

    private static class Node<T> {
        T value;
        Node<T> prev;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
