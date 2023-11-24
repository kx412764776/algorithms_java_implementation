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
                }
                distanceMap.put(
                        edge.to,
                        Math.min((distanceMap.get(toNode)), distance + edge.weight)
                );
            }
            visitedNodes.add(minNode);
            minNode = getMinDistanceAndUnvisitedNode(distanceMap, visitedNodes);
        }
        return distanceMap;

    }

    private static Node getMinDistanceAndUnvisitedNode(HashMap<Node, Integer> distanceMap, HashSet<Node> visitedNodes) {
        Node minNode = null;
        int minDistance = Integer.MAX_VALUE;

        for (Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!visitedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    public static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    public static class NodeHeap {

        private Node[] nodes;

        private HashMap<Node, Integer> heapIndexMap;
        private HashMap<Node, Integer> distanceMap;
        private int size;

        public NodeHeap(int size) {
            nodes = new Node[size];
            heapIndexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            size = 0;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (inHeap(node)) { // update
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                insertHeapify(node, heapIndexMap.get(node));
            }
            if (!isEntered(node)) { // add
                nodes[size] = node;
                heapIndexMap.put(node, size);
                distanceMap.put(node, distance);
                insertHeapify(node, size++);
            }
        }

        public NodeRecord pop() {
            NodeRecord nodeRecord = new NodeRecord(nodes[0], distanceMap.get(nodes[0]));
            swap(0, size - 1);
            heapIndexMap.put(nodes[size - 1], -1);
            distanceMap.remove(nodes[size - 1]);
            nodes[size - 1] = null;
            heapify(0, --size);
            return nodeRecord;
        }

        private void insertHeapify(Node node, int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index, int size) {
            int leftChildIndex = (index * 2) + 1;
            while (leftChildIndex < size) {
                int smallest =
                        distanceMap.get(nodes[leftChildIndex + 1]) < distanceMap.get(nodes[leftChildIndex]) ?
                                leftChildIndex + 1 : leftChildIndex;
                smallest = distanceMap.get(nodes[smallest]) < distanceMap.get(nodes[index]) ?
                        smallest : index;

                if (smallest == index) break;

                swap(smallest, index);
                index = smallest;
                leftChildIndex = index * 2 + 1;
            }
        }

        private boolean isEntered(Node node) {
            return heapIndexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && heapIndexMap.get(node) != -1;
        }

        private void swap(int index1, int index2) {
            heapIndexMap.put(nodes[index1], index2);
            heapIndexMap.put(nodes[index2], index1);
            Node tmp = nodes[index1];
            nodes[index1] = nodes[index2];
            nodes[index2] = tmp;
        }
    }

    public static HashMap<Node, Integer> dijkstra2(Node head, int size) {
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(head, 0);
        HashMap<Node, Integer> result = new HashMap<>();

        while (!nodeHeap.isEmpty()) {
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + distance);
            }
            result.put(cur, distance);
        }
        return result;
    }
}
