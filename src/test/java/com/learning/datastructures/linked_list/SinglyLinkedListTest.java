package com.learning.datastructures.linked_list;

import com.learning.datastructures.linkedlist.SinglyLinkedList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class SinglyLinkedListTest {

    @Test
    public void returnsLinkedList() {
        SinglyLinkedList<Integer> list = SinglyLinkedList.of(1, 2, 3, 4);

        assertThat(list.size(), equalTo(4));
        assertThat(list.isEmpty(), equalTo(false));
        assertThat(list.removeFirst(), equalTo(1));
        assertThat(list.removeFirst(), equalTo(2));
        assertThat(list.removeFirst(), equalTo(3));
        assertThat(list.removeFirst(), equalTo(4));
        assertThat(list.removeFirst(), equalTo(null));
        assertThat(list.size(), equalTo(0));
        assertThat(list.isEmpty(), equalTo(true));
    }
}