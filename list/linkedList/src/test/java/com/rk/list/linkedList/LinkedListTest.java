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

    }


    @Test
    void size() {
        assertEquals(4, defaultList.size());
    }

    @Test
    void add() {
       defaultList.add("sss");
       assertEquals(5,defaultList.size());
    }

    @Test
    void testAdd() {
        defaultList.add(4,"ssss");
        assertEquals(5,defaultList.size());
        assertEquals("ssss",defaultList.get(4));
    }

    @Test
    void remove() {
    }

    @Test
    void get() {
        assertEquals("rrr",defaultList.get(3));
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