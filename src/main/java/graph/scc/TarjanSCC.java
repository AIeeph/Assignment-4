package graph.scc;

import model.Graph;
import java.util.*;

public class TarjanSCC {
    private final Map<Integer, List<Graph.Edge>> adj;
    private final int[] disc, low;
    private final boolean[] inStack;
    private final Deque<Integer> stack = new ArrayDeque<>();
    private final List<List<Integer>> sccList = new ArrayList<>();
    private int time = 0;

    public TarjanSCC(Map<Integer, List<Graph.Edge>> adj, int n) {
        this.adj = adj;
        this.disc = new int[n];
        this.low = new int[n];
        this.inStack = new boolean[n];
        Arrays.fill(disc, -1);
    }

    public List<List<Integer>> findSCCs() {
        for (int i = 0; i < disc.length; i++)
            if (disc[i] == -1)
                dfs(i);
        return sccList;
    }

    private void dfs(int u) {
        disc[u] = low[u] = ++time;
        stack.push(u);
        inStack[u] = true;
        for (Graph.Edge e : adj.get(u)) {
            int v = e.v;
            if (disc[v] == -1) {
                dfs(v);
                low[u] = Math.min(low[u], low[v]);
            } else if (inStack[v]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (low[u] == disc[u]) {
            List<Integer> comp = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                inStack[w] = false;
                comp.add(w);
            } while (w != u);
            sccList.add(comp);
        }
    }
}
