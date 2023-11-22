package nQueens;

public class NQueens {

    public static int solution1(int n) {
        if (n < 1) return 0;

        int[] record = new int[n]; // record[i] = k -> the queen's position in i-th row is k-th column
        return process1(0, record, n);
    }

    // i: current row
    // record[0...i-1]: queens' position in 0...i-1 rows
    // n: total number of rows
    private static int process1(int i, int[] record, int n) {
        if (i == n) return 1;

        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    // check if the queen in i-th row can be placed in j-th column
    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            // determine if two queens are in the same column or diagonal
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false;

            }
        }
        return true;
    }

    public static int solution2(int n) {
        if (n < 1 || n > 32) return 0;
        // limit: 1 -> 1, 2 -> 3, 3 -> 7, 4 -> 15, 32 -> all 1
        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    /**
     * @param columnLimit the column limit
     * @param leftDiagonalLimit the left diagonal limit
     * @param rightDiagonalLimit the right diagonal limit
     * @return the number of valid solutions
     */
    private static int process2(int limit,
                                int columnLimit,
                                int leftDiagonalLimit,
                                int rightDiagonalLimit) {
        if (columnLimit == limit) return 1;

        // pos: all the available queen positions in current row
        int pos = limit & (~(columnLimit | leftDiagonalLimit | rightDiagonalLimit));
        int mostRightOne;
        int res = 0;

        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(
                    limit,
                    columnLimit | mostRightOne,
                    (leftDiagonalLimit | mostRightOne) << 1,
                    (rightDiagonalLimit | mostRightOne) >>> 1
            );
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(solution2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(solution1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
