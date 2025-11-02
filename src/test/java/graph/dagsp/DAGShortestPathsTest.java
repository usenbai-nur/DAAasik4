package graph.dagsp;

import model.Graph;
import org.junit.jupiter.api.Test;
import util.Metrics;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class DAGShortestPathsTest {

    @Test
    void testShortestPath() {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 2);

        Metrics metrics = new Metrics();
        List<Integer> topo = List.of(0, 1, 2, 3);
        Map<Integer, Integer> dist = DAGShortestPaths.shortestPath(g, 0, topo, metrics);

        assertEquals(0, dist.get(0));
        assertEquals(5, dist.get(1));
        assertEquals(8, dist.get(2));
        assertEquals(10, dist.get(3));
    }

    @Test
    void testLongestPath() {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 3);
        g.addEdge(2, 3, 2);

        Metrics metrics = new Metrics();
        List<Integer> topo = List.of(0, 1, 2, 3);
        Map<Integer, Integer> dist = DAGShortestPaths.longestPath(g, 0, topo, metrics);

        assertEquals(0, dist.get(0));
        assertEquals(5, dist.get(1));
        assertEquals(8, dist.get(2));
        assertEquals(10, dist.get(3));
    }
}
