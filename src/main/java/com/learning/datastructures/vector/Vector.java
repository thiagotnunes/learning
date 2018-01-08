package com.learning.datastructures.vector;

import java.lang.reflect.Array;

public class Vector<T> {
    public static Integer INITIAL_CAPACITY = 16;

    private Integer size;
    private Integer capacity;
    private Class<T> clazz;
    private T[] elements;

    @SuppressWarnings("unchecked")
    public Vector(Class<T> clazz, Integer capacity) {
        this.clazz = clazz;
        this.elements = (T[]) Array.newInstance(clazz, capacity);
        this.capacity = capacity;
        this.size = 0;
    }

    public Vector(Class<T> clazz) {
        this(clazz, INITIAL_CAPACITY);
    }

    // O(1)
    public Integer size() {
        return size;
    }

    // O(1)
    public Integer capacity() {
        return capacity;
    }

    // O(1)
    public Boolean isEmpty() {
        return size == 0;
    }

    // O(1)
    public T at(Integer i) {
        if (i >= size) {
            throw new ArrayIndexOutOfBoundsException(i);
        }

        return elements[i];
    }

    // O(1) - amortized
    public void push(T element) {
        if (size >= capacity) {
            resize(capacity * 2);
        }

        elements[size] = element;

        size++;
    }

    // O(n)
    public void insert(Integer index, T element) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        push(element);

        for (int i = size - 1; i > index; i--) {
            T tmp = elements[i - 1];
            elements[i - 1] = elements[i];
            elements[i] = tmp;
        }
    }

    // O(n)
    public void prepend(T element) {
        insert(0, element);
    }

    // O(1) -- amortized
    public T pop() {
        T element = elements[size - 1];

        size--;

        if (size <= capacity / 4) {
            resize(capacity / 2);
        }

        return element;
    }

    // O(n)
    public void delete(Integer index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        size--;

        if (size <= capacity / 4) {
            resize(capacity / 2);
        }
    }

    // O(n)
    public void remove(T element) {
        int i = 0;
        do {
            if (elements[i] == element) {
                delete(i);
            } else {
                i++;
            }
        } while (i < size);
    }

    // O(n)
    public Integer find(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element) {
                return i;
            }
        }

        return -1;
    }

    // O(n)
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] copyArray = (T[]) Array.newInstance(clazz, size);

        for (int i = 0; i < size; i++) {
            copyArray[i] = elements[i];
        }

        return copyArray;
    }

    // O(n)
    @SuppressWarnings("unchecked")
    private void resize(Integer newCapacity) {
        capacity = newCapacity;
        T[] newArray = (T[]) Array.newInstance(clazz, newCapacity);
        System.arraycopy(elements, 0, newArray, 0, size);
        elements = newArray;
    }
}
