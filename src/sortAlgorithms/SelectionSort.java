package sortAlgorithms;

public class SelectionSort {

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        // from 0 to arr.length - 1, find the min value in the subarray arr[i, arr.length - 1], swap it with arr[i]
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    // swap two elements in an array
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // test
    public static void main(String[] args) {
        int[] arr = new int[]{2, 7, 4, 9, 2, 5, 0, 3, 4, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        System.out.println("--------------------");
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
