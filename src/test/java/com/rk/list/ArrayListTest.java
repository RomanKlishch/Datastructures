package com.rk.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArrayListTest extends AbstractListTest{

    @Override
    List getInstance() {
        return new ArrayList<String>();
    }

    @Test
    void addToEmptyArray_shouldIncreaseSize() {
        List<String> strings = new ArrayList<>(0);
        strings.add("qqq");
        strings.add(null);
        strings.add("www");
        assertEquals(3, strings.size());
    }


}