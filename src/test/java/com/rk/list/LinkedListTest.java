package com.rk.list;

public class LinkedListTest extends AbstractListTest {

    @Override
    List getInstance() {
        return new LinkedList<String>();
    }

}
