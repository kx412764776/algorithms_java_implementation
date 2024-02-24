package topInterview150.matrix;

/**
 * 289. Game of Life<br>
 * According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."<br>
 * The board is made up of an m x n grid of cells, where each cell has an initial state: live (represented by a 1) or dead (represented by a 0).<br>
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):<br>
 * 1. Any live cell with fewer than two live neighbors dies as if caused by under-population.<br>
 * 2. Any live cell with two or three live neighbors lives on to the next generation.<br>
 * 3. Any live cell with more than three live neighbors dies, as if by over-population.<br>
 * 4. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.<br>
 * The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.<br>
 * Given the current state of the m x n grid board, return the next state.<br>
 * <br>
 * Example 1:<br>
 * Input: board = [[0,1,0],[0,0,1],[1,1,1],[0,0,0]]<br>
 * Output: [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]<br>
 * <br>
 * @Param: int[][] board
 * @Link: <a href="https://leetcode.com/problems/game-of-life/">Game of Life</a>
 */
public class GameOfLife {

    public static void gameOfLife(int[][] board) {

        int[] neighbors = {0, 1, -1};

        int rows = board.length;
        int cols = board[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {

                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {

                            int r = row + neighbors[i];
                            int c = col + neighbors[j];

                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && (Math.abs(board[r][c]) == 1)) {
                                liveNeighbors++;
                            }
                        }
                    }
                }

                if ((board[row][col] == 1) && (liveNeighbors < 2 || liveNeighbors > 3)) {
                    board[row][col] = -1;
                }

                if (board[row][col] == 0 && liveNeighbors == 3) {
                    board[row][col] = 2;
                }
            }
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (board[row][col] > 0) {
                    board[row][col] = 1;
                } else {
                    board[row][col] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {0, 0, 1}, {1, 1, 1}, {0, 0, 0}}; // [[0,0,0],[1,0,1],[0,1,1],[0,1,0]]
        gameOfLife(board);
        for (int[] row : board) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }

        System.out.println("=====================================");

        int[][] board2 = {{1, 1}, {1, 0}}; // [[1,1],[1,1]]
        gameOfLife(board2);
        for (int[] row : board2) {
            for (int col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }
}
