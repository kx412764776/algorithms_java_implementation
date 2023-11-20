package graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

// undirected graph only
public class Prim {

    public static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge e1, Edge e2) {
            return e1.weight - e2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {

        PriorityQueue<Edge> minHeap = new PriorityQueue<>(new EdgeComparator());
        HashSet<Node> visited = new HashSet<>();

        Set<Edge> result = new HashSet<>();

        for (Node node : graph.nodes.values()) {
            if (!visited.contains(node)) {
                visited.add(node);
                // add all edges from node to minHeap
                for (Edge edge : node.edges) {
                    minHeap.add(edge);
                }
                while (!minHeap.isEmpty()) {
                    Edge edge = minHeap.poll(); // get the smallest edge
                    Node toNode = edge.to; // toNode might be a new node
                    if (!visited.contains(toNode)) {
                        visited.add(toNode);
                        result.add(edge);
                        for (Edge nextEdge : toNode.edges) {
                            minHeap.add(nextEdge);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     * undirected graph only
     * graph[i][j] represents the distance from node i to node j,
     * if there is no edge between i and j, graph[i][j] = Integer.MAX_VALUE
     * @return sum of paths of minimum spanning tree
     */
    public static int prim(int[][] graph) {
        int size = graph.length;
        int[] distances = new int[size];
        boolean[] visited = new boolean[size];
        visited[0] = true;

        for (int i = 0; i < size; i++) {
            distances[i] = graph[0][i];
        }

        int sum = 0;
        for (int i = 0; i < size; i++) {
            int minPath = Integer.MAX_VALUE;
            int minIndex = -1;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distances[j] < minPath) {
                    minPath = distances[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                return sum;
            }
            visited[minIndex] = true;
            sum += minPath;
            for (int j = 0; j < size; j++) {
                if (!visited[j] && distances[j] > graph[minIndex][j]) {
                    distances[j] = graph[minIndex][j];
                }
            }
        }
        return sum;
    }
}
