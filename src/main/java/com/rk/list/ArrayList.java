package com.rk.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of range: " + size);
        }
        checkSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    //TODO: как протестировать "size - index - 1" или "size - index" ?
    //TODO: checkIndex(index) нужно тестить в комплексе с методом или отдельно?
    @Override
    public T remove(int index) {
        checkIndex(index);
        Object object = array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        size--;
        return (T) object;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    @Override
    public T set(T value, int index) {
        checkIndex(index);
        Object object = array[index];
        array[index] = value;
        return (T) object;
    }

    //TODO: как проверить что все ссылки теперь null?
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
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(value, array[i])) {
                return i;
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

    private void resize() {
        Object[] newArray = new Object[(int) (size * 1.5)];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void checkSize() {
        if (size == array.length) {
            resize();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range: " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            T object;
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                return object = (T) array[index++];
            }
        };
    }
}
