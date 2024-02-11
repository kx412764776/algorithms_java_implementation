package topInterview150.array;

/**
 * 238. Product of Array Except Self<br>
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].<br>
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.<br>
 * You must write an algorithm that runs in O(n) time and without using the division operation.<br>
 * Example 1:<br>
 * Input: nums = [1,2,3,4]<br>
 * Output: [24,12,8,6]<br>
 * {@code Link} <a href="https://leetcode.com/problems/product-of-array-except-self/">Product of Array Except Self</a><br>
 */
public class ProductOfArrayExceptSelf {

    // Time complexity: O(n), Space complexity: O(n)
    public static int[] productExceptSelf1(int[] nums) {

        int n = nums.length;

        // left[] and right[] are used to store the product of elements to the left and right of the current element
        int[] left = new int[n];
        int[] right = new int[n];
        int[] res = new int[n];

        left[0] = 1;
        // left[i] = the left num of nums[i] * left[i - 1]
        for (int i = 1; i < n; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        right[n - 1] = 1;
        // right[i] = the right num of nums[i] * right[i + 1]
        for (int i = n - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < n; i++) {
            res[i] = left[i] * right[i];
        }

        return res;
    }

    // Time complexity: O(n), Space complexity: O(1)
    public static int[] productExceptSelf2(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];

        // store the left product of nums[i]
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] * nums[i - 1];
        }


        int right = 1;
        // multiply the right product of nums[i] to the res[i]
        for (int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        int[] res1 = productExceptSelf1(nums);
        int[] res2 = productExceptSelf2(nums);
        for (int i : res1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : res2) {
            System.out.print(i + " ");
        }
    }
}
