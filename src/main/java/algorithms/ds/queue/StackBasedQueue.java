package algorithms.ds.queue;

import algorithms.ds.stack.ArrayStack;
import algorithms.ds.stack.IStack;

import java.util.NoSuchElementException;

public class StackBasedQueue<T> implements IQueue<T> {

    private int size=0;

    private IStack<T> stack1 = new ArrayStack<>();
    private IStack<T> stack2 = new ArrayStack<>();
    private boolean dataInStack1 = true;

    @Override
    public void enqueue(T t) {
        if (!dataInStack1) {
            stack1.clear();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            dataInStack1 = true;
        }
        stack1.push(t);
        size++;
    }

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        if (dataInStack1) {
            stack2.clear();
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            dataInStack1 = false;
        }
        T result = stack2.pop();
        size--;
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}
