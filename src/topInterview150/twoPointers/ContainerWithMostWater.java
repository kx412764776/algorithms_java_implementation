package topInterview150.twoPointers;

/**
 * 11. Container With Most Water<br>
 * {@code Param} int[]: an array of non-negative integers height of n non-negative integers<br>
 * {@code Return} int: the maximum area of water that can be contained<br>
 * <p>
 *     Find two lines that together with the x-axis forms a container such that the container contains the most water<br>
 *     Notice that you may not slant the container<br>
 *     {@code Link} <a href="https://leetcode.com/problems/container-with-most-water/">Container With Most Water</a><br>
 * </p>
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {

        int maxArea = 0;
        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            int area = (right - left) * Math.min(height[left],height[right]);
            maxArea = Math.max(maxArea, area);

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    public static void main(String[] args) {
        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(height));

        int[] height2 = {1,1};
        System.out.println(maxArea(height2));
    }
}
