package topInterview150.hashmap;


import java.util.Map;
import java.util.HashMap;
import java.util.Objects;

/**
 * 290. Word Pattern<br>
 * Given a pattern and a string s, find if s follows the same pattern.<br>
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.<br>
 * <br>
 * Example 1:<br>
 * Input: pattern = "abba", s = "dog cat cat dog"<br>
 * Output: true<br>
 * <br>
 * @Param: String pattern, String s
 * @Return: boolean
 */
public class WordPattern {

    // Time complexity: O(n)
    // Space complexity: O(n)
    public static boolean wordPattern(String pattern, String s) {
        Map<Character, String> charToWord = new HashMap<>();
        Map<String, Character> wordToChar = new HashMap<>();
        String[] words = s.split(" ");

        if (words.length != pattern.length()) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String w = words[i];

            if (charToWord.containsKey(c) && !charToWord.get(c).equals(w)) {
                return false;
            }
            if (wordToChar.containsKey(w) && !wordToChar.get(w).equals(c)) {
                return false;
            }
            charToWord.put(c, w);
            wordToChar.put(w, c);
        }
        return true;
    }

    // One HashMap
    // Time complexity: O(n)
    // Space complexity: O(n)
    public static boolean wordPattern2(String pattern, String s) {
        Map<Object, Integer> index = new HashMap<>();
        String[] words = s.split(" ");
        if (pattern.length() != words.length) {
            return false;
        }
        for (int i = 0; i < words.length; i++) {
            if (!Objects.equals(index.put(pattern.charAt(i), i), index.put(words[i], i))) {
                return false;
            }
        }
        return true;
    }

    // test
    public static void main(String[] args) {
        String pattern = "abba";
        String s = "dog cat cat dog";
        System.out.println(wordPattern(pattern, s)); // true
        System.out.println(wordPattern2(pattern, s)); // true

        String pattern2 = "abba";
        String s2 = "dog cat cat fish";
        System.out.println(wordPattern(pattern2, s2)); // false
        System.out.println(wordPattern2(pattern2, s2)); // false

        String pattern3 = "aaaa";
        String s3 = "dog cat cat dog";
        System.out.println(wordPattern(pattern3, s3)); // false
        System.out.println(wordPattern2(pattern3, s3)); // false
    }
}
