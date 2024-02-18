package topInterview150.twoPointers;

/**
 * 392. Is Subsequence<br>
 * {@code Param} String: s and t, both consisting of lowercase English letters<br>
 * {@code Return} boolean: true if s is a subsequence of t, and false otherwise<br>
 * <p>
 *     A subsequence of a string is a new string that is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters.<br>
 *     (i.e., "ace" is a subsequence of "abcde" while "aec" is not).<br>
 *     {@code Link} <a href="https://leetcode.com/problems/is-subsequence/">Is Subsequence</a><br>
 * </p>
 */
public class IsSubsequence {

    // Time complexity: O(n), where n is the length of the string t
    // Space complexity: O(1)
    public static boolean isSubsequence(String s, String t) {
        int i = 0, j = 0;

        while (i < s.length() && j < t.length()) {

            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == s.length();
    }

    public static void main(String[] args) {
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));

        String s2 = "axc";
        String t2 = "ahbgdc";
        System.out.println(isSubsequence(s2, t2));
    }
}
