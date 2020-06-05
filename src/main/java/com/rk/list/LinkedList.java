package com.rk.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class LinkedList<T> extends AbstractList<T> {
    private Node<T> tail;
    private Node<T> head;

    @Override
    public void add(T value, int index) {
        validateIndexForAdd(index);
        Node<T> newNode = new Node<>(value);

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
            Node<T> nodeToShift = getNode(index);
            nodeToShift.prev.next = newNode;
            newNode.prev = nodeToShift.prev;
            newNode.next = nodeToShift;
            nodeToShift.prev = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Node<T> nodeToRemove = getNode(index);
        if (size == 1) {
            head = tail = null;
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index == size - 1) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;
        }
        size--;
        return nodeToRemove.value;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        Node<T> node = getNode(index);
        return node.value;
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index);
        Node<T> currentNode = getNode(index);
        T oldValue = currentNode.value;
        currentNode.value = value;
        return oldValue;
    }

    @Override
    public int indexOf(T value) {
        int index = 0;
        for (T object : this) {
            if (Objects.equals(value, object)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T value) {
        Node<T> currentNode = tail;
        for (int index = size - 1; index > 0; index--) {
            if (Objects.equals(value, currentNode.value)) {
                return index;
            }
            currentNode = currentNode.prev;
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
        return new LinkedListIterator();
    }

    private Node<T> getNode(int index) {
        Node<T> node;
        if (index <= size / 2) {
            node = head;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = tail;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node;
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> currentNode = head;
        private Node<T> removeNode;
        private int index;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element not exist");
            }
            index++;
            removeNode = currentNode;
            T value = currentNode.value;
            currentNode = currentNode.next;
            return value;
        }

        @Override
        public void remove() {
//            LinkedList.this.remove(--index);
            if (removeNode.prev == null && removeNode.next == null) {
                head = tail = null;
            } else if (removeNode.prev == null) {
                head = removeNode.next;
                removeNode.next.prev = null;
            } else if (removeNode.next == null) {
                tail = removeNode.prev;
                removeNode.prev.next = null;
            } else {
                removeNode.prev.next = removeNode.next;
                removeNode.next.prev = removeNode.prev;
            }
            size--;
        }
    }

    private static class Node<T> {
        private T value;
        private Node<T> prev;
        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
