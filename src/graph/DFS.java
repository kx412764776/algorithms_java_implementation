package graph;

import java.util.HashSet;
import java.util.Stack;

public class DFS {

    public static void dfs(Node node) {
        if (node == null) return;

        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(node);
        set.add(node);
        System.out.println(node.value);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts) {
                if (!set.contains(next)) {
                    stack.push(cur);
                    stack.push(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);

        node1.nexts.add(node2);
        node1.nexts.add(node3);
        node1.nexts.add(node4);
        node2.nexts.add(node3);
        node3.nexts.add(node4);
        node3.nexts.add(node5);
        node4.nexts.add(node5);

        dfs(node1);

    }
}
