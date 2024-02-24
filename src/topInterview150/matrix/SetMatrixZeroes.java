package topInterview150.matrix;

import java.util.HashSet;
import java.util.Set;

/**
 * 73. Set Matrix Zeroes<br>
 * Given an m x n matrix. If an element is 0, set its entire row and column to 0.<br>
 * Must be done in place.<br>
 * <br>
 * Example 1:<br>
 * Input: matrix = [[1,1,1],[1,0,1],[1,1,1]]<br>
 * Output: [[1,0,1],[0,0,0],[1,0,1]]<br>
 * <br>
 * @Param: int[][] matrix
 * @Link: <a href="https://leetcode.com/problems/set-matrix-zeroes/">Set Matrix Zeroes</a>
 */
public class SetMatrixZeroes {

    // Using extra space
    // Time complexity: O(m * n)
    // Space complexity: O(m + n)
    public static void setZeroes(int[][] matrix) {

        int row = matrix.length;
        int col = matrix[0].length;
        Set<Integer> rows = new HashSet<>();
        Set<Integer> cols = new HashSet<>();

        // mark the rows and columns that are to be made zero
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }

        // set the entire row to zero
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    // test
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}; // [[1,0,1],[0,0,0],[1,0,1]]
        setZeroes(matrix);
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        int[][] matrix2 = {{0, 1, 2, 0}, {3, 4, 5, 2}, {1, 3, 1, 5}}; // [[0,0,0,0],[0,4,5,0],[0,3,1,0]]
        setZeroes(matrix2);
        for (int[] row : matrix2) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
