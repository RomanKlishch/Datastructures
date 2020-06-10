package com.rk.list;

import java.util.StringJoiner;

public abstract class AbstractList<T> implements List<T> {
    protected int size;

    @Override
    public void add(T value) {
        add(value, size);
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public String toString() {
        StringJoiner builder = new StringJoiner(", ", "[", "]");
        for (T object : this) {
            builder.add(String.valueOf(object));
        }
        return builder.toString();
    }

    protected void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index should be between 0 and size inclusive [ 0, %d), but was %d", size, index));
        }
    }

    protected void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index should be between 0 and size exclusive [ 0, %d), but was %d", size, index));
        }
    }
}
