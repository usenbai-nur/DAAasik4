package graph.topo;

import model.Graph;
import org.junit.jupiter.api.Test;
import util.Metrics;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {

    @Test
    void testTopoOrder() {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(0, 3, 1);
        g.addEdge(3, 4, 1);

        Metrics metrics = new Metrics();
        List<Integer> topo = TopologicalSort.sort(g, metrics);

        assertEquals(5, topo.size());
        assertTrue(topo.indexOf(0) < topo.indexOf(1));
        assertTrue(topo.indexOf(1) < topo.indexOf(2));
    }
}
