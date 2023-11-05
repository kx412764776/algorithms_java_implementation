package binarySearchQuestion;

import java.util.Arrays;

public class BinarySearch {

    /**
     * Binary search
     * find the index of target in a sorted array
     * @param arr sorted array
     * @param target number
     * @return index of target, -1 if not found
     */
    public static int binarySearch(int[] arr, int target) {
        if(arr == null || arr.length == 0) return -1;

        int left = 0;
        int right = arr.length - 1;

        // separate the array into two parts, find the target in the part that contains the target
        while(left <= right) {
            int mid = left + ((right - left) >> 1);

            if(arr[mid] == target) {
                return mid;
            } else if(arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }

        return -1;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {

        // Math.random(): [0, 1)
        // (maxSize + 1) * Math.random(): [0, maxSize + 1)
        int[] arr = new int[(int)((maxSize + 1) * Math.random())];

        // generate random array
        for(int i = 0; i < arr.length; i++) {
            // [-maxValue, maxValue]
            arr[i] = (int)((maxValue + 1) * Math.random()) - (int)((maxValue) * Math.random());
        }

        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if(arr == null) return null;

        int[] res = new int[arr.length];

        for(int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int maxSize = 100;
        int maxValue = 100;

        int[] arr = generateRandomArray(maxSize, maxValue);

        Arrays.sort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        int[] arr2 = copyArray(arr);
        for (int i : arr2) {
            System.out.print(i + " ");
        }
        System.out.println();

        // random generate a target index from 0 to 100
        int targetIndex = (int)((arr.length + 1) * Math.random());
        int target = arr[targetIndex];
        System.out.println("target index: " + targetIndex);

        int index = binarySearch(arr, target);
        System.out.println("index: " + index);

        int index2 = Arrays.binarySearch(arr2, target);
        System.out.println("expected index: " + index2);

        System.out.println(index == index2);


    }

}
