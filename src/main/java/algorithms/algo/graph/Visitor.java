package algorithms.algo.graph;

public interface Visitor<T> {
    boolean visit(SimpleGraph<T> g, T vertex);
}
