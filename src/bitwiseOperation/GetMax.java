package bitwiseOperation;

public class GetMax {

    // 1 -> 0; 0 -> 1
    public static int flip(int n) {
        return n ^ 1;
    }

    // if n > 0, return 1; else return 0
    public static int sign(int n) {
        return flip((n >> 31) & 1);
    }

    // risk: if a - b > Integer.MAX_VALUE, it will overflow
    public static int getMax1(int a, int b) {
        int c = a - b;
        int scA = sign(c); // if a - b > 0, scA = 1; else scA = 0
        int scB = flip(scA); // if scA = 1, scB = 0; else scB = 1
        // one of scA and scB is 1, the other is 0
        return a * scA + b * scB;
    }

    public static int getMax2(int a, int b) {
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb; // if a and b have different sign, difSab = 1; else difSab = 0
        int sameSab = flip(difSab); // if a and b have same sign, sameSab = 1; else sameSab = 0
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static void main(String[] args) {
        int a = -16;
        int b = 1;
        System.out.println(getMax1(a, b));
        System.out.println(getMax2(a, b));
        a = 2147483647;
        b = -2147480000;
        System.out.println(getMax1(a, b)); // wrong answer
        System.out.println(getMax2(a, b));
    }
}
