package com.learning.datastructures.queue;

public interface BoundedQueue<T> extends Queue<T> {
    boolean isFull();
}
