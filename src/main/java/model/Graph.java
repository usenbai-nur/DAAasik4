package model;

import java.util.*;

public class Graph {
    private final Map<Integer, List<int[]>> adjList;
    private final boolean directed;

    public Graph(boolean directed) {
        this.directed = directed;
        this.adjList = new HashMap<>();
    }

    public void addEdge(int from, int to, int weight) {
        adjList.putIfAbsent(from, new ArrayList<>());
        adjList.putIfAbsent(to, new ArrayList<>());
        adjList.get(from).add(new int[]{to, weight});

        if (!directed) {
            adjList.get(to).add(new int[]{from, weight});
        }
    }

    public Map<Integer, List<int[]>> getAdjList() {
        return adjList;
    }

    public int size() {
        return adjList.size();
    }

    public Set<Integer> getVertices() {
        return adjList.keySet();
    }
}
