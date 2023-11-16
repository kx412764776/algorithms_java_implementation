package binaryTree;

import java.util.Stack;

public class TestBST {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int preValue = Integer.MIN_VALUE;

    /**
     * judge whether a binary tree is a binary search tree using in-order recursion traversal
     */
    public static boolean isBST1(Node head) {
        if (head == null) {
            return true;
        }
        boolean left = isBST1(head.left);
        if (!left) {
            return false;
        }
        if (head.value <= preValue) {
            return false;
        } else {
            preValue = head.value;
        }
        return isBST1(head.right);
    }

    /**
     * judge whether a binary tree is a binary search tree using in-order un-recursion traversal
     */
    public static boolean isBST2(Node head) {
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    if (head.value <= preValue) {
                        return false;
                    } else {
                        preValue = head.value;
                    }
                    head = head.right;
                }
            }
        }
        return true;
    }

    public static boolean isBST3(Node head) {
        if (head == null) return true;
        return process(head).isBST;
    }

    public static class Info {
        public boolean isBST;
        public int max;
        public int min;
        public Info(boolean isBST, int max, int min) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
        }
    }

    public static Info process(Node head) {
        if (head == null) return null;

        Info leftInfo = process(head.left);
        Info rightInfo = process(head.right);
        int max = head.value;
        if (leftInfo != null) {
            max = Math.max(max, leftInfo.max);
        }
        if (rightInfo != null) {
            max = Math.max(max, rightInfo.max);
        }
        int min = head.value;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
        }
        boolean isBST = true;
        if (leftInfo != null && !leftInfo.isBST) {
            isBST = false;
        }
        if (rightInfo != null && !rightInfo.isBST) {
            isBST = false;
        }
        if (leftInfo != null && leftInfo.max >= head.value) {
            isBST = false;
        }
        if (rightInfo != null && rightInfo.min <= head.value) {
            isBST = false;
        }
        return new Info(isBST, max, min);

    }

    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            preValue = Integer.MIN_VALUE;
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBST3(head) != isBST2(head)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
