package other;

/**
 * Given a set of items, each with a weight and a value,
 * determine the number of each item to include in a collection
 * so that the total weight is less than or equal to a given limit and the total value is as large as possible.
 */
public class Knapsack {

    public static int maxValue(int[] weights, int[] values, int bag) {
        if (weights == null
                || values == null
                || weights.length != values.length
                || weights.length == 0
        ) {
            return 0;
        }
        return process(weights, values, 0, bag);
    }

    private static int process(int[] weights, int[] values, int index, int rest) {
        if (rest < 0) return -1;
        if (index == weights.length) return 0;
        int p1 = process(weights, values, index + 1, rest);
        int p2 = 0;
        int next = process(weights, values, index + 1, rest - weights[index]);
        if (next != -1) {
            p2 = values[index] + next;
        }
        return Math.max(p1, p2);
    }

    public static int dp(int[] weights, int[] values, int bag) {
        if (weights == null
                || values == null
                || weights.length != values.length
                || weights.length == 0
        ) {
            return 0;
        }

        int N = weights.length;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0; index--) {
            for (int rest = 0; rest <= bag; rest++) {
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                int next = rest - weights[index] < 0 ?
                        -1 : dp[index + 1][rest - weights[index]];
                if (next != -1) {
                    p2 = values[index] + next;
                }
                dp[index][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 3, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(maxValue(weights, values, bag));
        System.out.println(dp(weights, values, bag));
    }
}
