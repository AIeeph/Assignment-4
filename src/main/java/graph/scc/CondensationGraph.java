package graph.scc;

import model.Graph;
import java.util.*;

public class CondensationGraph {
    public static Map<Integer, List<Integer>> buildCondensation(List<List<Integer>> sccs, Map<Integer, List<Graph.Edge>> adj) {
        Map<Integer, Integer> compIndex = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++)
            for (int v : sccs.get(i))
                compIndex.put(v, i);
        Map<Integer, Set<Integer>> dagSet = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++) dagSet.put(i, new HashSet<>());
        for (int u : adj.keySet()) {
            int cu = compIndex.get(u);
            for (Graph.Edge e : adj.get(u)) {
                int cv = compIndex.get(e.v);
                if (cu != cv) dagSet.get(cu).add(cv);
            }
        }
        Map<Integer, List<Integer>> dag = new HashMap<>();
        for (int i = 0; i < sccs.size(); i++)
            dag.put(i, new ArrayList<>(dagSet.get(i)));
        return dag;
    }
}
