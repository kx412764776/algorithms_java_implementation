package topInterview150.array;

/**
 * Question: 88. Merge Sorted Array
 * Given two sorted integer arrays nums1 and nums2,
 * two integers m and n representing the number of elements in nums1 and nums2 respectively.
 * Merge nums1 and nums2 into a single sorted array.
 * The final sorted array should store in nums1.<br>
 * {@code @Link} <a href="https://leetcode.com/problems/merge-sorted-array/">Merge Sorted Array</a>
 */
public class MergeSortedArray {

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1;
        int p2 = n - 1;

        for (int i = m + n - 1; i >= 0; i--) {
            if (p2 < 0) break;

            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[i] = nums1[p1--];
            } else {
                nums1[i] = nums2[p2--];
            }
        }
    }

    // Test Case
    public static void main(String[] args) {

        int[] nums1 = new int[]{1, 2, 3, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6};
        merge(nums1, 3, nums2, 3);
        for (int num : nums1) {
            System.out.print(num + " ");
        }
    }
}
