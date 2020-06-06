package com.rk.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class HashMap<K, V> implements Map<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;
    private static final double DEFAULT_LOAD_FACTORY = 0.75;
    private final double loadFactory;

    private List<Entry>[] buckets;
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
        this.buckets = new List[initialCapacity];
        this.loadFactory = loadFactory;
    }

    @Override
    public V put(K key, V value) {
        growIfNeeded();
        Entry entry = getEntry(key);
        if (entry != null) {
            V oldValue = entry.getValue();
            entry.setValue(value);
            return oldValue;
        }

        Entry newEntry = new Entry(key, value);
        int bucketIndex = getBucketIndex(key);
        createBucketIfNotExist(bucketIndex);
        buckets[bucketIndex].add(newEntry);
        size++;
        return value;
    }

    //Todo: В прошлом видео ты удалил в методе putIfAbsent() последний else.
// Он нужен для коректной работы первого  if(). Если в бакет равен нулл,
// тогда мы точно знаем что такого Entry не существует
    @Override
    public V putIfAbsent(K key, V value) {
        int bucketIndex = getBucketIndex(key);

        if (buckets[bucketIndex] == null) {
            put(key, value);
        }
        Entry entry = getEntry(key);
        if (entry != null) {
            return entry.getValue();
        } else {
            put(key, value);
            return value;
        }
    }

    @Override
    public V get(K key) {
        Entry entry = getEntry(key);
        return entry != null ? entry.getValue() : null;
    }

    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        List<Entry> bucket = buckets[bucketIndex];

        Iterator<Entry> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Entry entry = iterator.next();
            if (isEntryEquals(key, keyHash, entry)) {
                iterator.remove();
                size--;
                return entry.getValue();
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

    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    //Todo: возможен один случай исключения - когда buckets.length=0(ошибка - деление на ноль).
// При этом есть ли смысл создавать такую HashMap?
    private int getBucketIndex(K key) {
        return Math.abs(hash(key) % buckets.length);
    }

    private int getBucketIndex(Entry entry, int customLength) {
        return entry.getHash() % customLength;
    }

    private void createBucketIfNotExist(int bucketIndex) {
        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = new ArrayList<>(1);
        }
    }

    private boolean isEntryEquals(K key, int keyHash, Entry entry) {
        return entry.getHash() == keyHash && Objects.equals(entry.getKey(), key);
    }

    private Entry getEntry(K key) {
        int bucketIndex = getBucketIndex(key);
        int keyHash = hash(key);
        if (buckets[bucketIndex] != null) {
            for (Entry entry : buckets[bucketIndex]) {
                if (isEntryEquals(key, keyHash, entry)) {
                    return entry;
                }
            }
        }
        return null;
    }

    private void growIfNeeded() {
        if (size > buckets.length * loadFactory) {
            @SuppressWarnings("uncheced")
            List<Entry>[] newBuckets = new List[((int) (buckets.length * 1.5) + 1)];

            for (List<Entry> list : buckets) {
                if (list != null) {

                    for (Entry entry : list) {
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
        private Iterator<Entry> bucketIterator;

        public HashMapIterator() {
            iterateToNextBucket();
        }

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public Entry next() {
            if (!bucketIterator.hasNext()) {
                bucketIndex++;
                iterateToNextBucket();
            }
            count++;
            return bucketIterator.next();
        }

        private void iterateToNextBucket() {
            while (buckets[bucketIndex] == null) {
                bucketIndex++;
            }
            bucketIterator = buckets[bucketIndex].iterator();
        }
    }

    private class Entry implements Map.Entry<K, V> {
        private final K key;
        private final int hash;

        private V value;

        public Entry(K key) {
            this.key = key;
            this.hash = hash(key);
        }

        public Entry(K key, V value) {
            this(key);
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        public int getHash() {
            return hash;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
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
