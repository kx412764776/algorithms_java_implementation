package topInterview150.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * 383. Ransom Note<br>
 * Given two stings ransomNote and magazine, return true if ransomNote can be constructed from magazine and false otherwise.<br>
 * Each letter in magazine can only be used once in ransomNote.<br>
 * <br>
 * Example 1:<br>
 * Input: ransomNote = "a", magazine = "b"<br>
 * Output: false<br>
 * <br>
 * @Param: String ransomNote, String magazine
 * @Return: boolean
 * @Link: <a href="https://leetcode.com/problems/ransom-note/">Ransom Note</a>
 */
public class RansomNote {

    // One HashMap
    // Time complexity: O(m + n)
    // Space complexity: O(m)
    public static boolean canConstruct(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) return false;

        Map<Character, Integer> count = new HashMap<>();
        for (char c : magazine.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            int countInMagazine = count.getOrDefault(c, 0);
            if (countInMagazine == 0) {
                return false;
            }
            count.put(c, countInMagazine - 1);
        }
        return true;
    }

    // Time complexity: O(m + n)
    // Space complexity: O(1)
    public static boolean canConstruct2(String ransomNote, String magazine) {
        if (magazine.length() < ransomNote.length()) return false;

        int[] count = new int[26];
        for (char c : magazine.toCharArray()) {
            count[c - 'a']++;
        }
        for (char c : ransomNote.toCharArray()) {
            count[c - 'a']--;
            if (count[c - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

    // test
    public static void main(String[] args) {
        String ransomNote = "a", magazine = "b"; // false
        System.out.println(canConstruct(ransomNote, magazine));
        System.out.println(canConstruct2(ransomNote, magazine));

        String ransomNote2 = "aa", magazine2 = "ab"; // false
        System.out.println(canConstruct(ransomNote2, magazine2));
        System.out.println(canConstruct2(ransomNote2, magazine2));

        String ransomNote3 = "aa", magazine3 = "aab"; // true
        System.out.println(canConstruct(ransomNote3, magazine3));
        System.out.println(canConstruct2(ransomNote3, magazine3));
    }
}
