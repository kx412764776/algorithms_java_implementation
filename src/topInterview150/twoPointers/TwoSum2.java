package topInterview150.twoPointers;

/**
 * 167. Two Sum II - Input array is sorted<br>
 * {@code Param} int[]: an array of integers numbers, which is already sorted in non-decreasing order<br>
 * {@code Param} int: an integer target<br>
 * {@code Return} int[]: the indices of the two numbers such that they add up to target<br>
 * <p>
 *     You may assume that each input would have exactly one solution and you may not use the same element twice<br>
 *     You can return the answer in any order<br>
 *     {@code Link} <a href="https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/">Two Sum II - Input array is sorted</a><br>
 * </p>
 */
public class TwoSum2 {

    public static int[] twoSum(int[] numbers, int target) {

        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                return new int[]{left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(numbers, target);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
