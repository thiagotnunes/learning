package com.learning.datastructures.vector;

import com.learning.datastructures.vector.Vector;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class VectorTest {

    private Vector<Integer> vector;
    private Integer capacity;

    @Before
    public void setUp() {
        capacity = 4;
        vector = new Vector<>(Integer.class, capacity);
    }

    @Test
    public void duplicatesCapacityWhenItIsReached() {
        for (int i = 0; i < capacity; i++) {
            vector.push(i);
        }

        vector.push(999);

        assertThat(vector.capacity(), equalTo(capacity * 2));
    }

    @Test
    public void insertsAndShiftsElements() {
        vector.push(1);
        vector.push(2);
        vector.insert(1, 3);

        assertThat(vector.toArray(), equalTo(new Integer[]{1, 3, 2}));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsExceptionWhenInsertingOnNegativeIndex() {
        vector.insert(-1, 1);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void throwsExceptionWhenInsertingOnIndexTooHigh() {
        vector.push(1);

        vector.insert(2, 2);
    }

    @Test
    public void reducesCapacityWhenSizeIsAQuarterOfIt() {
        vector.push(1);
        vector.push(2);
        vector.push(3);
        vector.push(4);
        vector.push(5);

        int newCapacity = capacity * 2;
        assertThat(vector.capacity(), equalTo(newCapacity));

        vector.pop();
        vector.pop();
        vector.pop();
        vector.pop();

        newCapacity = newCapacity / 4;
        assertThat(vector.capacity(), equalTo(newCapacity));
    }

    @Test
    public void removesAllTheElementsThatMatchTheGivenOne() {
        vector.push(1);
        vector.push(2);
        vector.push(1);
        vector.push(3);
        vector.push(1);

        vector.remove(1);

        assertThat(vector.toArray(), equalTo(new Integer[]{2, 3}));
    }
}