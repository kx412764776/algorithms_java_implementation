package topInterview150.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 242. Valid Anagram<br>
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.<br>
 * An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.<br>
 * <br>
 * Example 1:<br>
 * Input: s = "anagram", t = "nagaram"<br>
 * Output: true<br>
 * <br>
 * @Param: String s, String t
 * @Return: boolean
 */
public class ValidAnagram {

    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        int[] table = new int[26];

        for (int i = 0; i < s.length(); i++) {
            table[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            table[t.charAt(i) - 'a']--;
            if (table[t.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            wordMap.put(s.charAt(i), wordMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < t.length(); i++) {
            wordMap.put(t.charAt(i), wordMap.getOrDefault(t.charAt(i), 0) - 1);
            if (wordMap.get(t.charAt(i)) < 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram2(s, t));

        String s2 = "rat";
        String t2 = "car";
        System.out.println(isAnagram(s2, t2));
        System.out.println(isAnagram2(s2, t2));
    }
}
