package graph.topo;

import model.Graph;
import util.Metrics;
import util.Timer;
import java.util.*;

public class TopologicalSort {
    public static List<Integer> sort(Graph g, Metrics metrics) {
        Timer timer = new Timer();
        timer.start();

        Map<Integer, List<int[]>> adj = g.getAdjList();
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();

        for (int v : adj.keySet()) {
            if (!visited.contains(v)) {
                dfs(v, adj, visited, stack, metrics);
            }
        }

        metrics.setTimeMs(timer.stop());
        List<Integer> result = new ArrayList<>();
        while (!stack.isEmpty()) result.add(stack.pop());
        return result;
    }

    private static void dfs(int v, Map<Integer, List<int[]>> adj, Set<Integer> visited, Stack<Integer> stack, Metrics metrics) {
        visited.add(v);
        for (int[] edge : adj.getOrDefault(v, new ArrayList<>())) {
            metrics.addOperation();
            if (!visited.contains(edge[0])) {
                dfs(edge[0], adj, visited, stack, metrics);
            }
        }
        stack.push(v);
    }
}
