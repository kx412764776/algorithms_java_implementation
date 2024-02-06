package topInterview150.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Question: 169. Majority Element
 * Given an array nums of size n, return the majority element.
 * The majority element is the element that appears more than ⌊n / 2⌋ times.
 * You may assume that the majority element always exists in the array.<br>
 * {@code @Link} <a href="https://leetcode.com/problems/majority-element/">Majority Element</a>
 */
public class MajorityElement {

    // Boyer-Moore Voting Algorithm
    // Time: O(n), Space: O(1)
    public static int majorityElement1(int[] nums) {
        int count = 0;
        int candidate = 0;


        for (int num : nums) {
            if (count == 0) {
                candidate = num; // reset candidate
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }

    // Time: O(n), Space: O(n)
    public static int majorityElement2(int[] nums) {
        Map<Integer, Integer> counts = countNums(nums);

        Map.Entry<Integer, Integer> majorityEntry = null;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }

        return majorityEntry.getKey();
    }

    private static Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            } else {
                counts.put(num, counts.get(num) + 1);
            }
        }
        return counts;
    }

    // Test Case
    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 3};
        System.out.println(majorityElement1(nums));
        System.out.println(majorityElement2(nums));

        int[] nums2 = new int[]{2, 2, 1, 1, 1, 2, 2};
        System.out.println(majorityElement1(nums2));
        System.out.println(majorityElement2(nums2));

    }
}
