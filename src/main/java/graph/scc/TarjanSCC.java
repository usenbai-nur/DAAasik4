package graph.scc;

import model.Graph;
import util.Metrics;
import util.Timer;
import java.util.*;

public class TarjanSCC {
    private Map<Integer, List<int[]>> adj;
    private Map<Integer, Integer> indexMap;
    private Map<Integer, Integer> lowLink;
    private Stack<Integer> stack;
    private Set<Integer> onStack;
    private int index;
    private List<List<Integer>> sccList;
    private Metrics metrics = new Metrics();

    public List<List<Integer>> findSCCs(Graph g) {
        Timer timer = new Timer();
        timer.start();

        adj = g.getAdjList();
        indexMap = new HashMap<>();
        lowLink = new HashMap<>();
        stack = new Stack<>();
        onStack = new HashSet<>();
        sccList = new ArrayList<>();
        index = 0;

        for (int v : adj.keySet()) {
            if (!indexMap.containsKey(v)) {
                strongConnect(v);
            }
        }

        metrics.setTimeMs(timer.stop());
        return sccList;
    }

    private void strongConnect(int v) {
        indexMap.put(v, index);
        lowLink.put(v, index);
        index++;
        stack.push(v);
        onStack.add(v);
        metrics.addOperation();

        for (int[] edge : adj.getOrDefault(v, new ArrayList<>())) {
            int w = edge[0];
            if (!indexMap.containsKey(w)) {
                strongConnect(w);
                lowLink.put(v, Math.min(lowLink.get(v), lowLink.get(w)));
            } else if (onStack.contains(w)) {
                lowLink.put(v, Math.min(lowLink.get(v), indexMap.get(w)));
            }
        }

        if (lowLink.get(v).equals(indexMap.get(v))) {
            List<Integer> scc = new ArrayList<>();
            int w;
            do {
                w = stack.pop();
                onStack.remove(w);
                scc.add(w);
            } while (w != v);
            sccList.add(scc);
        }
    }

    public Metrics getMetrics() {
        return metrics;
    }
}
