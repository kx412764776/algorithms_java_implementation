package hashMapAndTreeMap;

public class Islands {

    public static int countIslands(int[][] matrix) {
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

    public static class Element<V> {
        public V value;

        public Element(V value) {
            this.value = value;
        }

    }
}
