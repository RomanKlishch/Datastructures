package com.rk.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashMapTest {

    HashMap<Integer, Integer> defaultMap;

    @BeforeEach
    public void each() {
        defaultMap = new HashMap<>();
        defaultMap.put(10, 1);
        defaultMap.put(20, 2);
        defaultMap.put(30, 3);
    }

    @Test
    public void put_shouldIncreaseSize() {
        defaultMap.put(40, 4);
        assertEquals(4, defaultMap.size());
    }

    @Test
    void putNew_shouldReturnAddedElement() {
        assertEquals(4, defaultMap.put(40, 4));
    }

    @Test
    void putUpdate_shouldReturnOldElementAndNotIncreaseSize() {
        assertEquals(1, defaultMap.put(10, 10));
        assertEquals(3, defaultMap.size());
    }

    @Test
    void putUpdate_shouldNotChangeValueAnotherElements() {
        defaultMap.put(20, 20);
        assertEquals(1, defaultMap.get(10));
        assertEquals(20, defaultMap.get(20));
        assertEquals(3, defaultMap.get(30));
    }

    @Test
    void putNullKey_shouldIncreaseSizeAndReturnValue() {
        assertEquals(10, defaultMap.put(null, 10));
        assertEquals(4, defaultMap.size());
    }

    @Test
    void putNullKeyNullValue_shouldIncreaseSizeAndReturnValue() {
        defaultMap.put(null, null);
        assertNull(defaultMap.get(null));
        assertEquals(4, defaultMap.size());
    }

    @Test
    void get_shouldReturnValue() {
        assertEquals(1, defaultMap.get(10));
    }

    @Test
    void getNullKey_shouldReturnValue() {
        defaultMap.put(null, 10);
        assertEquals(10, defaultMap.get(null));
    }

    @Test
    void get_shouldReturnNullIncorrectKey() {
        assertNull(defaultMap.get(100000));
    }

    @Test
    void remove_shouldReturnRemovedValueAndReduceSize() {
        assertEquals(1, defaultMap.remove(10));
        assertEquals(2, defaultMap.size());
    }

    @Test
    void removeIncorrectKey_shouldReturnNullAndNotReduceSize() {
        assertNull(defaultMap.remove(100000000));
        assertEquals(3, defaultMap.size());
    }

    @Test
    void remove_shouldNotChangeAnotherElement() {
        defaultMap.remove(10);
        assertEquals(2, defaultMap.get(20));
        assertEquals(3, defaultMap.get(30));
    }

    @Test
    void putAll_shouldIncreaseSize() {
        Map<Integer, Integer> newMap = new HashMap<>();
        newMap.put(40, 4);
        newMap.put(50, 5);
        newMap.put(null, 6);
        defaultMap.putAll(newMap);
        assertEquals(6, defaultMap.size());

    }

    @Test
    void putAllRepeatElement_shouldNotIncreaseSizeAndChangeValue() {
        Map<Integer, Integer> newMap = new HashMap<>();
        newMap.put(10, 10);
        newMap.put(20, 20);
        newMap.put(30, 30);
        defaultMap.putAll(newMap);
        assertEquals(3, defaultMap.size());
        assertEquals(1, defaultMap.get(10));
        assertEquals(2, defaultMap.get(20));
        assertEquals(3, defaultMap.get(30));

    }

    @Test
    void putIfAbsent_shouldReturnAddedValueAndIncreaseSize() {
        assertEquals(4, defaultMap.putIfAbsent(40, 4));
        assertEquals(4, defaultMap.size());
    }

    @Test
    void putIfAbsent_shouldReturnAOldValueAndNotIncreaseSize() {
        assertEquals(3, defaultMap.putIfAbsent(30, 4));
        assertEquals(3, defaultMap.size());
    }

    @Test
    void putIfAbsentNullKey_shouldReturnAOldValueAndNotIncreaseSize() {
        defaultMap.put(null, null);
        assertNull(defaultMap.putIfAbsent(null, 4));
        assertEquals(4, defaultMap.size());
    }

    @Test
    void emptyMap_shouldReturnTrue() {
        Map<String, String> map = new HashMap<>();
        assertTrue(map.isEmpty());
    }

    @Test
    void notEmptyMap_shouldReturnFalse() {
        assertFalse(defaultMap.isEmpty());
    }

    @Test
    void contains_shouldReturnTrue() {
        assertTrue(defaultMap.containsKey(30));
    }

    @Test
    void containsKeyNull_shouldReturnTrue() {
        defaultMap.put(null, null);
        assertTrue(defaultMap.containsKey(null));
    }

    @Test
    void contains_shouldReturnFalse() {
        assertFalse(defaultMap.containsKey(10000000));
    }
}
