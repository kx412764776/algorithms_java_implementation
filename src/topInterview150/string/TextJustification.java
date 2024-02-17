package topInterview150.string;

import java.util.ArrayList;
import java.util.List;

/**
 * 68. Text Justification<br>
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.<br>
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line.<br>
 * Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.<br>
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.<br>
 * For the last line of text, it should be left-justified and no extra space is inserted between words.<br>
 * {@code Link} <a href="https://leetcode.com/problems/text-justification/">Text Justification</a><br>
 */
public class TextJustification {

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            List<String> currentLine = getWords(i, words, maxWidth);
            i += currentLine.size();
            res.add(createLine(currentLine,i, words, maxWidth));
        }
        return res;
    }

    private static List<String> getWords(int i, String[] words, int maxWidth) {
        List<String> currentLine = new ArrayList<>();
        int length = 0;

        while (i < words.length && length + words[i].length() <= maxWidth) {
            currentLine.add(words[i]);
            length += words[i].length() + 1;
            i++;
        }
        return currentLine;
    }

    private static String createLine(List<String> line, int i, String[] words, int maxWidth) {

        int baseLength = -1;
        for (String word: line) {
            baseLength += word.length() + 1;
        }

        int extraSpaces = maxWidth - baseLength;

        if (line.size() == 1 || i == words.length) {
            return String.join(" ", line) + " ".repeat(extraSpaces);
        }

        int wordCount = line.size() - 1;
        int spacesPerWord = extraSpaces / wordCount;
        int needsExtraSpace = extraSpaces % wordCount;

        for (int j = 0; j < needsExtraSpace; j++) {
            line.set(j, line.get(j) + " ");
        }

        for (int j = 0; j < wordCount; j++) {
            line.set(j, line.get(j) + " ".repeat(spacesPerWord));
        }

        return String.join(" ",  line);
    }

    public static void main(String[] args) {

        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        System.out.println(fullJustify(words, maxWidth));
    }
}
