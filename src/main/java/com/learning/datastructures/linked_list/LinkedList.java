package com.learning.datastructures.linked_list;

import java.util.ArrayList;

public class LinkedList<T> {
    private Node<T> head;
    private Integer size;

    public LinkedList() {
        this.size = 0;
    }

    // O(1)
    public Integer size() {
        return size;
    }

    // O(1)
    public Boolean isEmpty() {
        return size == 0;
    }

    // O(n)
    public T valueAt(Integer index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " does not exist");
        }

        Node<T> current = head;
        for (int i = 1; i <= index; i++) {
            current = current.getNext();
        }

        return current.getValue();
    }

    // O(1)
    public void pushFront(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    // O(1)
    public T popFront() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("list is empty");
        }

        T value = head.getValue();

        head = head.getNext();
        size--;

        return value;
    }

    // O(n)
    public void pushBack(T value) {
        if (isEmpty()) {
            pushFront(value);
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(new Node<>(value));
            size++;
        }
    }

    // O(n)
    public T popBack() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("list is empty");
        } else if (size == 1) {
            T value = head.getValue();
            head = null;
            size--;

            return value;
        } else {
            Node<T> current = head;
            while (current.getNext().getNext() != null) {
                current = current.getNext();
            }

            T value = current.getNext().getValue();
            current.setNext(null);
            size--;

            return value;
        }
    }

    // O(1)
    public T front() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("list is empty");
        }

        return head.getValue();
    }

    // O(n)
    public T back() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("list is empty");
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            current = current.getNext();
        }

        return current.getValue();
    }

    // O(n)
    public void insert(Integer index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(index + " does not exist");
        } else if (index == 0) {
            Node<T> newNode = new Node<>(value);
            newNode.setNext(head);
            head = newNode;
            size++;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            Node<T> newNode = new Node<>(value);
            newNode.setNext(current.getNext());
            current.setNext(newNode);
            size++;
        }
    }

    // O(n)
    public void erase(Integer index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(index + " does not exist");
        } else if (index == 0) {
            head = head.getNext();
            size--;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext());
            size--;
        }
    }

    // O(n)
    public T valueNFromEnd(Integer n) {
        Node<T> endPointer = head;
        for (int i = 0; i < n; i++) {
            if (endPointer == null) {
                break;
            } else {
                endPointer = endPointer.getNext();
            }
        }

        Node<T> current = head;
        while (endPointer != null) {
            current = current.getNext();
            endPointer = endPointer.getNext();
        }

        return current == null ? null : current.getValue();
    }

    // O(n)
    public void reverse() {
        Node<T> current = head;
        Node<T> previousNode = null;
        Node<T> nextNode = null;

        while (current != null) {
            nextNode = current.getNext();
            current.setNext(previousNode);
            previousNode = current;
            current = nextNode;
        }

        head = previousNode;
    }

    // O(n)
    public void removeValue(T value) {
        if (isEmpty()) {
            return;
        }

        if (head.getValue() == value) {
            head = head.getNext();
        } else {
            Node<T> current = head;

            // a -> b -> c
            while (current.getNext() != null) {
                if (current.getNext().getValue() == value) {
                    current.setNext(current.getNext().getNext());
                    size--;
                    break;
                } else {
                    current = current.getNext();
                }
            }
        }
    }

    // O(n)
    public ArrayList<T> toArray() {
        Node<T> current = head;
        ArrayList<T> array = new ArrayList<>(size);

        while (current != null) {
            array.add(current.getValue());
            current = current.getNext();
        }

        return array;
    }
}
