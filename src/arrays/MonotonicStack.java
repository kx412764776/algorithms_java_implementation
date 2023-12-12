package arrays;

import java.util.Stack;

/**
 * @Algorithm: Monotonic Stack
 * @Question: Given an array of integers,
 * find the left and right indices of the element that is smaller than the current element.
 */
public class MonotonicStack {

    public static int[][] getNearLessNoRepeat(int[] arr) {
        int[][] res = new int[arr.length][2];
        // store the index of the element and guarantee the stack is monotonic
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int j = stack.pop();
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                res[j][0] = leftLessIndex;
                res[j][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            res[j][0] = leftLessIndex;
            res[j][1] = -1;
        }
        return res;
    }
}
