package com.rk.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private List<String> defaultList;

    abstract List<String> getInstance();

    @BeforeEach
    public void each() {
        defaultList = getInstance();
        defaultList.add("qqq");
        defaultList.add(null);
        defaultList.add("www");
    }

    @Test
    void addIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.add("zzz", 4));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void addIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.add("zzz", -2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void add_ShouldIncreaseSize() {
        defaultList.add("eee");
        assertEquals(4, defaultList.size());
    }

    @Test
    void addNull_ShouldIncreaseSize() {
        defaultList.add(null);
        assertEquals(4, defaultList.size());
    }

    @Test
    void add_ShouldReturnAddedElement() {
        defaultList.add("eee");
        assertEquals("eee", defaultList.get(defaultList.size() - 1));
    }

    @Test
    void addByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultList.add("eee", 0);
        assertEquals("eee", defaultList.get(0));
        assertEquals(4, defaultList.size());
    }

    @Test
    void addNullByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultList.add(null, 0);
        assertNull(defaultList.get(0));
        assertEquals(4, defaultList.size());
    }


    @Test
    void remove_shouldReduceSize() {
        defaultList.remove(1);
        assertEquals(2, defaultList.size());
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
        assertEquals("qqq", defaultList.remove(0));
    }

    @Test
    void removeLast_shouldReturnRemoveObject() {
        assertEquals("www", defaultList.remove(defaultList.size() - 1));
    }

    @Test
    void removeNull_shouldReturnRemoveObject() {
        assertNull(defaultList.remove(1));
    }

    @Test
    void removeIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.remove(3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void removeIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.remove(-2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void get_ShouldReturnZeroElementList() {
        assertEquals("qqq", defaultList.get(0));
    }

    @Test
    void get_ShouldReturnLastElementList() {
        assertEquals("www", defaultList.get(defaultList.size() - 1));
    }

    @Test
    void get_ShouldReturnNull() {
        assertNull(defaultList.get(1));
    }

    @Test
    void getIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.get(3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void getIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.get(-2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void set_shouldReturnOldValue() {
        assertEquals("www", defaultList.set("sss", 2));
    }

    @Test
    void set_shouldChangeValueAndNotChangeSize() {
        defaultList.set("sss", 2);
        assertEquals("sss", defaultList.get(2));
        assertEquals(3, defaultList.size());
    }

    @Test
    void setIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.set("a", 3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void setIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.set("s", -2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void clear_shouldReturnEmptyList() {
        defaultList.clear();
        assertEquals(0, defaultList.size());
    }

    @Test
    void size_shouldReturnListSize() {
        assertEquals(3, defaultList.size());
    }

    @Test
    void notEmptyList_shouldReturnFalse() {
        assertFalse(defaultList.isEmpty());
    }

    @Test
    void emptyList_shouldReturnTrue() {
        assertTrue(new ArrayList<String>().isEmpty());
    }

    @Test
    void containsNotValidValue_shouldReturnFalse() {
        assertFalse(defaultList.contains("ttt"));
    }

    @Test
    void containsValidValue_shouldReturnTrue() {
        assertTrue(defaultList.contains("qqq"));
    }

    @Test
    void containsNull_shouldReturnTrue() {
        assertTrue(defaultList.contains(null));
    }

    @Test
    void indexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultList.indexOf("www"));
    }

    @Test
    void indexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultList.indexOf("ttt"));
    }

    @Test
    void indexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultList.indexOf(null));
    }

    @Test
    void lastIndexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultList.lastIndexOf("www"));
    }

    @Test
    void lastIndexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultList.lastIndexOf("ttt"));
    }

    @Test
    void lastIndexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultList.lastIndexOf(null));
    }

    @Test
    void testToString() {
        assertEquals("[qqq, null, www]", defaultList.toString());
        assertEquals("[]", new ArrayList<String>().toString());
    }

    @Test
    void iterator_shouldIterateList() {
        StringBuilder allList = new StringBuilder();
        Iterator<String> stringIterator = defaultList.iterator();
        while (stringIterator.hasNext()) {
            allList.append(stringIterator.next());
        }
        assertEquals("qqqnullwww", allList.toString());

    }
}
