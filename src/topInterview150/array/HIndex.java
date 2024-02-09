package topInterview150.array;

import java.util.Arrays;

/**
 * Question: 274. H-Index<br>
 * A scientist has an index h if h of their n papers have at least h citations each.<br>
 * Given an array of integers citations where citations[i] is the number of citations a researcher received for their ith paper.<br>
 * {@code @Return} h-index<br>
 * {@code @Link} <a href="https://leetcode.com/problems/h-index/">H-Index</a>
 */
public class HIndex {

    // Sorting
    // Time: O(nlogn), Space: O(1)
    public static int hIndex1(int[] citations) {
        Arrays.sort(citations);

        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }

    // Counting
    // Time: O(n), Space: O(n)
    public static int hIndex2(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];

        for (int citation : citations) {
            papers[Math.min(n, citation)]++;
        }

        int k = n;
        for (int i = papers[n]; i < k; i += papers[k]) {
            k--;
        }
        return k;
    }

    // Test Case
    public static void main(String[] args) {
        int[] citations = new int[]{3, 0, 6, 1, 5};
        System.out.println(hIndex1(citations));
        System.out.println(hIndex2(citations));

        int[] citations2 = new int[]{1, 3, 1};
        System.out.println(hIndex1(citations2));
        System.out.println(hIndex2(citations2));
    }
}
