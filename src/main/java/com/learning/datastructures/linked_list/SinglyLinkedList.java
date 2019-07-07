package com.learning.datastructures.linked_list;

public class SinglyLinkedList<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Time complexity - O(n)
     * Space complexity - O(n)
     */
    public static <T> SinglyLinkedList<T> of(T... elements) {
        SinglyLinkedList<T> list = new SinglyLinkedList<>();

        for (int i = elements.length - 1; i >= 0; i--) {
            list.addFirst(elements[i]);
        }

        return list;
    }

    public SinglyLinkedList() {
        size = 0;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void add(T e) {
        addFirst(e);
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void addFirst(T e) {
        Node<T> newNode = new Node<>(e);
        if (head == null) {
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public void addLast(T e) {
        Node<T> newNode = new Node<>(e);
        if (tail == null) {
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        size++;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T getFirst() {
        return head == null ? null : head.value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T getLast() {
        return tail == null ? null : tail.value;
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T remove() {
        return removeFirst();
    }

    /**
     * Time complexity - O(1)
     * Space complexity - O(1)
     */
    public T removeFirst() {
        if (head == null) return null;

        T value = head.value;
        head = head.next;
        size--;
        if (size == 0) tail = null;

        return value;
    }

    /**
     * Time complexity - O(n)
     * Space complexity - O(1)
     */
    public T removeLast() {
        if (tail == null) return null;

        T value = tail.value;
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            Node<T> current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }

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
        return size == 0;
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        Node(T value) {
            this.value = value;
        }
    }
}
