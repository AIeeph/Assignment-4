package graph.dagsp;

import java.util.ArrayList;
import java.util.List;

public class DAGLongestPaths {

    private int n; 
    private List<Integer>[] adj;  
    private int[] dp; 
    private boolean[] visited;

    public DAGLongestPaths(int n) {
        this.n = n;
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        dp = new int[n];
        visited = new boolean[n];
    }

    public void addEdge(int u, int v) {
        adj[u].add(v);
    }

    private int dfs(int u) {
        if (visited[u])
            return dp[u];
        visited[u] = true;
        int maxLength = 0;
        for (int v : adj[u]) {
            maxLength = Math.max(maxLength, dfs(v));
        }
        dp[u] = maxLength + 1;
        return dp[u];
    }

    public int findLongestPath() {
        int maxPathLength = 0;
        for (int i = 0; i < n; i++) {
            maxPathLength = Math.max(maxPathLength, dfs(i));
        }
        return maxPathLength - 1; 
    }

    public static void main(String[] args) {
        DAGLongestPaths graph = new DAGLongestPaths(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        int longestPathLength = graph.findLongestPath();
        System.out.println("Longest path length in DAG is " + longestPathLength);
    }
}
