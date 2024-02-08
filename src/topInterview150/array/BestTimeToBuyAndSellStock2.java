package topInterview150.array;

/**
 * Question: 122. Best Time to Buy and Sell Stock II<br>
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.<br>
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).<br>
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).<br>
 * {@code @Link} <a href="https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/">Best Time to Buy and Sell Stock II</a>
 */
public class BestTimeToBuyAndSellStock2 {

    // Time: O(n), Space: O(1)
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += prices[i] - prices[i - 1];
            }
        }
        return maxProfit;
    }

    // Test Case
    public static void main(String[] args) {
        int[] prices = new int[]{7, 1, 5, 3, 6, 4};
        System.out.println(maxProfit(prices)); // 7 (5-1 + 6-3)
    }
}
