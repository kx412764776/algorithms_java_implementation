package linkedList;

/**
 * Question:
 * Given a linked list and a target value T
 * Adjust the linked list so that left parts of list are all smaller than T,
 * middle parts are all equal to T, and right parts are all greater than T.
 * For example: 4 -> 2 -> 7 -> 1 -> 3 -> 5 -> 6 -> null, target = 4,
 * return 2 -> 1 -> 3 -> 4 -> 7 -> 5 -> 6 -> null.
 * Assumption:
 * The target value T is not null.
 * LeetCode: #86. Partition List
 * Link: https://leetcode.com/problems/partition-list/
 */
public class SmallerEqualBigger {

    public static class Node {
        public int value;
        public Node next;

        public Node (int value) {
            this.value = value;
        }
    }

    /**
     * Solution 1: Use an array to store all nodes,
     *             then partition the array and reconstruct the linked list.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static Node listPartition1 (Node head, int pivot) {
        if (head == null) return null;

        Node cur = head;
        int i = 0;
        while (cur != null) {
            i++;
            cur = cur.next;
        }

        Node[] nodeArr = new Node[i];
        i = 0;
        cur = head;
        for (i = 0; i != nodeArr.length; i++) {
            nodeArr[i] = cur;
            cur = cur.next;
        }
        arrPartition(nodeArr, pivot);
        for (i = 1; i != nodeArr.length; i++) {
            nodeArr[i - 1].next = nodeArr[i];
        }
        nodeArr[i - 1].next = null;
        return nodeArr[0];
    }

    private static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;
        int big = nodeArr.length;
        int index = 0;
        while (index != big) {
            if (nodeArr[index].value < pivot) {
                swap(nodeArr, ++small, index++);
            } else if (nodeArr[index].value == index) {
                index++;
            } else {
                swap(nodeArr, --big, index);
            }
        }
    }

    private static void swap(Node[] nodeArr, int a, int b) {
        Node temp = nodeArr[a];
        nodeArr[a] = nodeArr[b];
        nodeArr[b] = temp;
    }

    // Solution 2: use six pointers to divide the list into three parts
    // Time Complexity: O(N)
    // Space Complexity: O(1)
    public static Node listPartition2(Node head, int pivot) {
        Node sH = null; // small head
        Node sT = null; // small tail
        Node eH = null; // equal head
        Node eT = null; // equal tail
        Node bH = null; // big head
        Node bT = null; // big tail
        Node next = null; // save next node
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sH.next = head;
                    sT = head;
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eH.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bH.next = head;
                    bT = head;
                }
            }
            head = next;
        }

        // connect three parts
        // small part tail connect equal part head
        if (sT != null) {
            sT.next = eH;
            eT = eT == null ? sT : eT;
        }

        // equal part tail connect big part head
        // if equal part exist, eT -> equal part tail
        // if equal part not exist, sT -> small part tail
        // make sure eT is not null
        if (eT != null) {
            eT.next = bH;
        }

        return sH != null ? sH : (eH != null ? eH : bH);
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
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        // head1 = listPartition1(head1, 4);
        head1 = listPartition2(head1, 5);
        printLinkedList(head1);

    }
}
