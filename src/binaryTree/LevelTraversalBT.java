package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Print binary tree by level, from top to bottom and from left to right
 */
public class LevelTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void levelTraversal (Node head) {
        System.out.println("level order: ");
        if (head == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.print(cur.value + " ");
            if (cur.left != null) {
                queue.add(cur.left);
            }
            if (cur.right != null) {
                queue.add(cur.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        levelTraversal(head);
        System.out.println("========");
    }
}
