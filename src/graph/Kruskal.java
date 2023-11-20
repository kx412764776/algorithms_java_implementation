package graph;

import java.util.*;

// undirected graph only
// used for finding the minimum spanning tree
public class Kruskal {

    public static class MySets{
        public HashMap<Node, List<Node>> setMap;

        public MySets(List<Node> nodes) {
            for (Node cur : nodes) {
                List<Node> set = new ArrayList<>();
                set.add(cur);
                setMap.put(cur, set);
            }
        }

        public boolean isSameSet(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            return fromSet == toSet;
        }

        public void union(Node from, Node to) {
            List<Node> fromSet = setMap.get(from);
            List<Node> toSet = setMap.get(to);
            for (Node toNode : toSet) {
                fromSet.add(toNode);
                setMap.put(toNode, fromSet);
            }
        }
    }

    // Union-Find Set
    public static class UnionFind {
        // key: a node, value: the parent of the node
        private HashMap<Node, Node> parentMap;
        // key: a representative node of a set, value: the size of the set
        private HashMap<Node, Integer> sizeMap;

        public UnionFind() {
            parentMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();
        }

        public void makeSets(Collection<Node> nodes) {
            parentMap.clear();
            sizeMap.clear();
            for (Node node : nodes) {
                parentMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        private Node findParent(Node node) {
            Stack<Node> path = new Stack<>();
            while (node != parentMap.get(node)) {
                path.add(node);
                node = parentMap.get(node);
            }
            while (!path.isEmpty()) {
                parentMap.put(path.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(Node a, Node b) {
            return findParent(a) == findParent(b);
        }

        public void union(Node a, Node b) {
            if (a == null || b == null) return;

            Node aParent = findParent(a);
            Node bParent = findParent(b);
            if (aParent != bParent) {
                int aSetSize = sizeMap.get(aParent);
                int bSetSize = sizeMap.get(bParent);
                if (aSetSize <= bSetSize) {
                    parentMap.put(aParent, bParent);
                    sizeMap.put(bParent, aSetSize + bSetSize);
                    sizeMap.remove(aParent);
                } else {
                    parentMap.put(bParent, aParent);
                    sizeMap.put(aParent, aSetSize + bSetSize);
                    sizeMap.remove(bParent);
                }
            }
        }
    }

    public static class EdgeComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> kruskalMST(Graph graph) {
        UnionFind unionFind = new UnionFind();
        unionFind.makeSets(graph.nodes.values());

        // from small edge to big edge using min heap
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>(new EdgeComparator());
        for (Edge edge : graph.edges) {
            priorityQueue.add(edge);
        }
        Set<Edge> result = new HashSet<>();
        while (!priorityQueue.isEmpty()) {
            Edge edge = priorityQueue.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                result.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return result;
    }
}
