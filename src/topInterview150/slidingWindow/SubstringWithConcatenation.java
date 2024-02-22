package topInterview150.slidingWindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. Substring with Concatenation of All Words<br>
 * You are given a string s and an array of strings words. All the strings of words are of the same length.<br>
 * A concatenated substring in s is a substring that contains all the strings of any permutation of words concatenated.<br>
 * <p>
 *     For example, if words = ["ab", "cd", "ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated strings.<br>
 *     "acdbef" is not a concatenated string, because it is not the concatenation of any permutation of words.<br>
 * </p>
 * @Param: String s, String[] words<br>
 * @Return: the starting indices of all the concatenated substrings in s. The answer can be in any order.<br>
 * @Link: <a href="https://leetcode.com/problems/substring-with-concatenation-of-all-words/">Substring with Concatenation of All Words</a><br>
 */
public class SubstringWithConcatenation {

    public List<Integer> findSubstring(String s, String[] words) {

        int wordsLength = words.length;
        int eachWordLength = words[0].length();

        if (s.length() < wordsLength * eachWordLength) {
            return new ArrayList<>();
        }

        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        int left, right;
        Map<String, Integer> wordsFound = new HashMap<>();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < eachWordLength; i++) {
            left = i;
            right = i;

            // sliding window
            while (right + eachWordLength <= s.length()) {

                String word = s.substring(right, right + eachWordLength);
                right += eachWordLength;

                // if the word is in the wordCount, add it to the wordsFound
                if (wordCount.containsKey(word)) {
                    wordsFound.put(word, wordsFound.getOrDefault(word, 0) + 1);

                    // if the word is found more than the wordCount, move the left pointer
                    while (wordsFound.get(word) > wordCount.get(word)) {
                        String leftWord = s.substring(left, left + eachWordLength);
                        wordsFound.put(leftWord, wordsFound.get(leftWord) - 1);
                        left += eachWordLength;
                    }
                    if (right - left == wordsLength * eachWordLength) {
                        res.add(left);
                    }
                } else {
                    wordsFound.clear();
                    left = right;
                }
            }
            wordsFound.clear();
        }

        return res;
    }

    // test case
    public static void main(String[] args) {
        SubstringWithConcatenation solution = new SubstringWithConcatenation();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        System.out.println(solution.findSubstring(s, words));

        s = "wordgoodgoodgoodbestword";
        words = new String[]{"word", "good", "best", "word"};
        System.out.println(solution.findSubstring(s, words));

        s = "lingmindraboofooowingdingbarrwingmonkeypoundcake";
        words = new String[]{"fooo", "barr", "wing", "ding", "wing"};
        System.out.println(solution.findSubstring(s, words)); // [13]
    }
}
