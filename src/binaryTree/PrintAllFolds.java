package binaryTree;

/**
 * print all folds of a paper (n times)
 */
public class PrintAllFolds {

    public static void printAllFolds(int n) {
        printProcess(1, n, true);
    }

    public static void printProcess(int i, int n, boolean down) {
        if (i > n) return;
        printProcess(i + 1, n, true);
        System.out.println(down ? "down" : "up");
        printProcess(i + 1, n, false);
    }

    public static void main(String[] args) {
        int n = 3;
        printAllFolds(n);
    }
}
