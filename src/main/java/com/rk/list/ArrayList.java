package com.rk.list;

import java.util.Iterator;

public class ArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;

    asfadfsdfgsdgs

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    zxzxczczxcz
    sdgsdasczxcv
    public ArrayList(int initialCapacity) {
        array = new Object[initialCapacity];
    }


    @Override
    public void add(T value) {
        add(value, size);
    }
zxcxzx
    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and " + size);
        }
        checkSize();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = value;
        size++;
    }

    @Override
    public Object remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and" + size);
        }
        if (isEmpty()){
            throw new RuntimeException("Array already is empty");
        }
        Object object = array[index];
        System.arraycopy(array, index + 1, array, index, size - index);
        size--;
        return object;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and" + size);
        }
        return array[index];
    }

    @Override
    public Object set(T value, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index should be between 0 and" + size);
        }
        return array[index] = value;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
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
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (null == array[i]) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        hereWillBeConfictAhtung();
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (null == array[i]) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (value.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                builder.append(array[i]);
                break;
            }
            builder.append(array[i]);
            builder.append(", ");
        }
        builder.append(']');
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



    @Override
    public Iterator<T> iterator() {
        return null;
    }



    private void hereWillBeConfictAhtung(){
        System.out.println("Achtung!!! Angry dogs!!!");
        conflict3();
    }

    private void conflict3(){
        System.out.println("here will be somnething not good!!!");
    }

}
