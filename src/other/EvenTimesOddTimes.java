package other;

public class EvenTimesOddTimes {

    /**
     * Question:
     * In an array, there are only one number appears odd times, others appear even times.
     * Find the number that appears odd times.
     * @param arr an array of integers
     */
    public static void findOddTimesNum1(int[] arr) {
        int eor = 0;
        // XOR all elements in the array
        for (int cur : arr) {
            eor ^= cur;
        }

        System.out.println(eor);
    }

    /**
     * Question:
     * In an array, there are only two numbers appear odd times, others appear even times.
     * Find the two numbers that appear odd times.
     * @param arr an array of integers
     */
    public static void findOddTimesNum2(int[] arr) {
        int eor = 0;

        // XOR all elements in the array
        for (int cur : arr) {
            eor ^= cur;
        }

        // extract the rightmost 1
        int rightMostOne = eor & (~eor + 1);

        int onlyOne = 0;
        // filter out the numbers that have 1 in the rightmost position,
        // these numbers contain one of the two numbers that appear odd times
        for (int cur : arr) {
            if ((cur & rightMostOne) == 0) {
                onlyOne ^= cur;
            }
        }

        System.out.println(onlyOne + " " + (eor ^ onlyOne));
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 7, 4, 9, 2, 5, 0, 3, 4, 4, 5};
        int[] arr2 = new int[]{2, 7, 4, 9, 2, 5, 0, 3, 4, 4, 5, 7};
        findOddTimesNum1(arr1);
        findOddTimesNum2(arr2);
    }
}
