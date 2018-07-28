package algorithms.ds.queue;

import java.util.NoSuchElementException;

public class ArrayBasedQueue<T> implements IQueue<T> {
    static final int INITIAL_CAPACITY = 16;

    private T[] array;
    private int tail = 0;
    private int head = 0;
    private int size=0;

    public ArrayBasedQueue() {
        this(INITIAL_CAPACITY);
    }

    public ArrayBasedQueue(int capacity) {
        this.array = (T[])new Object[capacity];
    }

    // add to tail
    @Override
    public void enqueue(T t) {
        if (tail == array.length) {
            enlarge();
        }
        array[tail++] = t;
        size++;
    }

    private void enlarge() {
        T[] newArray;
        if (head > array.length/2) {
            newArray = (T[])new Object[array.length];
        } else {
            newArray = (T[])new Object[array.length*2];
        }
        System.arraycopy(array, head, newArray, 0, tail - head);
        this.array = newArray;
        tail = tail - head;
        head = 0;
    }

    // remove from head
    @Override
    public T dequeue() {
        if (head == tail) {
            throw new NoSuchElementException();
        }
        size--;
        return array[head++];
    }

    @Override
    public int size() {
        return size;
    }
}