package sortAlgorithms;

import java.util.Arrays;

public class InsertionSort {

    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // make sure arr[0] to arr[i] is sorted
        for(int i = 1; i < arr.length; i++) {
            // compare i with i - 1, if i < i - 1, swap arr[i] and arr[i - 1] until arr[i] >= arr[i - 1]
            for(int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {

        // Math.random(): [0, 1)
        // (int) ((maxSize + 1) * Math.random()): [0, maxSize + 1)
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];

        // generate random array
        for(int i = 0; i < arr.length; i++) {
            // [-maxValue, maxValue]
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue) * Math.random());
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

    // for test
    public static boolean isEquals(int[] arr1, int[] arr2) {
        if((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) return false;
        if(arr1 == null && arr2 == null) return true;
        if(arr1.length != arr2.length) return false;

        for(int i = 0; i < arr1.length; i++) {
            if(arr1[i] != arr2[i]) return false;
        }

        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if(arr == null) return;

        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            insertionSort(arr1);
            comparator(arr2);

            if(!isEquals(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }

        System.out.println(succeed ? "Test finished!" : "This algorithm has some problems!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        insertionSort(arr);
        printArray(arr);
    }

}
