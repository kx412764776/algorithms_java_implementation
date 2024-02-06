package topInterview150.array;

/**
 * Question: 27. Remove Element
 * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
 * return the remaining count of numbers after removing the val.<br>
 * {@code @Link:} <a href="https://leetcode.com/problems/remove-element/">Remove Element</a>
 */
public class RemoveElement {

    public static int removeElement(int[] nums, int val) {
        int p1 = 0;
        int p2 = nums.length;

        while (p1 < p2) {
            if (nums[p1] == val) {
                nums[p1] = nums[p2 -1];
                p2--;
            }else {
                p1++;
            }
        }
        return p2;
    }

    // Test Case
    public static void main(String[] args) {

        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;
        System.out.println(removeElement(nums, val));

        int[] nums2 = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        System.out.println(removeElement(nums2, val2));
    }
}
