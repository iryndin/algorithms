package algorithms.ds.lrucache;

import org.junit.Test;

import static org.junit.Assert.*;

public class LRUCacheTest {

    @Test
    public void testEmptyCache() {
        ILRUCache<Integer, String> cache = new LRUCache<>(10);
        assertTrue(cache.isEmpty());
        assertEquals(0, cache.size());
        for (int i=0; i<10;i++) {
            assertNull(cache.get(i));
        }
    }

    @Test
    public void testPut() {
        ILRUCache<Integer, String> cache = new LRUCache<>(10);
        assertTrue(cache.isEmpty());
        assertEquals(0, cache.size());
        for (int i=1; i<1000;i++) {
            cache.put(i, "val_"+i);
            if (i <= 10) {
                assertEquals(i, cache.size());
            } else {
                assertEquals(10, cache.size());
            }
        }
    }

    @Test
    public void testGet() {
        ILRUCache<Integer, String> cache = new LRUCache<>(10);
        assertTrue(cache.isEmpty());
        assertEquals(0, cache.size());
        for (int i=1; i<=10;i++) {
            cache.put(i, "val_"+i);
        }

        for (int i=1; i<=10;i++) {
            assertEquals("val_"+i, cache.get(i));
        }
    }
}
