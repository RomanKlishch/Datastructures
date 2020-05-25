package com.rk.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.StringJoiner;

public class LinkedList<T> implements List<T> {
    private Node<T> tail;
    private Node<T> head;
    private int size;

    public LinkedList() {
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (size == 0) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrev(tail);
        }
        tail = newNode;
        size++;
    }

    //TODO: как сделать Stream<Node> используя Stream.iterate()?
    //TODO: как заменить if()?

    @Override
    public void add(T value, int index) {
        validateIndex(index, index > size);
        Node<T> newNode = new Node<>(value);
        if (index == 0) {
            if (size == 0) {
                head = tail = newNode;
            } else {
                head.setPrev(newNode);
                newNode.setNext(head);
                head = newNode;
            }
        } else {
            Node<T> iterationNode = getNode(index);
            iterationNode.getPrev().setNext(newNode);
            newNode.setPrev(iterationNode.getPrev());
            newNode.setNext(iterationNode);
            iterationNode.setPrev(newNode);
        }
        size++;
    }

    @Override
    public T remove(int index) {
        validateIndex(index, index >= size);
        Node<T> iterationNode = getNode(index);
        if (index == 0) {
            head.getNext().setPrev(null);
            iterationNode = head;
            head = head.getNext();
        } else {
            iterationNode.getPrev().setNext(iterationNode.getNext());
            if (index != size - 1) {
                iterationNode.getNext().setPrev(iterationNode.getPrev());
            }
        }
        size--;
        return iterationNode.getValue();
    }

    @Override
    public T get(int index) {
        validateIndex(index, index >= size);
        Node<T> iterationNode = getNode(index);
        return iterationNode.getValue();
    }

    @Override
    public T set(T value, int index) {
        validateIndex(index, index >= size);
        Node<T> newNode = getNode(index);
        T oldValue = newNode.value;
        newNode.setValue(value);
        return oldValue;
    }

    @Override
    public int indexOf(T value) {
        Node<T> iterationNode = head;
        for (int i = 0; i < size; i++) {
            if (Objects.equals(value, iterationNode.value)) {
                return i;
            }
            iterationNode = iterationNode.getNext();
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
            iterationNode = iterationNode.getPrev();
        }
        return -1;
    }

    @Override
    public void clear() {
        size = 0;
        head = new Node<>();
        tail = head;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    @Override
    public String toString() {
        StringJoiner builder = new StringJoiner(", ", "[", "]");
        Node<T> nodeIteration = head;
        for (int i = 0; i < size; i++) {
            builder.add(String.valueOf(nodeIteration.value));
            nodeIteration = nodeIteration.getNext();
        }
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> nodeIteration = head;

            @Override
            public boolean hasNext() {
                return nodeIteration != null;
            }

            @Override
            public T next() {
                T value = nodeIteration.value;
                nodeIteration = nodeIteration.getNext();
                return value;
            }
        };
    }

    private void validateIndex(int index, boolean b) {
        if (index < 0 || b) {
            throw new IndexOutOfBoundsException("Index out of range: " + size);
        }
    }

    private Node<T> getNode(int index) {
        Node<T> iterationNode = null;
        if (index <= size / 2) {
            iterationNode = head;
            for (int i = 0; i < index; i++) {
                iterationNode = iterationNode.getNext();
            }
        }
        if (index > size / 2) {
            iterationNode = tail;
            for (int i = size - 1; i > index; i--) {
                iterationNode = iterationNode.getPrev();
            }
        }
        return iterationNode;
    }

    private class Node<E> {
        T value;
        private Node<E> prev;
        private Node<E> next;

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }
    }
}
