package binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Question: Get the max width of a binary tree
 */
public class GetMaxWidth {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node (int value) {
            this.value = value;
        }
    }

    /**
     * Solution 1: use hashmap to record the level of each node
     */
    public static int maxWidthUseMap(Node head) {
        if (head == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);

        // levelMap: key: node, value: node's level
        HashMap<Node, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // width of current level
        int curLevelNodes = 0; // number of nodes of current level
        int max = 0; // max width

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            int curNodeLevel = levelMap.get(cur); // level of current node

            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }

            // judge if current node level is the same as current level
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else { // if not, update max width and add current level, reset current level nodes
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }

        max = Math.max(max, curLevelNodes);
        return max;
    }

    /**
     * Solution 2: use variables to record the level of each node
     */
    public static int maxWidthNoMap (Node head) {
        if (head == null) return 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        Node curEnd = head; // end node of current level
        Node nextEnd = null; // end node of next level
        int max = 0;
        int curLevelNodes = 0; // number of nodes of current level

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.left != null) {
                queue.add(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right != null) {
                queue.add(cur.right);
                nextEnd = cur.right;
            }
            curLevelNodes++;
            if (cur == curEnd) {
                max = Math.max(max, curLevelNodes);
                curLevelNodes = 0;
                curEnd = nextEnd;
            }
        }
        return max;
    }

    // for test
    public static Node generateRandomBST (int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    private static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;

        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
