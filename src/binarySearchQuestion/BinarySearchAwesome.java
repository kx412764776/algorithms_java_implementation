package binarySearchQuestion;

public class BinarySearchAwesome {

    /**
     * Question:
     * search for the index of the local minimum value in an array
     */
    public static int getLessIndex(int[] arr) {
        if (arr == null || arr.length == 1) return -1;

        if (arr.length == 1 || arr[0] < arr[1]) return 0;

        if (arr[arr.length - 1] < arr[arr.length - 2]) return arr.length - 1;

        int left = 1;
        int right = arr.length - 2;
        int mid = 0;
        while (left < right) {
            mid = (left + right) >> 1;
            if (arr[mid] > arr[mid - 1]) {
                right = mid - 1;
            } else if (arr[mid] > arr[mid + 1]) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return left;

    }

    // verify the result is a local minimum value or not
    public static boolean isRight(int[] arr, int index) {
        if (arr.length <= 1) {
            return true;
        }
        if (index == 0) {
            return arr[index] < arr[index + 1];
        }
        if (index == arr.length - 1) {
            return arr[index] < arr[index - 1];
        }
        return arr[index] < arr[index - 1] && arr[index] < arr[index + 1];
    }

    // for generate random array
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        arr[0] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
        for (int i = 1; i < arr.length; i++) {
            do {
                arr[i] = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            } while (arr[i] == arr[i - 1]);
        }
        return arr;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 1000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int result = getLessIndex(arr);
            if (!isRight(arr, result)) {
                succeed = false;
                System.out.println("Something wrong!");
                break;
            }
        }

        System.out.println("Test " + testTime + " times, succeed: " + succeed);
    }
}
