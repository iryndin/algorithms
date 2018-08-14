package algorithms.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TopoSort {

    public static <T> Optional<List<T>> kahn(SimpleGraph<T> g) {
        List<T> result = new ArrayList<>();
        Map<T, Integer> incomingEdgeMap = getVerticesWithIncominEdgesPower(g);

        while (true) {
            Optional<T> nodeWithoutIncomingEdgesOpt = getFirstNodeWithNoIncomingEdges(incomingEdgeMap);
            if (!nodeWithoutIncomingEdgesOpt.isPresent()) {
                break;
            }
            T nodeWithoutIncomingEdges = nodeWithoutIncomingEdgesOpt.get();
            incomingEdgeMap.remove(nodeWithoutIncomingEdges);
            result.add(nodeWithoutIncomingEdges);
            // now remove edges outcoming from nodeWithoutIncomingEdges
            g.getAdjNodes(nodeWithoutIncomingEdges).forEach(v -> {
                // v is vertex, which has incoming edge from vertex i
                incomingEdgeMap.put(v, incomingEdgeMap.get(v)-1);
            });
        }
        // Now, if incomingEdgeMap is not empty, then there are cycles, and return error
        // else, return result
        return incomingEdgeMap.isEmpty() ? Optional.of(result) : Optional.empty();

    }

    private static <T> Optional<T> getFirstNodeWithNoIncomingEdges(Map<T, Integer> map) {
        return map.entrySet().stream()
                .filter(e -> e.getValue() == 0)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    /**
     * Return a map, where keys are vertices, and values are number of incoming edges into this vertex
     * @param g graph to count it
     * @return
     */
    public static <T> Map<T, Integer> getVerticesWithIncominEdgesPower(SimpleGraph<T> g) {
        Map<T, Integer> incomingEdgesMap = new HashMap<>();
        for (T v : g.getVertices()) {
            incomingEdgesMap.put(v, 0);
        }
        for (T n : g.getVertices()) {
            g.getAdjNodes(n).forEach(v -> {
                // v is vertex, which has incoming edge from vertex n
                incomingEdgesMap.put(v, incomingEdgesMap.get(v)+1);
            });
        }
        return incomingEdgesMap;
    }
}