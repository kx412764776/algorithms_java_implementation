package topInterview150.array;

/**
 * Question: 189. Rotate Array
 * Given an array, rotate the array to the right by k steps, where k is non-negative. <br>
 * <p>
 *     Example 1:
 *     Input: nums = [1,2,3,4,5,6,7], k = 3
 *     Output: [5,6,7,1,2,3,4]
 * </p>
 * {@code @Link} <a href="https://leetcode.com/problems/rotate-array/">Rotate Array</a>
 */
public class RotateArray {

    public static void rotate(int[] nums, int k) {
        k %= nums.length; // in case k > nums.length
        reverseNum(nums, 0, nums.length - 1); // 1234567 -> 7654321
        reverseNum(nums, 0, k - 1); // 7654321 -> 5674321
        reverseNum(nums, k, nums.length - 1); // 5674321 -> 5671234
    }

    private static void reverseNum(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = temp;
        }
    }

    // Test Case
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}
