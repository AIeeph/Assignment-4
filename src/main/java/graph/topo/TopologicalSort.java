package graph.topo;

import java.util.*;

public class TopologicalSort {
    public static List<Integer> sort(Map<Integer, List<Integer>> dag) {
        Map<Integer, Integer> indeg = new HashMap<>();
        for (int u : dag.keySet()) indeg.putIfAbsent(u, 0);
        for (int u : dag.keySet())
            for (int v : dag.get(u))
                indeg.put(v, indeg.getOrDefault(v, 0) + 1);
        Queue<Integer> q = new ArrayDeque<>();
        for (var e : indeg.entrySet())
            if (e.getValue() == 0) q.add(e.getKey());
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int u = q.poll();
            order.add(u);
            for (int v : dag.getOrDefault(u, List.of())) {
                indeg.put(v, indeg.get(v) - 1);
                if (indeg.get(v) == 0) q.add(v);
            }
        }
        return order;
    }
}
