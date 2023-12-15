package binaryTree;

public class MorrisTraversal {

    public static class Node {
        public int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morris(Node head) {
        if (head == null) return;

        Node cur = head;
        Node mostRight = null;

        while (cur != null) {
            mostRight = cur.left;
            // judge whether cur has left subtree
            if (mostRight != null) {
                // find most right node of cur's left subtree
                while (mostRight != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                // 1. if most right node's right is null
                //    set most right node's right of cur's left subtree to cur
                //    and then cur = cur.left
                // 2. if most right node's right is cur
                //    set most right node's right to null
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }
}
