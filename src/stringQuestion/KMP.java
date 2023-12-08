package stringQuestion;

/**
 * KMP algorithm
 * find the first index of str2 in str1
 * if not found, return -1
 * if found, return the first index
 */
public class KMP {

    public static int getIndexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() < 1 || str2.length() < 1) {
            return -1;
        }
        char[] strArr1 = str1.toCharArray();
        char[] strArr2 = str2.toCharArray();
        int i1 = 0;
        int i2 = 0;
        int[] next = getNextArray(strArr2); // O(M)
        // O(N)
        while (i1 < strArr1.length && i2 < strArr2.length) {
            if (strArr1[i1] == strArr2[i2]) {
                i1++;
                i2++;
            } else if (i2 == 0) { // next[i2] == -1
                i1++;
            } else {
                i2 = next[i2];
            }
        }
        return i2 == strArr2.length ? i1 - i2 : -1;
    }

    private static int[] getNextArray(char[] strArr) {
        if (strArr.length == 1) {
            return new int[] {-1};
        }
        int[] next = new int[strArr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < next.length) {
            if (strArr[i - 1] == strArr[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("Test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Test failed");
            }
        }
        System.out.println("Test success");
    }
}
