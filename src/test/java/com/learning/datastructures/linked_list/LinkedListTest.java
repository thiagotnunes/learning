package com.learning.datastructures.linked_list;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class LinkedListTest {

    @Test
    public void returnsNElementFromTheEnd() {
        LinkedList<Integer> list = new LinkedList<>();

        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        list.pushFront(5);

        assertThat(list.valueNFromEnd(2), equalTo(2));
    }

    @Test
    public void reversesList() {
        LinkedList<Integer> list = new LinkedList<>();

        list.pushFront(1);
        list.pushFront(2);
        list.pushFront(3);
        list.pushFront(4);
        list.pushFront(5);

        list.reverse();

        assertThat(list.toArray(), equalTo(Arrays.asList(1, 2, 3, 4, 5)));
    }

}