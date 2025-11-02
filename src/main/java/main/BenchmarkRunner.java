package main;

import graph.scc.TarjanSCC;
import graph.topo.TopologicalSort;
import graph.dagsp.DAGShortestPaths;
import model.Graph;
import util.Metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BenchmarkRunner {
    public static void main(String[] args) throws IOException {
        int[] sizes = {10, 50, 100, 200, 500};
        Random rand = new Random();

        try (FileWriter writer = new FileWriter("results.csv")) {
            writer.write("Algorithm,Vertices,Edges,Operations,Time(ms)\n");

            for (int size : sizes) {
                Graph g = new Graph(true);
                int edges = size * 2;

                for (int i = 0; i < edges; i++) {
                    int u = rand.nextInt(size);
                    int v = u + rand.nextInt(size - u);
                    if (v < size && u != v) {
                        g.addEdge(u, v, rand.nextInt(10) + 1);
                    }
                }

                TarjanSCC tarjan = new TarjanSCC();
                tarjan.findSCCs(g);
                writer.write(String.format("TarjanSCC,%d,%d,%d,%.4f\n",
                        size, edges, tarjan.getMetrics().getOperations(), tarjan.getMetrics().getTimeMs()));

                Metrics topoMetrics = new Metrics();
                List<Integer> topo = TopologicalSort.sort(g, topoMetrics);
                writer.write(String.format("TopologicalSort,%d,%d,%d,%.4f\n",
                        size, edges, topoMetrics.getOperations(), topoMetrics.getTimeMs()));

                Metrics shortM = new Metrics();
                DAGShortestPaths.shortestPath(g, 0, topo, shortM);
                writer.write(String.format("ShortestPath,%d,%d,%d,%.4f\n",
                        size, edges, shortM.getOperations(), shortM.getTimeMs()));

                Metrics longM = new Metrics();
                DAGShortestPaths.longestPath(g, 0, topo, longM);
                writer.write(String.format("LongestPath,%d,%d,%d,%.4f\n",
                        size, edges, longM.getOperations(), longM.getTimeMs()));

                writer.flush();
            }
        }

        System.out.println("Results saved in results.csv");
    }
}
