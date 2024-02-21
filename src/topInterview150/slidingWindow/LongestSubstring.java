package topInterview150.slidingWindow;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. Longest Substring Without Repeating Characters<br>
 * Given a string s, find the length of the longest substring without repeating characters.<br>
 * {@code Link}: <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">Longest Substring Without Repeating Characters</a><br>
 */
public class LongestSubstring {

    // sliding window
    // Time Complexity: O(n)
    // Space Complexity: O(min(m, n)), m is the size of the charset, n is the length of the string
    public static int lengthOfLongestSubstring1(String s) {

        Map<Character, Integer> chars = new HashMap<>();

        int left = 0;
        int right = 0;
        int max = 0;

        while (right < s.length()) {
            char r = s.charAt(right);
            chars.put(r, chars.getOrDefault(r, 0) + 1);

            while (chars.get(r) > 1) {
                char l = s.charAt(left);
                chars.put(l, chars.get(l) - 1);
                left++;
            }

            max = Math.max(max, right - left + 1);

            right++;
        }

        return max;
    }

    // sliding window optimized
    public static int lengthOfLongestSubstring2(String s) {

        Map<Character, Integer> map = new HashMap<>(); // current index of character

        int max = 0;

        for (int right = 0, left = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(map.get(s.charAt(right)), left);
            }

            max = Math.max(max, right - left + 1);
            map.put(s.charAt(right), right + 1);
        }

        return max;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring1(s)); // 3
        System.out.println(lengthOfLongestSubstring2(s));

        s = "bbbbb";
        System.out.println(lengthOfLongestSubstring1(s)); // 1
        System.out.println(lengthOfLongestSubstring2(s));
    }
}
