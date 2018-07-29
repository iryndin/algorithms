package algorithms.ds.heap;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class MaxHeapTest {

    @Test
    public void testEmptyHeap() {
        IMaxHeap<Integer> heap = new MaxHeap<>();
        assertTrue(heap.isEmpty());
        try {
            heap.peekMax();
            fail("Should throw Exception here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }

        try {
            heap.popMax();
            fail("Should throw Exception here");
        } catch (NoSuchElementException ignore) {
            // ignore
        }
    }

    @Test
    public void testHeap() {
        IMaxHeap<Integer> heap = new MaxHeap<>();
        assertTrue(heap.isEmpty());

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        for (int i=99; i>=0; i--) {
            assertEquals(i, heap.popMax().intValue());
        }

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        for (int i=99; i>=50; i--) {
            assertEquals(i, heap.popMax().intValue());
        }

        for (int i=-100; i<0; i++) {
            heap.add(i);
        }

        int e1, e2=Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            e1 = heap.popMax();
            assertTrue(e1 < e2);
            e2 = e1;
        }
    }

    @Test
    public void testIncrease() {
        IMaxHeap<Integer> heap = new MaxHeap<>();
        assertTrue(heap.isEmpty());

        for (int i=0; i<100; i++) {
            heap.add(i);
        }
        assertEquals(100, heap.size());

        heap.increase(10, 200);
        heap.increase(11, 199);
        heap.increase(12, 198);
        heap.increase(13, 197);
        assertEquals(200, heap.popMax().intValue());
        assertEquals(199, heap.popMax().intValue());
        assertEquals(198, heap.popMax().intValue());
        assertEquals(197, heap.popMax().intValue());

        int e1, e2=Integer.MAX_VALUE;
        while (!heap.isEmpty()) {
            e1 = heap.popMax();
            assertTrue(e1 < e2);
            e2 = e1;
        }
    }
}
