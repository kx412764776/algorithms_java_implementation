package topInterview150.array;

/**
 * 42. Trapping Rain Water<br>
 * Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.<br>
 * {@code Link} <a href="https://leetcode.com/problems/trapping-rain-water/">Trapping Rain Water</a><br>
 */
public class TrappingRainWater {

    // Brute Force
    // Time complexity: O(n^2), Space complexity: O(1)
    public static int trap1(int[] height) {

        int res = 0;
        int size = height.length;

        for(int i = 0; i < size; i++) {
            int leftMax = 0, rightMax = 0;
            for (int j = i; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }

            for (int j = i; j < size; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }

            res += Math.min(leftMax, rightMax) - height[i];
        }

        return res;
    }

    // Two Pointers
    // Time complexity: O(n), Space complexity: O(1)
    public static int trap2(int[] height) {
        if (height == null || height.length == 0) return 0;

        int left = 0, right = height.length - 1;
        int res = 0;
        int leftMax = 0, rightMax = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    res += (leftMax - height[left]);
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    res += (rightMax - height[right]);
                }
                right--;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap1(height));
        System.out.println(trap2(height));

        int[] height2 = {4,2,0,3,2,5};
        System.out.println(trap1(height2));
        System.out.println(trap2(height2));
    }
}
