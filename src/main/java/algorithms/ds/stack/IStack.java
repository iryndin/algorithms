package algorithms.ds.stack;

public interface IStack<T> {
    void push(T t);
    T pop();
    T peek();
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }
}
