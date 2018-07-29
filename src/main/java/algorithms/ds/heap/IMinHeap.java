package algorithms.ds.heap;

public interface IMinHeap<E> {
    void add(E element);
    E peekMin();
    E popMin();
    void decrease(E key, E newValue);
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
