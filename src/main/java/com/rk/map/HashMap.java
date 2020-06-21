package com.rk.map;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 2;
    private static final double DEFAULT_LOAD_FACTORY = 0.75;
    private final double loadFactory;

    private Entry<K, V>[] buckets;
    private int size;

    public HashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTORY);
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTORY);
    }

    public HashMap(int initialCapacity, double loadFactory) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException(initialCapacity + " less then or equals 0");
        }
        this.buckets = new Entry[initialCapacity];
        this.loadFactory = loadFactory;
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = newEntry;
        } else {
            Entry<K, V> oldEntry = buckets[bucketIndex];
            while (oldEntry != null) {
                if (isEntryEquals(key, newEntry.hash, oldEntry)) {
                    V oldValue = oldEntry.value;
                    oldEntry.value = value;
                    return oldValue;
                }
                if (oldEntry.next == null) {
                    oldEntry.next = newEntry;
                    break;
                }
                oldEntry = oldEntry.next;
            }
        }
        size++;
        return value;
    }

    @Override
    public V putIfAbsent(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> newEntry = new Entry<>(key, value);
        growIfNeeded();
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = newEntry;
        } else {
            Entry<K, V> oldEntry = buckets[bucketIndex];
            while (oldEntry != null) {
                if (isEntryEquals(key, newEntry.hash, oldEntry)) {
                    return oldEntry.value;
                }
                if (oldEntry.next == null) {
                    oldEntry.next = newEntry;
                    break;
                }
                oldEntry = oldEntry.next;
            }
        }
        size++;
        return value;
    }

    @Override
    public V get(K key) {
        Entry<K, V> entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        Entry<K, V> bucket = buckets[bucketIndex];
        if (bucket == null) {
            return null;
        } else {
            Iterator<Map.Entry<K, V>> iterator = iterator();
            while (iterator.hasNext()) {
                Map.Entry<K, V> entry = iterator.next();
                if (isEntryEquals(key, keyHash, (Entry<K, V>) entry)) {
                    iterator.remove();
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map) {
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
        return getEntry(key) != null;
    }

    @Override
    public Iterator<Map.Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    private static int hash(Object key) {
        return key == null ? 0 : key.hashCode();
    }

    private int getBucketIndex(K key) {
        return Math.abs(hash(key) % buckets.length);
    }

    private boolean isEntryEquals(K key, int keyHash, HashMap.Entry<K,V> entry) {
        return entry.getHash() == keyHash && Objects.equals(entry.getKey(), key);
    }

    private Entry<K, V> getEntry(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        Entry<K, V> entry = buckets[bucketIndex];

        while (entry != null) {
            if (isEntryEquals(key, keyHash, entry)) {
                return entry;
            }
            entry = entry.next;
        }
        return null;
    }

    private void growIfNeeded() {
        if (size > buckets.length * loadFactory) {
            @SuppressWarnings("uncheced")
            Entry<K, V>[] newBuckets = new Entry[((int) (buckets.length * 1.5) + 1)];
            for (Entry<K, V> entry : buckets) {
                if (entry!=null){
                    int index = getBucketIndex(entry.getKey());
                    if (newBuckets[index]==null){
                        newBuckets[index] = entry;
                    }else {
                        Entry<K, V> prevEntry = newBuckets[index];
                        while (prevEntry.next!=null){
                            prevEntry = prevEntry.next;
                        }
                        prevEntry.next = entry;
                    }
                }
            }
        }
    }

    private class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        private int nextBucketIndex;
        private int currentBucketIndex;
        private Entry<K, V> nextEntry;
        private Entry<K, V> currentEntry;
        private Entry<K, V> prevEntry;
        private int count;

        public HashMapIterator() {
            if (size != 0) {
                iterateToNextBucket();
                nextEntry = buckets[nextBucketIndex];
            }
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Entry<K, V> next() {
            prevEntry = currentEntry;
            currentEntry = nextEntry;
            currentBucketIndex = nextBucketIndex;
            if (++count < size) {
                if (nextEntry.next == null) {
                    nextBucketIndex++;
                    iterateToNextBucket();
                    nextEntry = buckets[nextBucketIndex];
                } else {
                    nextEntry = nextEntry.next;
                }
            }
            return currentEntry;
        }

        @Override
        public void remove() {
            if (buckets[currentBucketIndex] == currentEntry && currentEntry.next == null) {
                buckets[currentBucketIndex] = null;

            } else if (buckets[currentBucketIndex] == currentEntry && currentEntry.next != null) {
                buckets[currentBucketIndex] = currentEntry.next;
            } else {
                prevEntry.next = currentEntry.next;
                currentEntry = null;
            }
            currentEntry = null;
            size--;
        }

        private void iterateToNextBucket() {
            while (buckets[nextBucketIndex] == null) {
                if (nextBucketIndex > buckets.length - 1) {
                    throw new NoSuchElementException("Next element not exist");
                }
                nextBucketIndex++;
            }
        }
    }

    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private final int hash;

        private V value;
        private Entry<K, V> next;

        public Entry(K key) {
            this.key = key;
            this.hash = HashMap.hash(key);
        }

        public Entry(K key, V value) {
            this(key);
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public int getHash() {
            return hash;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", hash=" + hash +
                    ", value=" + value +
                    '}';
        }
    }
}
