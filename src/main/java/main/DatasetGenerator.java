package main;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DatasetGenerator {

    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {
        generateDataset("src/main/resources/data/small_graphs.json", 3, 6, 10);
        generateDataset("src/main/resources/data/medium_graphs.json", 3, 10, 20);
        generateDataset("src/main/resources/data/large_graphs.json", 3, 20, 50);
        System.out.println("All datasets generated successfully in /resources/data/");
    }

    private static void generateDataset(String filePath, int count, int minNodes, int maxNodes) throws IOException {
        JSONArray graphs = new JSONArray();

        for (int i = 1; i <= count; i++) {
            int nodeCount = random.nextInt(maxNodes - minNodes + 1) + minNodes;
            List<String> nodes = new ArrayList<>();
            for (int n = 0; n < nodeCount; n++) nodes.add(String.valueOf((char) ('A' + n)));

            JSONArray edges = new JSONArray();
            int edgeCount = nodeCount + random.nextInt(nodeCount * 2);

            for (int e = 0; e < edgeCount; e++) {
                String from = nodes.get(random.nextInt(nodes.size()));
                String to = nodes.get(random.nextInt(nodes.size()));
                if (from.equals(to)) continue; // avoid self-loops
                int weight = random.nextInt(20) + 1;

                JSONObject edge = new JSONObject();
                edge.put("from", from);
                edge.put("to", to);
                edge.put("weight", weight);
                edges.put(edge);
            }

            JSONObject graph = new JSONObject();
            graph.put("id", i);
            graph.put("nodes", new JSONArray(nodes));
            graph.put("edges", edges);

            graphs.put(graph);
        }

        JSONObject output = new JSONObject();
        output.put("graphs", graphs);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(output.toString(4));
        }

        System.out.println("Generated " + count + " graphs at: " + filePath);
    }
}
