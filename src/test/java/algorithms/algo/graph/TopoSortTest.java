package algorithms.algo.graph;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TopoSortTest {

    @Test
    public void testKahn() {
        SimpleGraph<String> g = createGraph();
        Optional<List<String>> sorted = TopoSort.kahn(g);
        assertTrue(sorted.isPresent());
        assertEquals(Arrays.asList("a", "b", "c", "d", "e"), sorted.get());
    }

    @Test
    public void testKahnWithCyclicGraph() {
        SimpleGraph<String> g = createGraphWithCycle();
        Optional<List<String>> sorted = TopoSort.kahn(g);
        assertFalse(sorted.isPresent());
    }

    // create graph from here:
    // https://commons.wikimedia.org/wiki/File:Tred-G.svg?uselang=ru
    private SimpleGraph<String> createGraph() {
        SimpleGraph<String> g = new SimpleGraph<>();
        g.putAdjNodes("a", "b", "c", "d", "e");
        g.putAdjNodes("b", "d");
        g.putAdjNodes("c", "d", "e");
        g.putAdjNodes("d", "e");
        return g;
    }

    private SimpleGraph<String> createGraphWithCycle() {
        SimpleGraph<String> g = createGraph();
        g.putAdjNodes("e", "b");
        return g;
    }
}
