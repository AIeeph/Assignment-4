import com.google.gson.*;
import model.Graph;
import graph.scc.*;
import graph.topo.TopologicalSort;
import graph.dagsp.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream in = Main.class.getResourceAsStream("/data/tasks.json");
        if (in == null) throw new FileNotFoundException("tasks.json not found in resources/data/");
        String json = new String(in.readAllBytes());
        Gson gson = new Gson();
        Graph g = gson.fromJson(json, Graph.class);

        Map<Integer, List<Graph.Edge>> adj = g.toAdjacencyList();
        TarjanSCC tarjan = new TarjanSCC(adj, g.n);
        List<List<Integer>> sccs = tarjan.findSCCs();

        System.out.println("SCCs:");
        for (int i = 0; i < sccs.size(); i++)
            System.out.println(i + ": " + sccs.get(i));

        Map<Integer, List<Integer>> dag = CondensationGraph.buildCondensation(sccs, adj);
        List<Integer> topo = TopologicalSort.sort(dag);
        System.out.println("Topological order: " + topo);

        Map<Integer, List<int[]>> dagWeighted = new HashMap<>();
        for (int u : dag.keySet()) dagWeighted.put(u, new ArrayList<>());
        for (int u : adj.keySet()) {
            for (Graph.Edge e : adj.get(u)) {
                int cu = getCompIndex(u, sccs);
                int cv = getCompIndex(e.v, sccs);
                if (cu != cv) dagWeighted.get(cu).add(new int[]{cv, e.w});
            }
        }

        int srcComp = getCompIndex(g.source, sccs);
        Map<Integer, Integer> shortest = DAGShortestPaths.shortestPaths(dagWeighted, srcComp, topo);

        System.out.println("Shortest paths: " + shortest);

        DAGLongestPaths longestPathFinder = new DAGLongestPaths(sccs.size());
        for (int u = 0; u < sccs.size(); u++) {
            for (int[] edge : dagWeighted.getOrDefault(u, new ArrayList<>())) {
                int v = edge[0];
                longestPathFinder.addEdge(u, v);
            }
        }
        int longestLength = longestPathFinder.findLongestPath();
        System.out.println("Longest path length in condensation graph is " + longestLength);
    }

    private static int getCompIndex(int v, List<List<Integer>> sccs) {
        for (int i = 0; i < sccs.size(); i++)
            if (sccs.get(i).contains(v))
                return i;
        return -1;
    }
}
