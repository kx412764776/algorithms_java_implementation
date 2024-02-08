package topInterview150.array;

/**
 * Question: 121. Best Time to Buy and Sell Stock<br>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.<br>
 * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.<br>
 * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.<br>
 * {@code @Link} <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock/">Best Time to Buy and Sell Stock</a>
 */
public class BestTimeToBuyAndSellStock {

    // Time: O(n), Space: O(1)
    public static int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i = 0; i <prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }

    // Test Case
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // 5
    }
}
