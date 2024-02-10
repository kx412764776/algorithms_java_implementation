package topInterview150.array;

import java.util.*;

/**
 * 380. Insert Delete GetRandom O(1)<br>
 * Implement the RandomizedSet class:<br>
 * RandomizedSet() Initializes the RandomizedSet object.<br>
 * bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.<br>
 * bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.<br>
 * int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called).<br>
 * Example 1:<br>
 * Input<br>
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]<br>
 * [[], [1], [2], [2], [], [1], [2], []]<br>
 * Output<br>
 * [null, true, false, true, 2, true, false, 2]<br>
 * {@code Link} <a href="https://leetcode.com/problems/insert-delete-getrandom-o1/">Insert Delete GetRandom O(1)</a><br>
 */
public class InsertDeleteGetRandom {

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        System.out.println(obj.insert(1));
        System.out.println(obj.remove(2));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
        System.out.println(obj.remove(1));
        System.out.println(obj.insert(2));
        System.out.println(obj.getRandom());
    }

}

class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    Random random = new Random();

    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;

        map.put(val, list.size());
        list.add(list.size(), val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        // move the last element to the place idx of the element to delete
        int lastElement = list.getLast();
        int idx = map.get(val);
        list.set(idx, lastElement);
        map.put(lastElement, idx);

        // delete the last element
        list.removeLast();
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}
