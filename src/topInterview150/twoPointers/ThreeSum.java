package topInterview150.twoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum<br>
 * {@code Param} int[]: an integer array nums, of n integers<br>
 * {@code Return} List<List<Integer>>: all the unique triplets in the array which gives the sum of zero<br>
 * <p>
 *     The solution set must not contain duplicate triplets<br>
 *     {@code Link} <a href="https://leetcode.com/problems/3sum/">3Sum</a><br>
 * </p>
 */
public class ThreeSum {

    // Time complexity: O(n^2)
    // Space complexity: O(log n) Because Arrays.sort() method uses quicksort algorithm which has O(log n) space complexity
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        // traverse nums[0] to nums[i] where nums[i] <= 0
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            // if nums[i] is the same as the previous number, skip it
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSum(nums, i, res);
            }
        }
        return res;
    }

    private static void twoSum(int[] nums, int i, List<List<Integer>> res) {
        int left = i + 1;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum < 0) {
                left++;
            } else if (sum > 0) {
                right--;
            } else {
                res.add(Arrays.asList(nums[i], nums[left++], nums[right--]));
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        for (List<Integer> list : result) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
