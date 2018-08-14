package algorithms.algo.graph;

import algorithms.ds.stack.IStack;
import algorithms.ds.stack.LinkedListStack;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class DFS {

    public static <T> void dfsRecursive(SimpleGraph<T> g, Visitor<T> visitor) {
        dfsRecursive(g, g.getVertices().iterator().next(), visitor);
    }

    public static <T> void dfsRecursive(SimpleGraph<T> g, T startVertex, Visitor<T> visitor) {
        dfsRecursive(startVertex, g, visitor, new HashSet<>(), new AtomicBoolean(true));
    }

    private static <T> void dfsRecursive(T vertex, SimpleGraph<T> g, Visitor<T> visitor, Set<T> visited, AtomicBoolean continueTraverse) {
        if (continueTraverse.get()) {
            if (!visited.contains(vertex)) {
                boolean continueTraversing = visitor.visit(g, vertex);
                visited.add(vertex);
                if (continueTraversing) {
                    for (T adjacentNodes : g.getAdjNodes(vertex)) {
                        dfsRecursive(adjacentNodes, g, visitor, visited, continueTraverse);
                    }
                } else {
                    continueTraverse.set(false);
                }
            }
        }
    }

    public static <T> void dfsIterative(SimpleGraph<T> g, Visitor<T> visitor) {
        dfsIterative(g, g.getVertices().iterator().next(), visitor);
    }

    public static <T> void dfsIterative(SimpleGraph<T> g, T startNode, Visitor<T> visitor) {
        IStack<T> stack = new LinkedListStack<>();
        stack.push(startNode);
        Set<T> visited = new HashSet<>();

        while (!stack.isEmpty()) {
            T vertex = stack.pop();
            if (!visited.contains(vertex)) {
                boolean continueTraversing = visitor.visit(g, vertex);
                visited.add(vertex);
                if (continueTraversing) {
                    for (T adjNode : g.getAdjNodes(vertex)) {
                        stack.push(adjNode);
                    }
                } else {
                    break;
                }
            }
        }
    }
}
