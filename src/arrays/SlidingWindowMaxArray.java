package arrays;

import java.util.LinkedList;

/**
 * Question: Given an array of integers and a number w,
 * return an array of maximums of each subarray of size w.
 * Example: [10, 5, 2, 7, 8, 7] and w = 3
 * Answer: [10, 7, 8, 8]
 */
public class SlidingWindowMaxArray {

    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        // store the index of the max element in the window
        LinkedList<Integer> subArrayMaxIndex = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            while (!subArrayMaxIndex.isEmpty() &&
                    arr[subArrayMaxIndex.peekLast()] <= arr[i]
            ) {
                subArrayMaxIndex.pollLast();
            }
            subArrayMaxIndex.addLast(i);
            if (!subArrayMaxIndex.isEmpty() &&
                    subArrayMaxIndex.peekFirst() == i - w) {
                subArrayMaxIndex.pollFirst();
            }
            if (i >= w - 1) {
                res[index++] = arr[subArrayMaxIndex.peekFirst()];
            }
        }
        return res;
    }

    public static int[] bruteForce(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        int N = arr.length;
        int[] res = new int[N - w + 1];
        int index = 0;
        int L = 0;
        int R = w - 1;

        while (R < N) {
            int max = arr[L];
            for (int i = L + 1; i <= R; i++) {
                max = Math.max(max, arr[i]);
            }
            res[index++] = max;
            L++;
            R++;
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("Test begin:");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = getMaxWindow(arr, w);
            int[] ans2 = bruteForce(arr, w);
            if (!isEqual(ans1, ans2)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
