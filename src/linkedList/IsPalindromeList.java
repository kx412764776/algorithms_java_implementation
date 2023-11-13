package linkedList;

import java.util.Stack;

/**
 * Question:
 * Given a singly linked list, determine if it is a palindrome.
 * For example: 1 -> 2 -> 2 -> 1 is a palindrome list.
 *             1 -> 2 -> 3 -> 4 is not a palindrome list.
 * LeetCode: #234. Palindrome Linked List
 * Link: https://leetcode.com/problems/palindrome-linked-list/
 */
public class IsPalindromeList {

    public static class Node {
        public int value;
        public Node next;

        public Node (int value) {
            this.value = value;
        }
    }

    // Solution 1: Stack
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static boolean isPalindrome1 (Node head) {
        Stack<Node> stack  = new Stack<>();
        Node cur = head;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    // Solution 2: Quick and Slow Pointers
    // 1. Reverse the second half of the list
    // 2. Compare the first half and the second half
    // 3. Recover the list
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static boolean isPalindrome2 (Node head) {
        if (head == null || head.next == null) return true;

        Node slow = head;
        Node fast = head;

        // find mid node
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;// slow -> mid
            fast = fast.next.next;// fast -> end
        }

        // 1. Reverse the second half of the list
        fast = slow.next;// fast -> right part first node
        slow.next = null;// mid.next -> null
        Node node = null;
        while (fast != null) {
            node = fast.next; // prev -> save next node
            fast.next = slow; // reverse right part node
            // move slow and fast pointer to next node
            slow = fast;
            fast = node;
        }

        // 2. check palindrome
        node = slow; // node -> save last node
        fast = head; // fast -> left first node
        boolean res = true;
        while (slow != null && fast != null) {
            if (slow.value != fast.value) {
                res = false;
                break;
            }
            slow = slow.next;
            fast = fast.next;
        }

        // 3. recover the list
        slow = node.next; // slow -> node after the last node
        node.next = null; // last node.next -> null
        while (slow != null) {
            fast = slow.next; // fast -> save next node in reversed list
            slow.next = node; // reverse right part node
            node = slow; // node -> move to next node
            slow = fast; // slow -> move to next node
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome2(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");
    }
}
