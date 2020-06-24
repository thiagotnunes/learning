package com.learning.datastructures.tree;

public class UnbalancedBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private int size;
    private Node<T> root;

    public UnbalancedBinarySearchTree() {
        this.size = 0;
        this.root = null;
    }

    @Override
    public void add(T e) {
        Node<T> parent = null;
        Node<T> current = root;
        while (current != null) {
            if (current.value.compareTo(e) < 0) {
                parent = current;
                current = current.right;
            } else {
                parent = current;
                current = current.left;
            }
        }

        if (parent == null) {
            root = new Node<>(e);
        } else if (parent.value.compareTo(e) < 0) {
            parent.right = new Node<>(e);
        } else {
            parent.left = new Node<>(e);
        }
        size++;
    }

    @Override
    public boolean contains(T e) {
        Node<T> current = root;
        while (current != null && current.value.compareTo(e) != 0) {
            if (current.value.compareTo(e) < 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return current != null;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<T> {
        final T value;
        Node<T> left;
        Node<T> right;

        Node(T value) {
            this.value = value;
        }
    }
}
