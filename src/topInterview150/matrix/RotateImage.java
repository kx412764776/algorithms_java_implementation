package topInterview150.matrix;
/**
 * 48. Rotate Image<br>
 * Given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).<br>
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.<br>
 * <br>
 * Example 1:<br>
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]<br>
 * Output: [[7,4,1],[8,5,2],[9,6,3]]<br>
 * @Param: int[][] matrix
 * @Return: void
 * @Link: <a href="https://leetcode.com/problems/rotate-image/">Rotate Image</a>
 */
public class RotateImage {

    public static void rotate(int[][] matrix) {

        int n = matrix.length;

        for (int i = 0; i < (n + 1) / 2; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - j - 1];
                matrix[n - 1 - i][n - j - 1] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }
    }

    public static void rotate2(int[][] matrix) {

        int n = matrix.length;

        // transpose the matrix
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = temp;
            }
        }

        // reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

    // test
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}; // [[7,4,1],[8,5,2],[9,6,3]]
        rotate(matrix);
        for (int[] row : matrix) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        int[][] matrix2 = {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}}; // [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
        rotate2(matrix2);
        for (int[] row : matrix2) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
