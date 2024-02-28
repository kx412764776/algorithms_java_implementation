package topInterview150.hashmap;

import java.util.*;

/**
 * 49. Group Anagrams<br>
 * Given an array of strings strs, group the anagrams together. You can return the answer in any order.<br>
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.<br>
 * <br>
 * Example 1:<br>
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]<br>
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]<br>
 * <br>
 * {@code Params: String[] strs}<br>
 * {@code Return: List<List<String>>}
 * @Link: <a href="https://leetcode.com/problems/group-anagrams/">Group Anagrams</a>
 */
public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {

        if (strs == null || strs.length == 0) {
            return null;
        }

        if (strs.length == 1) {
            return List.of(List.of(strs[0]));
        }

        Map<String, List<String>> anagrams = new HashMap<>();

        for (String str : strs) {
            char[] s = str.toCharArray();
            Arrays.sort(s);
            String key = String.valueOf(s);
            if (!anagrams.containsKey(key)) {
                anagrams.put(key, new ArrayList<>());
            }
            anagrams.get(key).add(str);
        }
        return new ArrayList<>(anagrams.values());
    }

    public static void main(String[] args) {
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));

        String[] strs2 = {"a"};
        System.out.println(groupAnagrams(strs2));

        String[] strs3 = {"eat","tea","tan","ate","nat","bat", "a"};
        System.out.println(groupAnagrams(strs3));

        String[] strs4 = {""};
        System.out.println(groupAnagrams(strs4));
    }
}
