package com.rk.list.linkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    LinkedList<String> defaultList;

    @BeforeEach
    public void setUp(){
        defaultList = new LinkedList<>();
        defaultList.add("qqq");
        defaultList.add("www");
        defaultList.add("eee");
        defaultList.add("rrr");
        defaultList.add("ttt");
        defaultList.add("yyy");

    }

    @Test
    void size() {
        assertEquals(6, defaultList.size());
    }

    @Test
    void add() {
       defaultList.add("sss");
       assertEquals(7,defaultList.size());
    }

    @Test
    void testAdd() {
        defaultList.add(5,"ssss");
        assertEquals(7,defaultList.size());
        assertEquals("ssss",defaultList.get(5));

    }

    @Test
    void remove() {
        assertNotEquals("qqq",defaultList.get(0));
        assertEquals(5,defaultList.size());
    }

    @Test
    void get() {
        assertEquals("www",defaultList.get(1));
        assertEquals("yyy",defaultList.get(5));
    }

    @Test
    void set() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }

    @Test
    void clear() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
    }

    @Test
    void testToString() {
    }

    @Test
    void listIterator() {
    }
}