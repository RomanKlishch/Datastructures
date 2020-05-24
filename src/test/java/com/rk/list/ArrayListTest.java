package com.rk.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<String> defaultArrayList;

    @BeforeEach
    public void each() {
        defaultArrayList = new ArrayList<>();
        defaultArrayList.add("qqq");
        defaultArrayList.add(null);
        defaultArrayList.add("www");
    }

    @Test
    void addIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.add("zzz", 4));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void addIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.add("zzz", -2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void add_ShouldIncreaseSize() {
        defaultArrayList.add("eee");
        assertEquals(4, defaultArrayList.size());
    }

    @Test
    void addNull_ShouldIncreaseSize() {
        defaultArrayList.add(null);
        assertEquals(4, defaultArrayList.size());
    }

    @Test
    void add_ShouldReturnAddedElement() {
        defaultArrayList.add("eee");
        assertEquals("eee", defaultArrayList.get(defaultArrayList.size() - 1));
    }

    @Test
    void addByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultArrayList.add("eee", 0);
        assertEquals("eee", defaultArrayList.get(0));
        assertEquals(4, defaultArrayList.size());
    }

    @Test
    void addNullByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultArrayList.add(null, 0);
        assertNull(defaultArrayList.get(0));
        assertEquals(4, defaultArrayList.size());
    }


    @Test
    void remove_shouldReduceSize() {
        defaultArrayList.remove(1);
        assertEquals(2, defaultArrayList.size());
    }

    @Test
    void removeFromEmptyArray_shouldReturnException() {
        List<String> list = new ArrayList<>();
        Throwable thrownRemove = assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(0));
        assertEquals(IndexOutOfBoundsException.class, thrownRemove.getClass());
    }

    @Test
    void removeFirst_shouldReturnRemoveObject() {
        assertEquals("qqq", defaultArrayList.remove(0));
    }

    @Test
    void removeLast_shouldReturnRemoveObject() {
        assertEquals("www", defaultArrayList.remove(defaultArrayList.size() - 1));
    }

    @Test
    void removeNull_shouldReturnRemoveObject() {
        assertNull(defaultArrayList.remove(1));
    }

    @Test
    void removeIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.remove(3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void removeIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.remove(-2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void get_ShouldReturnZeroElementList() {
        assertEquals("qqq", defaultArrayList.get(0));
    }

    @Test
    void get_ShouldReturnLastElementList() {
        assertEquals("www", defaultArrayList.get(defaultArrayList.size() - 1));
    }

    @Test
    void get_ShouldReturnNull() {
        assertNull(defaultArrayList.get(1));
    }

    @Test
    void getIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.get(3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void getIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.get(-2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void set_shouldReturnOldValue() {
        assertEquals("www", defaultArrayList.set("sss", 2));
    }

    @Test
    void set_shouldChangeValueAndNotChangeSize() {
        defaultArrayList.set("sss", 2);
        assertEquals("sss", defaultArrayList.get(2));
        assertEquals(3, defaultArrayList.size());
    }

    @Test
    void setIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.set("a", 3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void setIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultArrayList.set("s", -2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void clear_shouldReturnEmptyList() {
        defaultArrayList.clear();
        assertEquals(0, defaultArrayList.size());
    }

    @Test
    void size_shouldReturnListSize() {
        assertEquals(3, defaultArrayList.size());
    }

    @Test
    void notEmptyList_shouldReturnFalse() {
        assertFalse(defaultArrayList.isEmpty());
    }

    @Test
    void emptyList_shouldReturnTrue() {
        assertTrue(new ArrayList<String>().isEmpty());
    }

    @Test
    void containsNotValidValue_shouldReturnFalse() {
        assertFalse(defaultArrayList.contains("ttt"));
    }

    @Test
    void containsValidValue_shouldReturnTrue() {
        assertTrue(defaultArrayList.contains("qqq"));
    }

    @Test
    void containsNull_shouldReturnTrue() {
        assertTrue(defaultArrayList.contains(null));
    }

    @Test
    void indexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultArrayList.indexOf("www"));
    }

    @Test
    void indexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultArrayList.indexOf("ttt"));
    }

    @Test
    void indexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultArrayList.indexOf(null));
    }

    @Test
    void lastIndexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultArrayList.lastIndexOf("www"));
    }

    @Test
    void lastIndexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultArrayList.lastIndexOf("ttt"));
    }

    @Test
    void lastIndexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultArrayList.lastIndexOf(null));
    }

    @Test
    void testToString() {
        assertEquals("[qqq, null, www]", defaultArrayList.toString());
        assertEquals("[]", new ArrayList<String>().toString());
    }

    @Test
    void iterator_shouldIterateList() {
        String allList = "";
        Iterator<String> stringIterator = defaultArrayList.iterator();
        while (stringIterator.hasNext()){
            allList+=stringIterator.next();
        }
        assertEquals("qqqnullwww", allList);

    }


}