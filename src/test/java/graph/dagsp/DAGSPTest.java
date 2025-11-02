package graph.dagsp;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGSPTest {
    @Test
    public void testShortestAndLongestPaths() {
        Map<Integer, List<int[]>> dag = new HashMap<>();
        dag.put(0, List.of(new int[]{1, 3}, new int[]{2, 6}));
        dag.put(1, List.of(new int[]{2, 4}, new int[]{3, 4}));
        dag.put(2, List.of(new int[]{3, 8}));
        dag.put(3, List.of());
        List<Integer> topo = List.of(0, 1, 2, 3);
        Map<Integer, Integer> shortest = DAGShortestPaths.shortestPaths(dag, 0, topo);
        assertEquals(0, (int) shortest.get(0));
        assertEquals(3, (int) shortest.get(1));
        assertEquals(6, (int) shortest.get(2));
        assertEquals(7, (int) shortest.get(3));
    }
}
