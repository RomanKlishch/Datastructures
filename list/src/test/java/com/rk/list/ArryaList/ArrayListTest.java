package com.rk.list.ArryaList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ArrayListTest {

    private ArrayList<String> defaultArrayList;
    private ArrayList<Integer> arrayListWithParametr;

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
        assertEquals(4,defaultArrayList.size());
    }

    @Test
    void testAddByIndex() {
        defaultArrayList.add("sss",2);
        for (int i = 0; i < defaultArrayList.size(); i++) {
            System.out.println(defaultArrayList.get(i));
        }
        assertEquals(5,defaultArrayList.size());
        assertEquals("sss",defaultArrayList.get(2));
    }
    @Test
    void testAddByIndex_shouldReturnException() {

    }

    @Test
    void remove() {
    }

    @Test
    void get() {
    }

    @Test
    void set() {
    }

    @Test
    void clear() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
    }

    @Test
    void indexOf() {
    }

    @Test
    void lastIndexOf() {
    }
}