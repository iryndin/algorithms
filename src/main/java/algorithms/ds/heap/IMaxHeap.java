package algorithms.ds.heap;

public interface IMaxHeap<E> {
    void add(E key);
    E peekMax();
    E popMax();
    void increase(E key, E newValue);
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
