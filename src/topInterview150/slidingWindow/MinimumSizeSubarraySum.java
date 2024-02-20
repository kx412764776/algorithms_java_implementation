package topInterview150.slidingWindow;

/**
 * 209. Minimum Size Subarray Sum<br>
 * {@code Param} int: an array of n positive integers and a positive integer target<br>
 * {@code Return} int: the minimal length of a contiguous subarray of which the sum is greater than or equal to target<br>
 * <p>
 *     If there is no such subarray, return 0<br>
 *     {@code Link} <a href="https://leetcode.com/problems/minimum-size-subarray-sum/">Minimum Size Subarray Sum</a><br>
 * </p>
 *
 */
public class MinimumSizeSubarraySum {

    public static int minSubArrayLen(int target, int[] nums) {

        int left = 0;
        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;
        System.out.println(minSubArrayLen(target, nums));

        int[] nums2 = {1, 4, 4};
        int target2 = 4;
        System.out.println(minSubArrayLen(target2, nums2));

        int[] nums3 = {1, 1, 1, 1, 1, 1, 1, 1};
        int target3 = 11;
        System.out.println(minSubArrayLen(target3, nums3));
    }
}
