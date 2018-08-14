package algorithms.algo.graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DFSTest {

    static class TestVisitor<T> implements Visitor<T> {

        private final List<T> visitedNodes = new ArrayList<>();

        @Override
        public boolean visit(SimpleGraph<T> g, T vertex) {
            visitedNodes.add(vertex);
            System.out.println(vertex);
            return true;
        }

        public List<T> getVisitedNodes() {
            return visitedNodes;
        }
    }

    static class StoppingTestVisitor<T> implements Visitor<T> {

        private final List<T> visitedNodes = new ArrayList<>();
        private final T stopVertex;

        public StoppingTestVisitor(T stopVertex) {
            this.stopVertex = stopVertex;
        }

        @Override
        public boolean visit(SimpleGraph<T> g, T vertex) {
            visitedNodes.add(vertex);
            System.out.println(vertex);
            return !vertex.equals(stopVertex);
        }

        public List<T> getVisitedNodes() {
            return visitedNodes;
        }
    }



    @Test
    public void testDfsRecursive() {
        SimpleGraph<String> g = createGraph();
        TestVisitor<String> visitor = new TestVisitor<>();
        DFS.dfsRecursive(g, "b", visitor);
        assertEquals(Arrays.asList("b", "d", "e", "a", "c"), visitor.getVisitedNodes());
    }

    @Test
    public void testDfsRecursiveWithStop() {
        SimpleGraph<String> g = createGraph();
        StoppingTestVisitor<String> visitor = new StoppingTestVisitor<>("e");
        DFS.dfsRecursive(g, "b", visitor);
        assertEquals(Arrays.asList("b", "d", "e"), visitor.getVisitedNodes());
    }

    @Test
    public void testDfsIterative() {
        SimpleGraph<String> g = createGraph();
        TestVisitor<String> visitor = new TestVisitor<>();
        DFS.dfsIterative(g, "b", visitor);
        assertEquals(Arrays.asList("b", "d", "e", "a", "c"), visitor.getVisitedNodes());
    }

    @Test
    public void testDfsIterativeWithStop() {
        SimpleGraph<String> g = createGraph();
        StoppingTestVisitor<String> visitor = new StoppingTestVisitor<>("e");
        DFS.dfsIterative(g, "b", visitor);
        assertEquals(Arrays.asList("b", "d", "e"), visitor.getVisitedNodes());
    }

    // create graph from here (similar except edge from 'e' to 'a'):
    // https://commons.wikimedia.org/wiki/File:Tred-G.svg?uselang=ru
    private SimpleGraph<String> createGraph() {
        SimpleGraph<String> g = new SimpleGraph<>();
        g.putAdjNodes("a", "b", "c", "d", "e");
        g.putAdjNodes("b", "d");
        g.putAdjNodes("c", "d", "e");
        g.putAdjNodes("d", "e");
        g.putAdjNodes("e", "a");
        return g;
    }
}
