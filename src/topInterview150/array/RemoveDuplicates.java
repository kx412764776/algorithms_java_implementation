package topInterview150.array;

/**
 * Question: 26. Remove Duplicates from Sorted Array
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same.<br>
 * {@code Return} the new length of the array after removing the duplicates.<br>
 * {@code Link} <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array/">Remove Duplicates from Sorted Array</a>
 */
public class RemoveDuplicates {

    // Two pointers
    // Time: O(n), Space: O(1)
    public static int removeDuplicates(int[] nums) {
        int j = 1;
        // from index 1 traverse the array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i]; // set the new value to the index j, and j++
                j++;
            }
        }

        return j;
    }

    // Test Case
    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(removeDuplicates(nums));
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] nums2 = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicates(nums2));
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
