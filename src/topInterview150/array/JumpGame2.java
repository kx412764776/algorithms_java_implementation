package topInterview150.array;

/**
 * Question: 45. Jump Game II<br>
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].<br>
 * Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at nums[i],
 * you can jump to any nums[i + j] where:<br>
 *    <ul>
 *        <li>0 <= j <= nums[i]</li>
 *        <li>i + j < n</li>
 *    </ul>
 * {@code @Return} the minimum number of jumps to reach nums[n - 1].<br>
 * {@code @Link} <a href="https://leetcode.com/problems/jump-game-ii/">Jump Game II</a>
 */
public class JumpGame2 {

    // Time: O(n), Space: O(1)
    public int jump(int[] nums) {

        int curEnd = 0;
        int curFar = 0;
        int res = 0;

        for (int i = 0; i < nums.length - 1; ++i) {
            // find the farthest position can reach
            curFar = Math.max(curFar, i + nums[i]);

            // update curEnd when reach the current end
            if (i == curEnd) {
                res++;
                curEnd = curFar;
            }
        }

        return res;
    }

    // Test Case
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, 1, 1, 4};
        JumpGame2 jumpGame2 = new JumpGame2();
        System.out.println(jumpGame2.jump(nums));

        int[] nums2 = new int[]{2, 3, 0, 1, 4};
        System.out.println(jumpGame2.jump(nums2));
    }
}
