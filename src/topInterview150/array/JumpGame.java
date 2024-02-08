package topInterview150.array;

/**
 * Question: 55. Jump Game<br>
 * You are given an integer array nums.
 * You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position. <br>
 * Return true if you can reach the last index, or false otherwise.<br>
 * {@code @Link} <a href="https://leetcode.com/problems/jump-game/">Jump Game</a>
 */
public class JumpGame {

    public static boolean canJump(int[] nums) {
        int last = nums.length - 1;

        for(int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] + i >= last) {
                last = i;
            }
        }

        return last == 0;
    }

    // Test Case
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        System.out.println(canJump(nums)); // true

        int[] nums2 = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(nums2)); // false
    }
}
