package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

// For edges without negative weights
// used for finding the shortest path from a source node to all other nodes
public class Dijkstra {

    // return the shortest distance from head to all other nodes
    public static HashMap<Node, Integer> dijkstra1(Node head) {
        // key: a node
        // value: the shortest distance from head to the node
        // if a node is not visited, the distance is Integer.MAX_VALUE
        HashMap<Node, Integer> distanceMap = new HashMap<>();
        distanceMap.put(head, 0);

        // store nodes that have been visited
        HashSet<Node> visitedNodes = new HashSet<>();
        Node minNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedNodes);

        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            visitedNodes.add(minNode);
            minNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnvisitedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> visited) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!visited.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }
}
