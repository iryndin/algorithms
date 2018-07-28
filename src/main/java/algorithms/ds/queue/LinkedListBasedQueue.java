package algorithms.ds.queue;

import java.util.NoSuchElementException;

public class LinkedListBasedQueue<T> implements IQueue<T> {

    private static final class Node<T> {
        public final T data;
        public Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size=0;

    @Override
    public void enqueue(T t) {
        Node<T> newNode = new Node<>(t);
        if (tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if (head == null) {
            head = newNode;
        }
        size++;
    }

    @Override
    public T dequeue() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.data;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}