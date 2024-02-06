package topInterview150.array;

/**
 * Question: 80. Remove Duplicates from Sorted Array II
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most twice and return the new length.
 * Do not allocate extra space for another array; you must do this by modifying the input array in-place with O(1) extra memory.<br>
 * {@code @Link} <a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/">Remove Duplicates from Sorted Array II</a>
 */
public class RemoveDuplicates2 {

    // Time: O(n), Space: O(1)
    public static int removeDuplicates(int[] nums) {

        int i = 0;
        for (int num : nums) {
            // remain the first 2 elements
            // when current number > the last 2nd number
            if (i < 2 || num > nums[i - 2]) {
                nums[i++] = num; // replace the number at i using the current number, then i++
            }
        }
        return i;
    }

    // Test Case
    public static void main(String[] args) {

        int[] nums = new int[]{1, 1, 1, 2, 2, 3};
        System.out.println(removeDuplicates(nums));
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();

        int[] nums2 = new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3};
        System.out.println(removeDuplicates(nums2));
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }
}
