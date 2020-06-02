package com.rk.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> extends AbstractList<T> implements List<T> {
    private Node<T> tail;
    private Node<T> head;

    @Override
    public void add(T value) {
        add(value, size);
    }

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node<T> newNode = new Node<>();
        newNode.value = value;
        if (size == 0) {
            tail = head = newNode;
        } else if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> iterationNode = getNode(index);
            iterationNode.prev.next = newNode;
            newNode.prev = iterationNode.prev;
            newNode.next = iterationNode;
            iterationNode.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> iterationNode = getNode(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index == size - 1) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            iterationNode.prev.next = iterationNode.next;
            iterationNode.next.prev = iterationNode.prev;
        }
        size--;
        return iterationNode.value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        Node<T> iterationNode = getNode(index);
        return iterationNode.value;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        Node<T> newNode = getNode(index);
        T oldValue = newNode.value;
        newNode.value = value;
        return oldValue;
    }

    @Override
    public int indexOf(T value) {
        int i = 0;
        for (T object : this) {
            if (Objects.equals(value, object)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> iterationNode = tail;
        for (int i = size - 1; i > 0; i--) {
            if (Objects.equals(value, iterationNode.value)) {
                return i;
            }
            iterationNode = iterationNode.prev;
        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
        tail = head = null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> nodeIteration = head;

            @Override
            public boolean hasNext() {
                return nodeIteration != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Next element not exist");
                }
                T value = nodeIteration.value;
                nodeIteration = nodeIteration.next;
                return value;
            }
        };
    }


    private Node<T> getNode(int index) {
        Node<T> iterationNode = null;
        if (index <= size / 2) {
            iterationNode = head;
            for (int i = 0; i < index; i++) {
                iterationNode = iterationNode.next;
            }
        } else if (index > size / 2) {
            iterationNode = tail;
            for (int i = size - 1; i > index; i--) {
                iterationNode = iterationNode.prev;
            }
        }
        return iterationNode;
    }

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;
    }
}
