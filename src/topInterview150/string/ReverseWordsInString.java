package topInterview150.string;

import java.util.*;

/**
 * 151. Reverse Words in a String<br>
 * Given an input string s, reverse the string word by word.<br>
 * {@code Return} String: the words in reverse order concatenated by a single space<br>
 * {@code Note} s may contain leading or trailing spaces or multiple spaces between two words.
 * The returned string should only have a single space separating the words<br>
 * {@code Link} <a href="https://leetcode.com/problems/reverse-words-in-a-string/">Reverse Words in a String</a><br>
 */
public class ReverseWordsInString {

    // Time complexity: O(n), where n is the length of the string
    // Space complexity: O(n)
    public static String reverseWords1(String s) {

        s = s.trim();

        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }

    // Deque
    // Time complexity: O(n)
    // Space complexity: O(n)
    public static String reverseWords2(String s) {
        int left = 0;
        int right = s.length() - 1;

        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }

        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        Deque<String> deque = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();

        // push word by word in front of deque
        while (left <= right) {
            char c = s.charAt(left);

            if ((!word.isEmpty()) && (c == ' ')) {
                deque.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            left++;
        }
        deque.offerFirst(word.toString());

        return String.join(" ", deque);
    }

    public static void main(String[] args) {

        String s = "  the  sky is blue  ";
        System.out.println(reverseWords1(s));
        System.out.println(reverseWords2(s));

        String s2 = "  hello world  ";
        System.out.println(reverseWords1(s2));
        System.out.println(reverseWords2(s2));
    }
}
