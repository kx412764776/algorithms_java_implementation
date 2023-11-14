package binaryTree;

public class RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    // recursive
    public static void recursiveTraversalBT(Node head) {
        if (head == null) return;

        recursiveTraversalBT(head.left);
        recursiveTraversalBT(head.right);
    }

    // recursive sequence: self, left, right
    public static void preOrderRecur(Node head) {
        if (head == null) return;

        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    // recursive sequence: left, self, right
    public static void inOrderRecur(Node head) {
        if (head == null) return;

        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    // recursive sequence: left, right, self
    public static void posOrderRecur(Node head) {
        if (head == null) return;

        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println("recursiveTree:");
        recursiveTraversalBT(head);
        System.out.println();

        System.out.println("preOrderRecur:");
        preOrderRecur(head);
        System.out.println();

        System.out.println("inOrderRecur:");
        inOrderRecur(head);
        System.out.println();

        System.out.println("posOrderRecur:");
        posOrderRecur(head);
        System.out.println();
    }
}
