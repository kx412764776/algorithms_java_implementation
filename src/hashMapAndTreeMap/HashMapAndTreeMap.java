package hashMapAndTreeMap;

import java.util.*;

public class HashMapAndTreeMap {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {

        // HashMap (unordered map)
        HashMap<Integer, String> test = new HashMap<>();
        Integer a = 1000000;
        Integer b = 1000000;
        System.out.println(a == b);

        Integer c = 100;
        Integer d = 100;
        System.out.println(c.equals(d));

        test.put(a, "a");
        System.out.println(test.containsKey(a));
        System.out.println(test.containsKey(b));

        Node node1 = new Node(1);
        Node node2 = new Node(1);
        HashMap<Node, String> test2 = new HashMap<>();
        test2.put(node1, "node1");
        System.out.println(test2.containsKey(node1));
        System.out.println(test2.containsKey(node2));

        System.out.println(node1.hashCode());
        System.out.println(node2.hashCode());

        Node node3 = node1;
        test2.put(node3, "node1");
        System.out.println(test2.size());
        System.out.println(node3.hashCode());
        System.out.println("===============================");

        HashMap<Integer, String> test3 = new HashMap<>();
        test3.put(10000, "cost 10000");
        test3.put(2, "cost 2");
        test3.put(3, "cost 3");
        test3.put(4, "cost 4");
        test3.put(5, "cost 5");
        test3.put(10000, "cost 11111");

        System.out.println(test3.containsKey(1));
        System.out.println(test3.containsKey(10));

        System.out.println(test3.get(10000));
        System.out.println(test3.get(10));
        System.out.println(test3.get(4));

        test3.remove(4);
        System.out.println(test3.get(4));

        System.out.println("=============HashSet=============");

        // HashSet (unordered set)
        HashSet<String> set = new HashSet<>();
        set.add("chen");
        System.out.println(set.contains("chen"));
        set.remove("chen");

        System.out.println("=============TreeSet============");

        // TreeSet (ordered set)
        node1 = new Node(5);
        node2 = new Node(3);
        node3 = new Node(7);

        TreeSet<Node> treeSet = new TreeSet<>(); // red-black tree
        // the following line will throw an exception because Node does not implement Comparable interface
        try {
            treeSet.add(node1);
            treeSet.add(node2);
            treeSet.add(node3);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("===============================");

        // the following line will not throw an exception because Integer implements Comparable interface
        TreeSet<Integer> treeSet2 = new TreeSet<>();
        treeSet2.add(5);
        treeSet2.add(3);
        treeSet2.add(7);
        System.out.println(treeSet2.first());
        System.out.println(treeSet2.last());
        System.out.println(treeSet2.lower(4));
        System.out.println(treeSet2.higher(4));
        System.out.println(treeSet2.floor(4)); // <= 4
        System.out.println(treeSet2.ceiling(4)); // >= 4
        System.out.println(treeSet2.size());
        System.out.println(treeSet2.contains(4));

        Iterator<Integer> iterator = treeSet2.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();

        System.out.println("=============TreeMap=============");

        // TreeMap (ordered map)
        // red-black tree, AVL tree, size-balanced tree, skip list
        // TreeMap is implemented by red-black tree (O(logN))
        // show the common methods of TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(7, "seven");
        treeMap.put(5, "five");
        treeMap.put(3, "three");
        treeMap.put(9, "nine");
        treeMap.put(1, "one");
        treeMap.put(8, "eight");

        System.out.println("TreeMap size: " + treeMap.size());
        Iterator<Integer> iterator2 = treeMap.keySet().iterator();
        while (iterator2.hasNext()) {
            Integer key = iterator2.next();
            System.out.print(key + " : " + treeMap.get(key) + " | ");
        }
        System.out.println();

        System.out.println(treeMap.containsKey(3));
        System.out.println(treeMap.get(5));
        System.out.println("The smallest key is: " + treeMap.firstKey());
        System.out.println("The largest key is: " + treeMap.lastKey());
        System.out.println("The largest key smaller than 7 is: " + treeMap.lowerKey(7));
        System.out.println("The smallest key larger than 7 is: " + treeMap.higherKey(7));
        System.out.println("The largest key smaller than or equal to 7 is: " + treeMap.floorKey(7));
        System.out.println("The smallest key larger than or equal to 7 is: " + treeMap.ceilingKey(7));
        treeMap.remove(3);
        System.out.println(treeMap.get(3) + " is removed");
    }
}
