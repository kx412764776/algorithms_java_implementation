package topInterview150.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * 54. Spiral Matrix<br>
 * Given an m x n matrix, return all elements of the matrix in spiral order.<br>
 * Example 1:<br>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]<br>
 * Output: [1,2,3,6,9,8,7,4,5]<br>
 * @Param: int[][] matrix
 * @Return: List<Integer> spiralOrder
 * @Link: <a href="https://leetcode.com/problems/spiral-matrix/">Spiral Matrix</a>
 */
public class SpiralMatrix {

    public static List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<>();

        int rows = matrix.length;
        int cols = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = cols - 1;
        int down = rows - 1;

        while (result.size() < rows * cols) {

            // traverse from left -> right
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }

            // traverse downwards
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }

            if (up != down) {
                // traverse from right -> left
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }

            if (left != right) {
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        List<Integer> result = spiralOrder(matrix);
        System.out.println(result); // [1,2,3,6,9,8,7,4,5]

        int[][] matrix2 = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        List<Integer> result2 = spiralOrder(matrix2);
        System.out.println(result2); // [1,2,3,4,8,12,11,10,9,5,6,7]
    }
}
