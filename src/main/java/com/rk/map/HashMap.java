package com.rk.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double DEFAULT_LOAD_FACTORY = 0.75;
    private List<Entry<K, V>>[] buckets;
    private int size;
    private final double loadFactory;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTORY);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTORY);
    }

    public HashMap(int initialCapacity, double loadFactory) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException(
                    "Capacity should be greater than or equal zero. " + initialCapacity + " less then 0");
        }
        this.buckets = new List[initialCapacity];
        this.loadFactory = loadFactory;
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();

        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        checkBucket(bucketIndex);
        for (Entry<K, V> entry : buckets[bucketIndex]) {
            if (isEntryExist(key, keyHash, entry)) {
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
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        if (buckets[bucketIndex] == null) {
            put(key, value);
        }
        Entry<K, V> entry = getEntry(buckets[bucketIndex], key, keyHash);
        if (entry != null) {
            return entry.getValue();
        } else {
            put(key, value);
            return value;
        }
    }

    @Override
    public V get(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        Entry<K, V> entry = getEntry(buckets[bucketIndex], key, keyHash);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        List<Entry<K, V>> bucket = buckets[bucketIndex];

        Iterator<Entry<K, V>> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (isEntryExist(key, keyHash, entry)) {
                iterator.remove();
                size--;
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Entry<K, V> entry : map) {
            put(entry.getKey(), entry.getValue());
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
        return getEntry(buckets[bucketIndex], key, keyHash) != null;
    }

    //TODO: наверное имеет смысл добавить еще две реализации итератора для Key и Value?
    // Если это делать как правильнo (через implement Iterable<K>)?
    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    private int getBucketIndex(K key) {
        return hash(key) % buckets.length;
    }

    private int getBucketIndex(Entry<K, V> entry, int customLength) {
        return entry.getHash() % customLength;
    }

    private void checkBucket(int bucketIndex) {
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(1);
        }
    }

    private boolean isEntryExist(K key, int keyHash, Entry<K, V> entry) {
        return entry.getHash() == keyHash && Objects.equals(entry.getKey(), key);
    }

    private Entry<K, V> getEntry(List<Entry<K, V>> bucket, K key, int keyHash) {
        for (Entry<K, V> entry : bucket) {
            if (isEntryExist(key, keyHash, entry)) {
                return entry;
            }
        }
        return null;
    }

    private void growIfNeeded() {
        if (size > buckets.length * loadFactory) {
            @SuppressWarnings("uncheced")
            List<Entry<K, V>>[] newBuckets = new List[((int) (buckets.length * 1.5) + 1)];

            for (List<Entry<K, V>> list : buckets) {
                if (list != null) {

                    for (Entry<K, V> entry : list) {
                        int bucketIndex = getBucketIndex(entry, newBuckets.length);
                        if (newBuckets[bucketIndex] == null) {
                            newBuckets[bucketIndex] = new ArrayList<>(1);
                        }
                        newBuckets[bucketIndex].add(entry);
                    }

                }
            }
            buckets = newBuckets;
        }
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int bucketIndex;
        private int count;
        private Iterator<Entry<K, V>> bucketIterator;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Entry<K, V> next() {
            Entry<K, V> currentEntry;
            if (bucketIterator == null) {
                while (buckets[bucketIndex] == null) {
                    bucketIndex++;
                }
                bucketIterator = buckets[bucketIndex].iterator();
                count++;
                currentEntry = bucketIterator.next();
                return currentEntry;
            }
            if (!bucketIterator.hasNext()) {
                while (buckets[++bucketIndex] == null) {
                }
                bucketIterator = buckets[bucketIndex].iterator();
                count++;
                currentEntry = bucketIterator.next();
                return currentEntry;
            }
            currentEntry = bucketIterator.next();
            count++;
            return currentEntry;
        }
    }
}
