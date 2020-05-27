package com.rk.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("uncheked")
    public ArrayList(int initialCapacity) {
        array = (T[]) new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        T object = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[size - 1] = null;
        size--;
        return object;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return array[index];
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        T object = array[index];
        array[index] = value;
        return object;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
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
    public int indexOf(T value) {
        for (int index = 0; index < size; index++) {
            if (Objects.equals(value, array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int index = size - 1; index >= 0; index--) {
            if (Objects.equals(value, array[index])) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringJoiner builder = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < size; i++) {
            builder.add(String.valueOf(array[i]));
        }
        return builder.toString();
    }

    @SuppressWarnings("uncheked")
    private void resize() {
        T[] newArray = (T[]) new Object[(int) ((size + 1.5) * 1)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void ensureCapacity() {
        if (size == array.length) {
            resize();
        }
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
            );
        }
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(
                    String.format("Index should be between 0 and size inclusive [ 0, %d), but was %d", size, index));
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            //TODO:я не могу описать ситуацию в которой при которой возможен NoSuchElementException();?
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Next element not exist");
                }
                return array[index++];
            }
        };
    }

}
