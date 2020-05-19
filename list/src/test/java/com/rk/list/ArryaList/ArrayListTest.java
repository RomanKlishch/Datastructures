package com.rk.list.ArryaList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ArrayListTest {

    private ArrayList<String> defaultArrayList;


    @BeforeEach
    public void each(){
        defaultArrayList = new ArrayList<>();
        defaultArrayList.add("qqq");
        defaultArrayList.add("www");
        defaultArrayList.add("eee");
        defaultArrayList.add("rrr");
    }


    @Test
    void add() {
        defaultArrayList.add("rrr");
        assertEquals("rrr",defaultArrayList.get(defaultArrayList.size()-1));
    }

    @Test
    void addByIndex() {
        defaultArrayList.add("sss",2);
        assertEquals("sss",defaultArrayList.get(2));
    }
    @Test
    void addByIndex_shouldReturnException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class,
                ()->defaultArrayList.add("sss",20));
    }

    @Test
    void removeExpectedObject() {
        assertEquals("qqq",defaultArrayList.remove(0));
        defaultArrayList.remove(0);
    }
    @Test
    void remove() {
        defaultArrayList.remove(0);
        assertEquals(3,defaultArrayList.size());
    }

    @Test
    void get() {
        assertEquals("qqq",defaultArrayList.get(0));
    }

    @Test
    void set() {
        defaultArrayList.set("sss",2);
        assertEquals("sss",defaultArrayList.get(2));
    }
    //TODO: как правильно проверить clear() ?
    @Test
    void clear() {
        defaultArrayList.clear();
        assertEquals(0,defaultArrayList.size());
    }

    @Test
    void size() {
        assertEquals(4,defaultArrayList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(new ArrayList<String>().isEmpty());
        assertFalse(defaultArrayList.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(defaultArrayList.contains("ttt"));
        assertTrue(defaultArrayList.contains("qqq"));
    }

    @Test
    void indexOf() {
        assertEquals(1,defaultArrayList.indexOf("www"));
        assertEquals(-1,defaultArrayList.indexOf("ttt"));
    }

    @Test
    void lastIndexOf() {
        assertEquals(1,defaultArrayList.indexOf("www"));
        assertEquals(-1,defaultArrayList.indexOf("ttt"));
    }

    @Test
    void testToString() {
        assertEquals("[qqq, www, eee, rrr]",defaultArrayList.toString());
    }
}