package com.rk.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractListTest {
    private List<String> defaultList;

    abstract List<String> getInstance();

    @BeforeEach
    public void each() {
        defaultList = getInstance();
        defaultList.add("A");
        defaultList.add(null);
        defaultList.add("C");
    }

    @Test
    void addIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.add("D", 4));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void addIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.add("D", -2));
        assertEquals(IndexOutOfBoundsException.class, thrownLessThenZero.getClass());

    }

    @Test
    void add_ShouldIncreaseSize() {
        defaultList.add("D");
        assertEquals(4, defaultList.size());
    }

    @Test
    void addNull_ShouldIncreaseSize() {
        defaultList.add(null);
        assertEquals(4, defaultList.size());
    }

    @Test
    void add_ShouldReturnAddedElement() {
        defaultList.add("D");
        assertEquals("D", defaultList.get(defaultList.size() - 1));
    }

    @Test
    void addByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultList.add("D", 0);
        assertEquals("D", defaultList.get(0));
        assertEquals(4, defaultList.size());
    }

    @Test
    void addNullByZeroIndex_shouldReturnAddedElementAndIncreaseSize() {
        defaultList.add(null, 0);
        assertNull(defaultList.get(0));
        assertEquals(4, defaultList.size());
    }

    @Test
    void addByIndexInEmptyList_shouldIncreaseSize() {
        List<String> strings = new LinkedList<>();
        strings.add("D", 0);
        assertEquals(1, strings.size());
    }

    @Test
    void remove_shouldReduceSize() {
        defaultList.remove(1);
        assertEquals(2, defaultList.size());
    }

    @Test
    void removeFromEmptyArray_shouldReturnException() {
        List<String> list = getInstance();
        Throwable thrownRemove = assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(0));
        assertEquals(IndexOutOfBoundsException.class, thrownRemove.getClass());
    }

    @Test
    void removeFirst_shouldReturnRemovedObject() {
        assertEquals("A", defaultList.remove(0));
    }

    @Test
    void removeElementWhenSizeOne_shouldReturnRemovedObjectAndReduceSize() {
        List<String> strings = getInstance();
        strings.add("A");
        assertEquals("A", strings.remove(0));
        assertEquals(0, strings.size());
    }

    @Test
    void removeLast_shouldReturnRemovedObject() {
        assertEquals("C", defaultList.remove(defaultList.size() - 1));
    }

    @Test
    void removeNull_shouldReturnRemovedObject() {
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
        assertEquals("A", defaultList.get(0));
    }

    @Test
    void get_ShouldReturnLastElementList() {
        assertEquals("C", defaultList.get(defaultList.size() - 1));
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
    void set_shouldReturnOldValueAndNotChangeAnotherValue() {
        assertEquals("C", defaultList.set("D", 2));
        assertEquals("A", defaultList.get(0));
        assertNull(defaultList.get(1));
        assertEquals("D", defaultList.get(2));

    }

    @Test
    void set_shouldChangeValueAndNotChangeSize() {
        defaultList.set("D", 2);
        assertEquals("D", defaultList.get(2));
        assertEquals(3, defaultList.size());
    }

    @Test
    void setIndexMoreThenSize_shouldReturnException() {
        Throwable thrownMoreThenSize = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.set("D", 3));
        assertEquals(IndexOutOfBoundsException.class, thrownMoreThenSize.getClass());
    }

    @Test
    void setIndexLessThenZero_shouldReturnException() {
        Throwable thrownLessThenZero = assertThrows(IndexOutOfBoundsException.class,
                () -> defaultList.set("D", -2));
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
        assertFalse(defaultList.contains("D"));
    }

    @Test
    void containsValidValue_shouldReturnTrue() {
        assertTrue(defaultList.contains("A"));
    }

    @Test
    void containsNull_shouldReturnTrue() {
        assertTrue(defaultList.contains(null));
    }

    @Test
    void indexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultList.indexOf("C"));
    }

    @Test
    void indexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultList.indexOf("D"));
    }

    @Test
    void indexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultList.indexOf(null));
    }

    @Test
    void lastIndexOfValidValue_shouldReturnIndex() {
        assertEquals(2, defaultList.lastIndexOf("C"));
    }

    @Test
    void lastIndexOfNotValidValue_shouldReturnResult() {
        assertEquals(-1, defaultList.lastIndexOf("D"));
    }

    @Test
    void lastIndexOfNullValue_shouldReturnIndex() {
        assertEquals(1, defaultList.lastIndexOf(null));
    }

    @Test
    void testToString() {
        assertEquals("[A, null, C]", defaultList.toString());
        assertEquals("[]", getInstance().toString());
    }

    @Test
    void iterator_shouldIterateList() {
        StringBuilder allList = new StringBuilder();
        for (String s : defaultList) {
            allList.append(s);
        }
        assertEquals("AnullC", allList.toString());

    }

    @Test
    void iteratorRemoveLast_shouldRemoveElementAndReduceSize() {
        Iterator<String> iterator = defaultList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (Objects.equals(s, "C")) {
                iterator.remove();
            }
        }
        assertEquals(2, defaultList.size());
        assertEquals("A", defaultList.get(0));
        assertNull(defaultList.get(1));
    }

    @Test
    void iteratorRemoveFirst_shouldRemoveElementAndReduceSize() {
        Iterator<String> iterator = defaultList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (Objects.equals(s, "A")) {
                iterator.remove();
            }
        }
        assertEquals(2, defaultList.size());
        assertEquals("C", defaultList.get(1));
        assertNull(defaultList.get(0));
    }

    @Test
    void iteratorRemoveNull_shouldRemoveElementAndReduceSize() {
        Iterator<String> iterator = defaultList.iterator();
        while (iterator.hasNext()) {
            String s = iterator.next();
            if (Objects.equals(s, null)) {
                iterator.remove();
            }
        }
        assertEquals(2, defaultList.size());
        assertEquals("A", defaultList.get(0));
        assertEquals("C", defaultList.get(1));
    }
}
