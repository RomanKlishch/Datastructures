package com.rk.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 5;
    private static final double INITIAL_LOAD_FACTORY = 0.75;
    private ArrayList<Entry<K, V>>[] buckets;
    private int size;
    private final double loadFactory;

    public HashMap() {
        this(INITIAL_CAPACITY, INITIAL_LOAD_FACTORY);
    }

    public HashMap(int initialCapacity, double loadFactory) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                    "Capacity should be greater than or equal zero. " + initialCapacity + " less then 0");
        }
        this.buckets = new ArrayList[initialCapacity];
        this.loadFactory = loadFactory;
    }

    @Override
    public V put(K key, V value) {
        resize();

        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        checkBucket(bucketIndex);
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (entry.getHash() == keyHash && Objects.equals(entry.getKey(), key)) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
        }
        buckets[bucketIndex].add(newEntry);
        size++;
        return value;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        resize();

        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        if (buckets[bucketIndex] == null) {
            put(key, value);
        }
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (entry.getHash() == keyHash && Objects.equals(entry.getKey(), key)) {
                return entry.getValue();
            }
        }
        put(key, value);
        return value;
    }

    @Override
    public V get(K key) {
        if (containsKey(key)) {
            int bucketIndex = getBucketIndex(key);
            int keyHash = hash(key);
            for (Entry<K, V> entry : buckets[bucketIndex]) {
                if (entry.getHash() == keyHash && Objects.equals(entry.getKey(), key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];
        for (Entry<K, V> entry : bucket) {
            if (entry.getHash() == keyHash && Objects.equals(entry.getKey(), key)) {
                V removeEntry = entry.getValue();
                bucket.remove(entry);
                size--;
                return removeEntry;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Entry<K, V> entry : map) {
            putIfAbsent(entry.getKey(), entry.getValue());
        }
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
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (entry.getHash() == keyHash && Objects.equals(entry.getKey(), key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new Iterator<>() {
            private int indexBuckets = 0;
            private int indexList = 0;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
            }

            @Override
            public Entry<K, V> next() {
                while (indexBuckets < buckets.length) {
                    if (buckets[indexBuckets] != null && buckets[indexBuckets].size() != 0) {
                        while (indexList < buckets[indexBuckets].size()) {
                            count++;
                            return buckets[indexBuckets].get(indexList++);
                        }
                        indexList = 0;
                    }
                    indexBuckets++;
                }
                return null;
            }
        };
    }

    private int hash(K key) {
        return key == null ? 0 : key.hashCode();
    }

    private int getBucketIndex(K key) {
        return hash(key) % buckets.length;
    }

    private void checkBucket(int bucketIndex) {
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(0);
        }
    }

    //TODO: Как протестировать метод и нужно ли это делать?
    private void resize() {
        if (size != 0 && size / buckets.length > loadFactory) {
            ArrayList<Entry<K, V>>[] newBuckets = new ArrayList[((int) (buckets.length * 1.5) + 1)];
            for (ArrayList<Entry<K, V>> list : buckets) {
                if (list != null) {
                    for (Entry<K, V> entry : list) {
                        int bucketIndex = entry.getHash() / newBuckets.length;
                        if (newBuckets[bucketIndex] == null) {
                            newBuckets[bucketIndex] = new ArrayList<>(0);
                        }
                        newBuckets[bucketIndex].add(entry);
                    }
                }
            }
            buckets = newBuckets;
        }
    }
}
