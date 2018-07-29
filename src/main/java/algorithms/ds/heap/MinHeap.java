package algorithms.ds.heap;

import java.util.NoSuchElementException;

public class MinHeap<E> implements IMinHeap<E> {

    static final int INITIAL_CAPACITY = 16;

    private int heapSize;
    private E[] array;

    public MinHeap() {
        this(INITIAL_CAPACITY);
    }

    public MinHeap(int capacity) {
        this.array = newArray(capacity);
    }

    @Override
    public int size() {
        return heapSize;
    }

    @Override
    public void add(E elem) {
        if (heapSize == array.length) {
            enlarge();
        }
        array[heapSize++] = elem;
        heapifyUp(heapSize-1);
    }

    @Override
    public E peekMin() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    @Override
    public E popMin() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        } else if (heapSize == 1) {
            heapSize--;
            return array[0];
        } else {
            E result = peekMin();
            array[0] = array[--heapSize];
            heapifyDown(0);
            return result;
        }
    }

    @Override
    public void decrease(E key, E newValue) {
        for (int i=0; i<array.length; i++) {
            if (array[i].equals(key)) {
                array[i] = newValue;
                heapifyUp(i);
                break;
            }
        }
    }

    private void enlarge() {
        E[] newArray = newArray(array.length*2);
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    private void swap(int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private void heapifyUp(int idx) {
        while (idx > 0 && isLess(idx, parent(idx))) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }
    }

    private void heapifyDown(int idx) {
        int left = left(idx);
        int right = right(idx);
        int smallest = idx;
        if (left < heapSize && isLess(left, smallest)) {
            smallest = left;
        }
        if (right < heapSize && isLess(right, smallest)) {
            smallest = right;
        }
        if (smallest != idx) {
            swap(smallest, idx);
            heapifyDown(smallest);
        }
    }

    private int parent(int i) {
        return (i-1)/2;
    }

    private int left(int i) {
        return 2*i+1;
    }

    private int right(int i) {
        return left(i)+1;
    }

    private E[] newArray(int len) {
        return (E[])new Object[len];
    }

    private boolean isLess(int i, int j) {
        Comparable cmp = (Comparable)array[i];
        return cmp.compareTo(array[j]) < 0;
    }
}
