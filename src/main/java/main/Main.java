package main;

import graph.dagsp.DAGShortestPaths;
import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import model.Graph;
import util.Metrics;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Graph g = new Graph(true);
        g.addEdge(0, 1, 5);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 6);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 4, 4);
        g.addEdge(3, 4, -1);
        g.addEdge(4, 5, 2);

        TarjanSCC scc = new TarjanSCC();
        System.out.println("Strongly Connected Components: " + scc.findSCCs(g));
        System.out.println(scc.getMetrics());

        Metrics topoMetrics = new Metrics();
        List<Integer> topo = TopologicalSort.sort(g, topoMetrics);
        System.out.println("Topological Order: " + topo);
        System.out.println(topoMetrics);

        Metrics shortMetrics = new Metrics();
        Metrics longMetrics = new Metrics();
        System.out.println("Shortest Paths: " + DAGShortestPaths.shortestPath(g, 0, topo, shortMetrics));
        System.out.println(shortMetrics);
        System.out.println("Longest Paths: " + DAGShortestPaths.longestPath(g, 0, topo, longMetrics));
        System.out.println(longMetrics);
    }
}
