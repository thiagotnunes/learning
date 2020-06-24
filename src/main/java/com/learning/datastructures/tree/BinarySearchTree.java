package com.learning.datastructures.tree;

public interface BinarySearchTree<T extends Comparable<T>> {
    void add(T e);

    boolean contains(T e);

    int size();
}
