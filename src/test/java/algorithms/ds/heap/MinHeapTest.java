package algorithms.ds.heap;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MinHeapTest {

    @Test
    public void testEmptyHeap() {
        IMinHeap<Integer> heap = new MinHeap<>();
        assertTrue(heap.isEmpty());
        try {
            heap.peekMin();
            fail("Should throw Exception here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }

        try {
            heap.popMin();
            fail("Should throw Exception here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }

    @Test
    public void testHeap() {
        IMinHeap<Integer> heap = new MinHeap<>();
        assertTrue(heap.isEmpty());

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        for (int i=0; i<100; i++) {
            assertEquals(i, heap.popMin().intValue());
        }

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        for (int i=0; i<50; i++) {
            assertEquals(i, heap.popMin().intValue());
        }

        for (int i=-100; i<0; i++) {
            heap.add(i);
        }

        int e1, e2=Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            e1 = heap.popMin();
            assertTrue(e1 > e2);
            e2 = e1;
        }
    }

    @Test
    public void testDecrease() {
        IMinHeap<Integer> heap = new MinHeap<>();
        assertTrue(heap.isEmpty());

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        heap.decrease(10, -200);
        heap.decrease(11, -199);
        heap.decrease(12, -198);
        heap.decrease(13, -197);
        assertEquals(-200, heap.popMin().intValue());
        assertEquals(-199, heap.popMin().intValue());
        assertEquals(-198, heap.popMin().intValue());
        assertEquals(-197, heap.popMin().intValue());

        int e1, e2=Integer.MIN_VALUE;
        while (!heap.isEmpty()) {
            e1 = heap.popMin();
            assertTrue(e1 > e2);
            e2 = e1;
        }
    }
}
