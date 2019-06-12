package de.codecrunch.model;

import java.util.List;

public class M_Path<T> {

    private Node head;
    private Node tail;

    public Node head() {
        return head;
    }

    public Node tail() {
        return tail;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Node addFront(T d) {
        Node h = new Node(d);
        Node old = head;
        head = h;
        if (old != null) {
            head.prev = old;
            old.next = head;
        } else
            tail = h;
        return h;
    }

    public void removeFront() {
        if (head == tail)
            tail = null;
        if (!head.hasPrev())
            head = null;
        else {
            head = head.prev;
            head.next = null;
        }
    }

    public Node addBack(T d) {
        Node t = new Node(d);
        Node old = tail;
        tail = t;
        if (old != null) {
            tail.next = old;
            old.prev = tail;
        } else {
            head = t;
        }
        return t;
    }

    public void removeBack() {
        if (head == tail)
            head = null;
        if (!tail.hasNext())
            tail = null;
        else {
            tail = tail.next;
            tail.prev = null;
        }
    }

    public List<T> addToList(List<T> list) {
        Node current = tail;
        if (current == null)
            return list;
        list.add(current.data);
        do {
            current = current.next;
            list.add(current.data);
        } while (current.hasNext());
        return list;
    }

    public M_Path<T> addFromList(List<T> list) {
        if (!list.isEmpty())
            list.forEach(M_Path.this::addFront);
        return this;
    }

    public class Node {
        private T data;
        private Node prev;
        private Node next;

        private Node(T d) {
            data = d;
        }

        public boolean hasNext() {
            return next != null;
        }

        public boolean hasPrev() {
            return prev != null;
        }

        public T get() {
            return data;
        }

        public Node prev() {
            return prev;
        }

        public Node next() {
            return next;
        }

        public String toString() {
            return data.toString();
        }
    }
}
