package greedyAlgrithm;

import java.util.PriorityQueue;

/**
 * @Description:
 * A gold bar cut in half is going to cost the same number of coins as the length value.
 * For example, a gold bar with a length of 20 will cost 20 coins no matter how long it is cut in half.
 * A group of people want to divide the whole gold bar into whole pieces,
 * how can they do it in a way that saves the most coins?
 * @Example:
 * given the array {10,20,30}, representing a total of three people,
 * the whole gold bar has a length of 10+20+30=60.The gold bar has to be divided into 10,20,30 parts.
 * If, first, the gold bar of length 60 is divided into 10 and 50, costing 60,
 * and then the gold bar of length 50 is divided into 20 and 30, costing 50,
 * the total cost is 110 coins.
 * However, if the gold bar of length 60 is divided into 30 and 30 first, costing 60,
 * and then the gold bar of length 30 is divided into 10 and 20, costing 30,
 * the total cost is 90 coins, which is the minimum cost.
 * @Question: Enter an array that returns the minimum cost of the division.
 */
public class LessMoneySplitGold {

    public static int testLessMoney(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0);
    }

    public static int process(int[] arr, int pre) {
        if (arr.length == 1) {
            return pre;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, process(copyAndMergeTwo(arr, i, j), pre + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] ans = new int[arr.length - 1];
        int ansi = 0;
        for (int arri = 0; arri < arr.length; arri++) {
            if (arri != i && arri != j) {
                ans[ansi++] = arr[arri];
            }
        }
        ans[ansi] = arr[i] + arr[j];
        return ans;
    }

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            minHeap.add(arr[i]);
        }

        int sum = 0, cur = 0;
        while (minHeap.size() > 1) {
            cur = minHeap.poll() + minHeap.poll();
            sum += cur;
            minHeap.add(cur);
        }
        return sum;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        System.out.println("Test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney(arr) != testLessMoney(arr)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
