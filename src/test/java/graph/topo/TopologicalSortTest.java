package graph.topo;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {
    @Test
    public void testTopoOrder() {
        Map<Integer, List<Integer>> dag = new HashMap<>();
        dag.put(0, List.of(1, 2));
        dag.put(1, List.of(3));
        dag.put(2, List.of(3));
        dag.put(3, List.of());
        List<Integer> order = TopologicalSort.sort(dag);
        assertEquals(4, order.size());
        assertTrue(order.indexOf(0) < order.indexOf(3));
    }
}
