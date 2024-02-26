package topInterview150.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 205. Isomorphic Strings<br>
 * Given two strings s and t, determine if they are isomorphic.<br>
 * Two strings s and t are isomorphic if the characters in s can be replaced to get t.<br>
 * All occurrences of a character must be replaced with another character while preserving the order of characters.<br>
 * No two characters may map to the same character, but a character may map to itself.<br>
 * <br>
 * Example 1:<br>
 * Input: s = "egg", t = "add"<br>
 * Output: true<br>
 * <br>
 * @Param: String s, String t
 * @Return: boolean
 * @Link: <a href="https://leetcode.com/problems/isomorphic-strings/">Isomorphic Strings</a>
 */
public class IsomorphicStrings {

    // Use hashmap to store the index of the first occurrence of each character
    // Time complexity: O(n)
    // Space complexity: O(n)
    public static boolean isIsomorphic(String s, String t) {
        return transformString(s).equals(transformString(t));
    }

    public static String transformString(String s) {
        Map<Character, Integer> indexMap = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (!indexMap.containsKey(c)) {
                indexMap.put(c, i);
            }

            builder.append(indexMap.get(c));
            builder.append(" ");
        }
        return builder.toString();
    }

    // Time complexity: O(n)
    // Space complexity: O(1)
    public static boolean isIsomorphic2(String s, String t) {
        int[] sMap = new int[256];
        int[] tMap = new int[256];
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            if (sMap[sChar] != tMap[tChar]) {
                return false;
            }
            sMap[sChar] = i + 1;
            tMap[tChar] = i + 1;
        }
        return true;
    }

    // test
    public static void main(String[] args) {
        String s = "egg", t = "add"; // true
        System.out.println(isIsomorphic(s, t));
        System.out.println(isIsomorphic2(s, t));

        String s2 = "foo", t2 = "bar"; // false
        System.out.println(isIsomorphic(s2, t2));
        System.out.println(isIsomorphic2(s2, t2));

        String s3 = "paper", t3 = "title"; // true
        System.out.println(isIsomorphic(s3, t3));
        System.out.println(isIsomorphic2(s3, t3));
    }
}
