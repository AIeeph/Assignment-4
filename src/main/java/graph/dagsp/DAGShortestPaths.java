package graph.dagsp;

import java.util.*;

public class DAGShortestPaths {
    public static Map<Integer, Integer> shortestPaths(Map<Integer, List<int[]>> dag, int source, List<Integer> topo) {
        Map<Integer, Integer> dist = new HashMap<>();
        for (int v : dag.keySet()) dist.put(v, Integer.MAX_VALUE);
        dist.put(source, 0);
        for (int u : topo) {
            if (dist.get(u) == Integer.MAX_VALUE) continue;
            for (int[] e : dag.getOrDefault(u, List.of())) {
                int v = e[0], w = e[1];
                if (dist.get(u) + w < dist.get(v))
                    dist.put(v, dist.get(u) + w);
            }
        }
        return dist;
    }
}
