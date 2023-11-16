package binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * find the lowest common ancestor of two nodes in a binary tree
 */
public class LowestCommonAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    // node1 and node2 must be in the tree
    public static Node lowestCommonAncestor1(Node head, Node node1, Node node2) {
        if (head == null) return null;
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);

        HashSet<Node> nodeSet = new HashSet<>();
        Node cur = node1;
        nodeSet.add(cur);

        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            nodeSet.add(cur);
        }
        cur = node2;
        while (!nodeSet.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    // Solution 2
    public static Node lowestCommonAncestor2(Node head, Node node1, Node node2) {
        if (head == null) return null;
        return process(head, node1, node2).ans;
    }

    public static class Info {
        public boolean findNode1;
        public boolean findNode2;
        public Node ans;

        public Info(boolean findNode1, boolean findNode2, Node ans) {
            this.findNode1 = findNode1;
            this.findNode2 = findNode2;
            this.ans = ans;
        }
    }

    public static Info process(Node head, Node node1, Node node2) {
        if (head == null) return new Info(false, false, null);

        Info leftInfo = process(head.left, node1, node2);
        Info rightInfo = process(head.right, node1, node2);
        boolean findA = (head == node1) || leftInfo.findNode1 || rightInfo.findNode1;
        boolean findB = (head == node2) || leftInfo.findNode2 || rightInfo.findNode2;
        Node ans = null;
        if (leftInfo.ans != null) {
            ans = leftInfo.ans;
        } else if (rightInfo.ans != null) {
            ans = rightInfo.ans;
        } else {
            if (findA && findB) {
                ans = head;
            }
        }
        return new Info(findA, findB, ans);
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

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestCommonAncestor1(head, o1, o2) != lowestCommonAncestor2(head, o1, o2)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
