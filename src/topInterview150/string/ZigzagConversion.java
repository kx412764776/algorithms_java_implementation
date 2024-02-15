package topInterview150.string;

import java.util.Arrays;

/**
 * 6. ZigZag Conversion<br>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:<br>
 * (you may want to display this pattern in a fixed font for better legibility)<br>
 * P   A   H   N<br>
 * A P L S I I G<br>
 * Y   I   R<br>
 * And then read line by line: "PAHNAPLSIIGYIR"<br>
 * {@code Param} String: a string
 * {@code Param} int: the number of rows
 * {@code Return} String: the string in a zigzag pattern on a given number of rows
 * {@code Link} <a href="https://leetcode.com/problems/zigzag-conversion/">ZigZag Conversion</a><br>
 */
public class ZigzagConversion {

    public static String convert1(String s, int numRows) {
        if (numRows == 1) return s;

        int n = s.length();
        int sections = (int) Math.ceil(n / (2 * numRows - 2.0));
        int numCols = sections * (numRows - 1);

        char[][] matrix = new char[numRows][numCols];
        for(char[] row : matrix) {
            Arrays.fill(row, ' ');
        }

        int currRow = 0, currCol = 0;
        int currStringIndex = 0;

        while (currStringIndex < n) {
            while (currRow < numRows && currStringIndex < n) {
                matrix[currRow][currCol] = s.charAt(currStringIndex);
                currRow++;
                currStringIndex++;
            }

            currRow -= 2;
            currCol++;

            while (currRow > 0 && currCol < numCols && currStringIndex < n) {
                matrix[currRow][currCol] = s.charAt(currStringIndex);
                currRow--;
                currCol++;
                currStringIndex++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (char[] row : matrix) {
            for (char character : row) {
                if (character != ' ') {
                    res.append(character);
                }
            }
        }
        return res.toString();
    }

    public static String convert2(String s, int numRows) {

        if (numRows == 1) {
            return s;
        }

        StringBuilder res = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);

        for (int currRow = 0; currRow < numRows; currRow++) {
            int index = currRow;

            while (index < n) {
                res.append(s.charAt(index));

                // If currRow is not first/last row, add one more char of curr section
                if (currRow != 0 && currRow != numRows - 1) {
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;

                    if (secondIndex < n) {
                        res.append(s.charAt(secondIndex));
                    }
                }

                // Jump to same row's first char of new section
                index += charsInSection;
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {

            String s = "PAYPALISHIRING";
            int numRows = 3;
            System.out.println(convert1(s, numRows)); // PAHNAPLSIIGYIR
            System.out.println(convert2(s, numRows));

            String s2 = "PAYPALISHIRING";
            int numRows2 = 4;
            System.out.println(convert1(s2, numRows2)); // PINALSIGYAHRPI
            System.out.println(convert2(s2, numRows2));
    }
}
