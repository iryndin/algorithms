package algorithms.ds.stack;

import java.util.NoSuchElementException;

public class ArrayStack<T> implements IStack<T> {
    static final int INITIAL_CAPACITY = 16;

    private T[] array;
    private int top=0;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.array = (T[])new Object[capacity];
    }

    @Override
    public void push(T t) {
        if (top == array.length) {
            enlarge();
        }
        array[top++] = t;
    }

    private void enlarge() {
        T[] newArray = (T[])new Object[array.length*2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    @Override
    public T pop() {
        if (top == 0) {
            throw new NoSuchElementException();
        }
        return array[--top];
    }

    @Override
    public T peek() {
        if (top == 0) {
            throw new NoSuchElementException();
        }
        return array[top-1];
    }

    @Override
    public int size() {
        return top;
    }

    @Override
    public void clear() {
        top=0;
    }
}
