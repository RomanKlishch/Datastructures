package com.rk.map;

public interface Map<K, V> extends Iterable<Map.Entry<K, V>> {

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    void putAll(Map<K, V> map);

    V putIfAbsent(K key, V value);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    interface Entry<K, V> {
        K getKey();

        V getValue();
    }
}
