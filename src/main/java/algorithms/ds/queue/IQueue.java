package algorithms.ds.queue;

public interface IQueue<T> {
    void enqueue(T t);
    T dequeue();
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
