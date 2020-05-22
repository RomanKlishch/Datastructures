package com.rk.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<String> defaultArrayList;

    @BeforeEach
    public void each() {
        defaultArrayList = new ArrayList<>();
        defaultArrayList.add("qqq");
        defaultArrayList.add("www");
        defaultArrayList.add("eee");
        defaultArrayList.add(null);
    }
//TODO: правильные названия методов?

    @Test
    void add() {
        defaultArrayList.add("rrr");
        assertEquals("rrr", defaultArrayList.get(defaultArrayList.size() - 1));
    }

    @Test
    void addByIndex() {
        defaultArrayList.add("sss", 3);
        assertEquals("sss", defaultArrayList.get(3));
    }

    @Test
    void addIndexOutOfRange_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.add("zzz", 20));
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.add("zzz", -2));

        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void removeExpectedObject() {
        assertEquals("qqq", defaultArrayList.remove(0));
    }

    @Test
    void remove() {
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        assertEquals(0, defaultArrayList.size());
    }

    @Test
    void removeFromEmptyArray_shouldReturnException() {
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        defaultArrayList.remove(0);
        Throwable thrownRemove = assertThrows(RuntimeException.class,
                () -> defaultArrayList.remove(0));
        assertEquals(RuntimeException.class, thrownRemove.getClass());
    }

    @Test
    void get() {
        assertEquals("qqq", defaultArrayList.get(0));
    }

    @Test
    void set() {
        defaultArrayList.set("sss", 2);
        assertEquals("sss", defaultArrayList.get(2));
    }

    @Test
    void clear() {
        defaultArrayList.clear();
        assertEquals(0, defaultArrayList.size());
    }

    @Test
    void size() {
        assertEquals(4, defaultArrayList.size());
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
        assertEquals(1, defaultArrayList.indexOf("www"));
        assertEquals(-1, defaultArrayList.indexOf("ttt"));
    }

    @Test
    void indexOfNullValue() {
        assertEquals(3, defaultArrayList.indexOf(null));
    }

    @Test
    void lastIndexOf() {
        assertEquals(1, defaultArrayList.lastIndexOf("www"));
        assertEquals(-1, defaultArrayList.lastIndexOf("ttt"));
    }

    @Test
    void lastIndexOfNullValue() {
        assertEquals(3, defaultArrayList.lastIndexOf(null));
    }

    @Test
    void testToString() {
        assertEquals("[qqq, www, eee, null]", defaultArrayList.toString());
        assertEquals("[]", new ArrayList<String>().toString());
    }
}