package algorithms.ds.stack;

import java.util.NoSuchElementException;

public class LinkedListStack<T> implements IStack<T> {

    private static final class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size;

    @Override
    public void push(T t) {
        Node<T> newHead = new Node<>(t);
        newHead.next = head;
        head = newHead;
        size++;
    }

    @Override
    public T pop() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.data;
        head = head.next;
        size--;
        return result;
    }

    @Override
    public T peek() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        size=0;
    }
}
