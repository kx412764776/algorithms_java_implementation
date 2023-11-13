package linkedList;

import java.util.HashMap;

/**
 * A linked list is given such that each node contains an additional random pointer,
 * which could point to any node in the list, or null.
 * Question: Construct a deep copy of the list.
 *           The deep copy should consist of exactly n brand new nodes,
 *           where each new node has its value set to the value of its corresponding original node.
 *           Both the next and random pointer of the new nodes should point to new nodes in the copied list
 *           such that the pointers in the original list and copied list represent the same list state.
 *           None of the pointers in the new list should point to nodes in the original list.
 * For example: If there are two nodes X and Y in the original list,
 *              where X.random --> Y, then for the corresponding two nodes x and y in the copied list,
 *              x.random --> y.
 * Return: The head of the copied linked list.
 * LeetCode: #138. Copy List with Random Pointer
 * Link: https://leetcode.com/problems/copy-list-with-random-pointer/
 */
public class CopyListWithRandomPointer {

    public static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node (int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    /**
     * Solution 1: HashMap
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * @param head
     * @return
     */
    public static Node copyRandomList1 (Node head) {

        if (head == null) return null;

        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;

        // key: old node; value: new node
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }

        cur = head;

        while (cur != null) {
            // cur: old node; map.get(cur): new node
            // according to cur.next and cur.random, set new node's next and random
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }

        return map.get(head);
    }

    /**
     * Solution 2: Manipulate the original list, copy every node after the original node, then separate them
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     * @param head
     * @return
     */
    public static Node copyRandomList2 (Node head) {
        if (head == null) return null;

        Node cur = head;
        Node next = null;

        // copy every node after the original node
        // 1 -> 2 -> 3 -> null => 1 -> 1' -> 2 -> 2' -> 3 -> 3' -> null
        while (cur != null) {
            next = cur.next;
            cur.next = new Node(cur.val);
            cur.next.next = next;
            cur = next;
        }

        // Set random pointer for every new node
        cur = head;
        Node copy = null;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            copy.random = cur.random != null ? cur.random.next : null;
            cur = next;
        }

        // Separate the original list and the copied list
        cur = head;
        Node res = head.next;
        while (cur != null) {
            next = cur.next.next;
            copy = cur.next;
            cur.next = next;
            copy.next = next != null ? next.next : null;
            cur = next;
        }
        return res;
    }
}
