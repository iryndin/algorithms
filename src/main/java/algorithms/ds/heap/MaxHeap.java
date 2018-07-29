package algorithms.ds.heap;

import java.util.NoSuchElementException;

public class MaxHeap <E> implements IMaxHeap<E> {

    public static final int INITIAL_CAPACITY = 16;

    protected int heapSize;
    protected E[] array;

    public MaxHeap() {
        this(INITIAL_CAPACITY);
    }

    public MaxHeap(int capacity) {
        this.array = newArray(capacity);
    }

    @Override
    public int size() {
        return heapSize;
    }

    @Override
    public void add(E element) {
        if (heapSize == array.length) {
            enlarge();
        }
        array[heapSize++] = element;
        heapifyUp(heapSize-1);
    }

    @Override
    public E peekMax() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        }
        return array[0];
    }

    @Override
    public E popMax() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        } else if (heapSize == 1) {
            heapSize--;
            return array[0];
        } else {
            E result = peekMax();
            array[0] = array[--heapSize];
            heapifyDown(0);
            return result;
        }
    }

    @Override
    public void increase(E key, E newValue) {
        for (int i=0; i<array.length; i++) {
            if (array[i].equals(key)) {
                array[i] = newValue;
                heapifyUp(i);
                break;
            }
        }
    }

    private void heapifyUp(int idx) {
        while (idx > 0 && isGreater(idx, parent(idx))) {
            swap(idx, parent(idx));
            idx = parent(idx);
        }
    }

    private void heapifyDown(int idx) {
        int left = left(idx);
        int right = right(idx);
        int largest = idx;
        if (left < heapSize && isGreater(left, largest)) {
            largest = left;
        }
        if (right < heapSize && isGreater(right, largest)) {
            largest = right;
        }
        if (largest != idx) {
            swap(largest, idx);
            heapifyDown(largest);
        }
    }

    private boolean isGreater(int i, int j) {
        Comparable cmp = (Comparable)array[i];
        return cmp.compareTo(array[j]) > 0;
    }

    private void swap(int i, int j) {
        E tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    private E[] newArray(int len) {
        return (E[])new Object[len];
    }

    private void enlarge() {
        E[] newArray = newArray(array.length*2);
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    private static int parent(int i) {
        return (i-1)/2;
    }

    private static int left(int i) {
        return 2*i+1;
    }

    private static int right(int i) {
        return left(i)+1;
    }
}
