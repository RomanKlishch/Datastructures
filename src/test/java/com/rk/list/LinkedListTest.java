package com.rk.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class LinkedListTest extends AbstractListTest {

    @Override
    List getInstance() {
        return new LinkedList<String>();
    }

    @Test
    void addByIndexInEmptyList_shouldIncreaseSize() {
        List<String> strings = new LinkedList<>();
        strings.add("eee", 0);
        assertEquals(1, strings.size());
    }
}
