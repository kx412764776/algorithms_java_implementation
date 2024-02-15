package topInterview150.string;

/**
 * 14. Longest Common Prefix<br>
 * {@code Param} String[]: an array of strings<br>
 * {@code Return} String: the longest common prefix string amongst an array of strings<br>
 * <p>
 *     Example 1:<br>
 *     Input: strs = ["flower","flow","flight"]<br>
 *     Output: "fl"<br>
 * </p>
 * {@code Link} <a href="https://leetcode.com/problems/longest-common-prefix/">Longest Common Prefix</a><br>
 */
public class LongestCommonPrefix {

    // Time complexity: O(n * m), where n is the length of the array and m is the length of the shortest string
    // Space complexity: O(1)
    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];

        for (int i = 1; i < strs.length; i++) {

            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }

        return prefix;
    }

    public static void main(String[] args) {

        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));
    }


}
