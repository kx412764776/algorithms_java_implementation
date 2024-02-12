package topInterview150.array;

import java.util.Arrays;

/**
 * 135. Candy<br>
 * There are n children standing in a line. Each child is assigned a rating value given in the integer array ratings.<br>
 * You are giving candies to these children subjected to the following requirements:<br>
 * Each child must have at least one candy.<br>
 * Children with a higher rating get more candies than their neighbors.<br>
 * {@code Return} the minimum number of candies you need to have to distribute the candies to the children.<br>
 * {@code Link} <a href="https://leetcode.com/problems/candy/">Candy</a><br>
 */
public class Candy {

    // Time complexity: O(n), Space complexity: O(1)
    public static int candy1(int[] ratings) {
        if (ratings.length <= 1) return ratings.length;

        int candies = 0; // total candies
        int up = 0; // increasing slope
        int down = 0; // decreasing slope
        int oldSlope = 0;

        // count the number of increasing and decreasing slopes
        for (int i = 1; i < ratings.length; i++) {
            int newSlope = Integer.compare(ratings[i], ratings[i - 1]); // 1: increasing, 0: flat, -1: decreasing

            if ((oldSlope > 0 && newSlope == 0) || (oldSlope < 0 && newSlope >= 0)) { // if the slope changes
                candies += count(up) + count(down) + Math.max(up, down); // add the number of candies for the previous slope
                up = 0;
                down = 0;
            }

            if (newSlope > 0) up++;
            if (newSlope < 0) down++;
            if (newSlope == 0) candies++;

            oldSlope = newSlope;
        }

        candies += count(up) + count(down) + Math.max(up, down) + 1;

        return candies;
    }

    private static int count(int n) {
        return n * (n + 1) / 2;
    }

    // Time complexity: O(n), Space complexity: O(n)
    public static int candy2(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        int res = candies[ratings.length - 1];

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
            res += candies[i];
        }

        return res;
    }

    public static void main(String[] args) {
        int[] ratings = {1,0,2};
        System.out.println(candy1(ratings));
        System.out.println(candy2(ratings));

        int[] ratings2 = {1,2,2};
        System.out.println(candy1(ratings2));
        System.out.println(candy2(ratings2));
    }
}
