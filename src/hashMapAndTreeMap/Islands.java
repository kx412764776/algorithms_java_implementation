package hashMapAndTreeMap;

import unionFind.*;
import java.util.ArrayList;
import java.util.List;

public class Islands {

    public static int countIslands1(int[][] matrix) {
        if (matrix == null || matrix[0] == null){
            return 0;
        }

        int row = matrix.length;
        int column = matrix[0].length;
        int res = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == 1) {
                    res++;
                    infect(matrix, i, j, row, column);
                }
            }
        }
        return res;
    }

    private static void infect(int[][] matrix, int i, int j, int row, int column) {

        if (i < 0 || i >= row || j < 0 || j >= column || matrix[i][j] != 1) {
            return;
        }

        // 1. set the first infected island to 2
        // 2. infect the adjacent islands
        matrix[i][j] = 2;
        infect(matrix, i + 1, j, row, column);
        infect(matrix, i - 1, j, row, column);
        infect(matrix, i, j + 1, row, column);
        infect(matrix, i, j - 1, row, column);
    }

    public static int countIslands2(int[][] matrix) {
        List<String> list = new ArrayList<>();
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[row][column] == 1) {
                    String position = String.valueOf(row) + "_" + String.valueOf(column);
                    list.add(position);
                }
            }
        }

        UnionFind.UnionFindSet<String> unionSet = new UnionFind.UnionFindSet<>(list);
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[0].length; column++) {
                if (matrix[row][column] == 1) {
                    String position = String.valueOf(row) + "_" + String.valueOf(column);
                    if (row - 1 >= 0 && matrix[row - 1][column] == 1) {
                        String up = String.valueOf(row - 1) + "_" + String.valueOf(column);
                        unionSet.union(up, position);
                    }
                    if (column - 1 >= 0 && matrix[row][column - 1] == 1) {
                        String left = String.valueOf(row) + "_" + String.valueOf(column - 1);
                        unionSet.union(left, position);
                    }

                }
            }
        }
        return unionSet.getSetNum();
    }

    public static void main(String[] args) {
        int[][] m1 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        for (int[] row : m1) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println(countIslands1(m1));

        int[][] m1Other = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 0, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        for (int[] row : m1Other) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println(countIslands2(m1Other));

        int[][] m2 = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        for (int[] row : m2) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println(countIslands1(m2));

        int[][] m2Other = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 1, 1, 1, 1, 1, 1, 1, 0 }, { 0, 1, 1, 1, 0, 0, 0, 1, 0 },
                { 0, 1, 1, 0, 0, 0, 1, 1, 0 }, { 0, 0, 0, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 1, 1, 1, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
        for (int[] row : m2Other) {
            for (int i : row) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println(countIslands2(m2Other));

    }
}
