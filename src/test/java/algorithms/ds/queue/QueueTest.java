package algorithms.ds.queue;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class QueueTest {

    @Test
    public void testEmptyArrayBasedQueue() {
        testEmptyQueue(new ArrayBasedQueue<>());
    }

    @Test
    public void testArrayBasedQueue() {
        testQueue(new ArrayBasedQueue<>());
    }

    @Test
    public void testEmptyLinkedListBasedQueue() {
        testEmptyQueue(new LinkedListBasedQueue<>());
    }

    @Test
    public void testLinkedListBasedQueue() {
        testQueue(new LinkedListBasedQueue<>());
    }

    @Test
    public void testEmptyStackBasedQueue() {
        testEmptyQueue(new StackBasedQueue<>());
    }

    @Test
    public void testStackBasedQueue() {
        testQueue(new StackBasedQueue<>());
    }

    public void testEmptyQueue(IQueue<String> queue) {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("Should throw NoSuchElementException here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }

    public void testQueue(IQueue<Integer> queue) {
        assertEquals(0, queue.size());
        for (int i=1; i<=1000; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }

        assertEquals(1000, queue.size());

        for (int i=1; i<=500; i++) {
            assertEquals(i, queue.dequeue().intValue());
            assertEquals(1000-i, queue.size());
        }

        assertEquals(500, queue.size());

        for (int i=1001; i<=10000; i++) {
            queue.enqueue(i);
            assertEquals(i-500, queue.size());
        }

        assertEquals(9500, queue.size());

        int i=501;
        int size=9500;
        while (!queue.isEmpty()) {
            assertEquals(i, queue.dequeue().intValue());
            assertEquals(--size, queue.size());
            i++;
        }

        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("Should throw NoSuchElementException here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }
}
