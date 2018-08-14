package algorithms.algo.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SimpleGraph<T> {
    private Map<T, List<T>> graph;

    public SimpleGraph()
    {
        graph = new HashMap<>();
    }

    public Set<T> getVertices() {
        return graph.keySet();
    }

    public void putAdjNodes(T v, T... adjNodes) {
        List<T> list = graph.get(v);
        if (list == null) {
            list = createEmptyAdjNodes(v);
        }
        for (T a : adjNodes) {
            list.add(a);
            if (!graph.containsKey(a)) {
                createEmptyAdjNodes(a);
            }
        }
    }

    private List<T> createEmptyAdjNodes(T v) {
        List<T> list = new ArrayList<>();
        graph.put(v, list);
        return list;
    }

    public List<T> getAdjNodes(T v) {
        return graph.get(v);
    }
}
