package graph.scc;

import model.Graph;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TarjanSCCTest {

    @Test
    void testSingleSCC() {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 0, 1);

        TarjanSCC scc = new TarjanSCC();
        List<List<Integer>> result = scc.findSCCs(g);

        assertEquals(1, result.size());
        assertTrue(result.get(0).containsAll(List.of(0, 1, 2)));
    }

    @Test
    void testMultipleSCCs() {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(3, 4, 1);

        TarjanSCC scc = new TarjanSCC();
        List<List<Integer>> result = scc.findSCCs(g);

        assertTrue(result.size() >= 2);
    }
}
