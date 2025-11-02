package graph.scc;

import model.Graph;
import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {
    @Test
    public void testSCC() {
        Graph g = new Graph();
        g.n = 5;
        g.edges = new ArrayList<>();
        g.edges.add(edge(0, 1));
        g.edges.add(edge(1, 2));
        g.edges.add(edge(2, 0));
        g.edges.add(edge(1, 3));
        g.edges.add(edge(3, 4));
        Map<Integer, List<Graph.Edge>> adj = g.toAdjacencyList();
        TarjanSCC t = new TarjanSCC(adj, g.n);
        List<List<Integer>> sccs = t.findSCCs();
        assertEquals(3, sccs.size());
    }

    private Graph.Edge edge(int u, int v) {
        Graph.Edge e = new Graph.Edge();
        e.u = u; e.v = v; e.w = 1;
        return e;
    }
}
