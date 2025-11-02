package model;

import java.util.*;

public class Graph {
    public boolean directed;
    public int n;
    public List<Edge> edges;
    public int source;

    public static class Edge {
        public int u, v, w;
    }

    public Map<Integer, List<Edge>> toAdjacencyList() {
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i = 0; i < n; i++) adj.put(i, new ArrayList<>());
        for (Edge e : edges) adj.get(e.u).add(e);
        return adj;
    }
}
