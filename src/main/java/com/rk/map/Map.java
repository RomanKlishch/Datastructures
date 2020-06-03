package com.rk.map;

public interface Map<K, V> extends Iterable<Map.Entry<K,V>>{

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void putAll(Map<K, V> map);

    V putIfAbsent(K key, V value);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    class Entry<K, V> {
        private final K key;
        private final int hash;

        private V value;

        public Entry(K key) {
            this.key = key;
            this.hash = key == null ? 0 : Math.abs(key.hashCode());
        }

        public Entry(K key, V value) {
            this(key);
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public int getHash() {
            return hash;
        }

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

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            }
            if (object == this) {
                return true;
            }
            if (getClass() != object.getClass()) {
                return false;
            }
            Entry<K, V> entry = (Entry<K, V>) object;
            return (hash == entry.hash && key.equals(entry.key));
        }
    }
}
