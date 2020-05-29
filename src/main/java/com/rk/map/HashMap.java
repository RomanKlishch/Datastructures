package com.rk.map;

import com.rk.list.ArrayList;

import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;
    private ArrayList<Entry<K, V>>[] hashMap;
    private int size;
    private int capacity;

    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    public HashMap(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException(
                    "Capacity should be greater than zero. " + initialCapacity + " less then 0");
        capacity = initialCapacity;
        this.hashMap = new ArrayList[initialCapacity];
        for (int i = 0; i < hashMap.length; i++) {
            hashMap[i] = new ArrayList<>(0);
        }
    }

    @Override
    public V put(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        int basket = findBasket(key);
        for (Entry<K, V> entry : hashMap[basket]) {
            if (entry.hash == hash(key) && Objects.equals(entry.key, key)) {
                V oldValue = entry.value;
                entry.value = value;
                return oldValue;
            }
        }
        hashMap[basket].add(newEntry);
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        int basket = findBasket(key);
        for (Entry<K, V> entry : hashMap[basket]) {
            if (entry.hash == hash(key) && Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int basket = findBasket(key);
        int i = 0;
        for (Entry<K, V> entry : hashMap[basket]) {
            if (entry.hash == hash(key) && Objects.equals(entry.key, key)) {
                hashMap[basket].remove(i);
                size--;
                return entry.value;
            }
            i++;
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        
    }

    @Override
    public V putIfAbsent(K key, V value) {
        Entry<K, V> newEntry = new Entry<>(key, value);
        int basket = findBasket(key);
        for (Entry<K, V> entry : hashMap[basket]) {
            if (entry.hash == hash(key) && Objects.equals(entry.key, key)) {
                return entry.value;
            }
        }
        hashMap[basket].add(newEntry);
        size++;
        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(K key) {
        int basket;
        basket = findBasket(key);
        for (Entry<K, V> entry : hashMap[basket]) {
            if (entry.hash == hash(key) && Objects.equals(entry.key, key)) {
                return true;
            }
        }
        return false;
    }

    public int capacity() {
        return capacity;
    }

    private int hash(K key) {
        return key == null ? 0 : 31 * key.hashCode();
    }

    private int findBasket(K key) {
        return hash(key) % capacity;
    }


    private static class Entry<K, V> {
        private Entry<K, V> next;
        private K key;
        private V value;
        private int hash;

        public Entry() {
        }

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            if (key == null) {
                this.hash = 0;
            } else {
                this.hash = 31 * key.hashCode();
            }
        }

        //TODO:прввильно ли будет переопределить equals() только по key и hash?
        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (getClass() != object.getClass()) {
                return false;
            }
            if (object == this) {
                return true;
            }
            Entry<K, V> entry = (Entry<K, V>) object;
            return (hash == entry.hash && key.equals(entry.key));
        }

        @Override
        public int hashCode() {
            hash = key == null ? 0 : 31 * key.hashCode();
            return hash;
        }
    }
}
