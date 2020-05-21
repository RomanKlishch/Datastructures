package com.rk.list.linkedList;

import java.util.*;

public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedList() {
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void add(T o) {
        Node<T> node = new Node<T>(o, head);
        if (size == 0) {
            tail = node;

        } else {
            head.setNext(node);
        }
        head = node;
        size++;
    }

    //TODO: как сделать Stream<Node> используя Stream.iterate()?
    public void add(int index, T element) {
        checkIndex(index);
        Node<T> newNode = new Node<>(element);

        if (index == 0) {
            tail.setPrev(newNode);
            newNode.setNext(tail);
            tail = newNode;
        }
        if (index > 0) {
            Node<T> iterationNode = getNode(index);
            iterationNode.getPrev().setNext(newNode);
            newNode.setPrev(iterationNode.getPrev());
            newNode.setNext(iterationNode);
            iterationNode.setPrev(newNode);
        }
        size++;
    }

    public T remove(int index) {
        checkIndex(index);
        Node<T> iterationNode = getNode(index);
        if (index == 0) {
            iterationNode.getNext().setPrev(null);
            tail = iterationNode.getNext();
        }
        if (index > 0) {
            iterationNode.getPrev().setNext(iterationNode.getNext());
            iterationNode.getNext().setPrev(iterationNode.getPrev());
        }
        size--;
        return iterationNode.getValue();
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> iterationNode = getNode(index);
        return iterationNode.getValue();
    }

    public T set(int index, T element) {
        checkIndex(index);
        Node<T> newNode = getNode(index);
        newNode.setValue(element);
        return newNode.getValue();
    }

    public int indexOf(Object o) {
        Node<T> iterationNode = tail;
        for (int i = 0; i <size ; i++) {
            if (o.equals(iterationNode.value)){
                return i;
            }
            iterationNode = iterationNode.getNext();
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Node<T> iterationNode = head;
        for (int i = size-1; i>0 ; i--) {
            if (o.equals(iterationNode.value)){
                return i;
            }
            iterationNode = iterationNode.getPrev();
        }
        return -1;
    }

    public void clear() {
        return;
    }

    public boolean isEmpty() {
        return false;
    }

    public boolean contains(Object o) {
        return false;
    }

    public String toString() {
        return super.toString();
    }


    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> nodeIteration = tail;
            private int iterator = 0;

            @Override
            public boolean hasNext() {
                if (iterator < size) {
                    iterator++;
                    return true;
                }
                return false;
            }

            @Override
            public T next() {
                T value = nodeIteration.value;
                nodeIteration = nodeIteration.next;
                return value;
            }
        };
    }

    private void checkIndex(int index) {
        if (index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<T> getNode(int index) {
        Node<T> iterationNode = null;
        if (index <= size / 2) {
            iterationNode = tail;
            for (int i = 0; i < index; i++) {
                iterationNode = iterationNode.getNext();
            }
        }
        if (index > size / 2) {
            iterationNode = head;
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

        public Node(T value) {
            this.value = value;
        }

        public Node(T value, Node<E> prev) {
            this.value = value;
            this.prev = prev;
        }

        public Node() {
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
