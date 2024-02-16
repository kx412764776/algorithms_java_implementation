package topInterview150.string;

/**
 * 28. Find Index of First Occurrence in String<br>
 * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack.<br>
 * If needle is not part of haystack, return -1.<br>
 * {@code Link} <a href="https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/">Find the Index of the First Occurrence in a String</a><br>
 */
public class FindIndexOfFirstOccurInString {

    // Time complexity: O(n * m), where n is the length of the haystack and m is the length of the needle
    // Space complexity: O(1)
    public static int strStr(String haystack, String needle) {

        int m = needle.length();
        int n = haystack.length();

        if (m == 0) {
            return 0;
        }

        if (m > n) {
            return -1;
        }

        for (int window = 0; window <= n - m; window++) {
            for (int i = 0; i < m; i++) {
                if (needle.charAt(i) != haystack.charAt(window + i)) {
                    break;
                }
                if (i == m - 1) {
                    return window;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {

            String haystack = "hello";
            String needle = "ll";
            System.out.println(strStr(haystack, needle));

            String haystack2 = "aaaaa";
            String needle2 = "bba";
            System.out.println(strStr(haystack2, needle2));
    }
}
