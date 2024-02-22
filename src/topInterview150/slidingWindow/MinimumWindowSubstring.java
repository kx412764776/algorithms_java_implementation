package topInterview150.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 76. Minimum Window Substring<br>
 * Given two strings s and t, return the minimum window in s which will contain all the characters in t.<br>
 * If there is no such window in s that covers all characters in t, return the empty string "".<br>
 * @Param: String s, String t<br>
 * @Return: String - the minimum window in s which will contain all the characters in t<br>
 * @Link: <a href="https://leetcode.com/problems/minimum-window-substring/">Minimum Window Substring</a><br>
 */
public class MinimumWindowSubstring {

    // Time Complexity: O(n)
    // Space Complexity: O(m), m is the size of the charset
    public static String minWindow(String s, String t) {

        if (s.length() < t.length() || s.isEmpty() || t.isEmpty()) {
            return "";
        }

        if (s.equals(t)) {
            return s;
        }

        Map<Character, Integer> count = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            count.put(t.charAt(i), count.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0;
        int right = 0;
        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int counter = count.size();

        while (right < s.length()) {

            char r = s.charAt(right);
            // move right pointer until all the char in t are found
            if (count.containsKey(r)) {
                count.put(r, count.get(r) - 1);
                // if the char in t is found, counter--
                if (count.get(r) == 0) {
                    counter--;
                }
            }
            right++;

            // after all the char int are found, move left pointer
            while (counter == 0) {
                char l = s.charAt(left);

                if (count.containsKey(l)) {
                    count.put(l, count.get(l) + 1);
                    // when the char in t is found, counter++
                    if (count.get(l) > 0) {
                        counter++;
                    }
                }

                // update the min window
                if (right - left < min) {
                    min = right - left;
                    start = left;
                    end = right;
                }
                left++;
            }
        }

        return min == Integer.MAX_VALUE ? "" : s.substring(start, end);

    }

    // test case
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t)); // BANC

        s = "a";
        t = "a";
        System.out.println(minWindow(s, t)); // a

        s = "a";
        t = "aa";
        System.out.println(minWindow(s, t)); // ""
    }
}
