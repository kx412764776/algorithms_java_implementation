package other;

public class Hanoi {

    public static void hanoi(int n) {
        if (n > 0) {
            process(n, "left", "right", "mid");
        }
    }

    private static void process(int n, String start, String end, String other) {
        if (n == 1) {
            System.out.println("Move 1 from " + start + " to " + end);
        } else {
            process(n - 1, start, other, end);
            System.out.println("Move " + n + " from " + start + " to " + end);
            process(n - 1, other, end, start);
        }
    }

    public static void main(String[] args) {
        hanoi(4);
    }
}
