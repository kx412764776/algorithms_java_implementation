package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class IsCBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * Check if a binary tree is a complete binary tree
     */
    public static boolean isCBT(Node head) {
        if (head == null) return true;

        Queue<Node> queue = new LinkedList<>();

        boolean noChild = false; // if encounter a node that has no left child or right child
        Node left = null;
        Node right = null;
        queue.add(head);

        while (!queue.isEmpty()) {
            head = queue.poll();
            left = head.left;
            right = head.right;
            // if encounter a node that doesn't have one child
            // then find current node is not a leaf node
            if (
                    (noChild && (left != null || right != null)) || (left == null && right != null)
            ) {
                return false;
            }
            if (left != null) {
                queue.add(left);
            }
            if (right != null) {
                queue.add(right);
            }
            if (left == null || right == null); {
                noChild = true;
            }
        }
        return true;
    }
}
