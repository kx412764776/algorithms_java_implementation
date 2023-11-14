package linkedList;

import java.util.HashSet;

/**
 * Question:
 * Given two singly linked lists, determine if the two lists intersect.
 * You should first determine if the two lists have a common node.
 * Return the intersecting node. Note that the intersection is defined based on reference, not value.
 * Assumption:
 * If the two linked lists have no intersection at all, return null.
 * The linked lists must retain their original structure after the function returns.
 */
public class FindFirstIntersectNode {

    public static class Node {
        public int value;
        public Node next;

        public Node (int value) {
            this.value = value;
        }
    }

    // Solution 1: Use a hash set to store all nodes of one linked list, then traverse the other linked list
    // Time Complexity: O(N)
    // Space Complexity: O(N)
    public static Node getIntersectNode1 (Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        HashSet<Node> set = new HashSet<>();
        Node cur = head1;
        while (cur != null) {
            set.add(cur);
            cur = cur.next;
        }

        cur = head2;
        while (cur != null) {
            if (set.contains(cur)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    // Solution 2: Use two pointers to find the first intersect node
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static Node getIntersectNode2(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }

        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, head2, loop1, loop2);
        }

        return null;
    }

    // Method: find the loop node of a linked list
    // if it does not exist, return null
    public static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (slow != fast) {
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }

        fast = head;
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    // If both linked lists have no loop, return the first intersect node if exists
    // If not, return null
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;

        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;

        // get the subtraction of length of two linked lists
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }

        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }

        cur1 = n > 0 ? head1 : head2; // cur1: the longer linked list
        cur2 = cur1 == head1 ? head2 : head1; // cur2: the smaller linked list

        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // If both linked lists have loop, return the first intersect node if exists
    private static Node bothLoop(Node head1, Node head2, Node loop1, Node loop2) {

        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            // loop1 != loop2
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1; // or loop2
                }
                cur1 = cur1.next;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode2(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode2(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode2(head1, head2).value);
    }
}
