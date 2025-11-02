package graph.dagsp;

import model.Graph;
import util.Metrics;
import util.Timer;
import java.util.*;

public class DAGShortestPaths {

    public static Map<Integer, Integer> shortestPath(Graph g, int start, List<Integer> topoOrder, Metrics metrics) {
        Timer timer = new Timer();
        timer.start();

        Map<Integer, List<int[]>> adj = g.getAdjList();
        Map<Integer, Integer> dist = new HashMap<>();

        for (int v : g.getVertices()) dist.put(v, Integer.MAX_VALUE);
        if (!dist.containsKey(start)) dist.put(start, 0);
        dist.put(start, 0);

        for (int u : topoOrder) {
            Integer currentDist = dist.get(u);
            if (currentDist == null || currentDist == Integer.MAX_VALUE) continue;

            for (int[] edge : adj.getOrDefault(u, new ArrayList<>())) {
                int v = edge[0];
                int weight = edge[1];
                metrics.addOperation();
                if (dist.get(v) > currentDist + weight) {
                    dist.put(v, currentDist + weight);
                }
            }
        }

        metrics.setTimeMs(timer.stop());
        return dist;
    }

    public static Map<Integer, Integer> longestPath(Graph g, int start, List<Integer> topoOrder, Metrics metrics) {
        Timer timer = new Timer();
        timer.start();

        Map<Integer, List<int[]>> adj = g.getAdjList();
        Map<Integer, Integer> dist = new HashMap<>();

        for (int v : g.getVertices()) dist.put(v, Integer.MIN_VALUE);
        if (!dist.containsKey(start)) dist.put(start, 0);
        dist.put(start, 0);

        for (int u : topoOrder) {
            Integer currentDist = dist.get(u);
            if (currentDist == null || currentDist == Integer.MIN_VALUE) continue;

            for (int[] edge : adj.getOrDefault(u, new ArrayList<>())) {
                int v = edge[0];
                int weight = edge[1];
                metrics.addOperation();
                if (dist.get(v) < currentDist + weight) {
                    dist.put(v, currentDist + weight);
                }
            }
        }

        metrics.setTimeMs(timer.stop());
        return dist;
    }
}
