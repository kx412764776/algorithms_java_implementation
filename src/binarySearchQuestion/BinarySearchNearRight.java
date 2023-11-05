package binarySearchQuestion;

import java.util.Arrays;

public class BinarySearchNearRight {

    /**
     * Question:
     * Search for the index of the number that is smaller than or equal to the target
     * @param arr sorted array
     * @param target given number
     */
    public static int nearestIndex(int[] arr, int target) {
        if(arr == null || arr.length == 0) return -1;

        int left = 0;
        int right = arr.length - 1;
        int index = -1;

        // separate the array into two parts, find the target in the part that contains the target
        while(left <= right) {
            int mid = left + ((right - left) >> 1);

            if(arr[mid] <= target) {
                index = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return index;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random()); // [-maxValue, maxValue]
            if (nearestIndex(arr, value) != test(arr, value)) {
                printArray(arr);
                System.out.println(value);
                System.out.println(nearestIndex(arr, value));
                System.out.println(test(arr, value));
                succeed = false;
                break;
            }

            System.out.println(succeed ? "This algorithm works!" : "This algorithm has some problems!");
        }
    }


}
