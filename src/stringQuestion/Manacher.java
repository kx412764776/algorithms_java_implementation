package stringQuestion;

public class Manacher {

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxPalindromeLength(String s) {
        if (s == null || s.length() == 0) return 0;

        char[] str = manacherString(s); // 1221 -> #1#2#2#1#
        int[] palindromeRadiusArr = new int[str.length]; // stores the radius of each palindrome
        int center = -1; // the center of the palindrome
        int right = -1; // The right border of the palindrome is moved one position to the right.
        int max = Integer.MIN_VALUE;

        for (int i = 0; i != str.length; i++) { // get the radius of palindrome at each position
            // 1. if i exceeds the right border, the radius of palindrome at i is 1
            // 2. if i is within the right border, the radius of palindrome at i is the minimum of
            // the radius of palindrome at i's mirror position and the distance between i and the right border
            palindromeRadiusArr[i] = right > i ?
                    Math.min(palindromeRadiusArr[2 * center - i], right - i) : 1;
            // expand the radius of palindrome at i
            while (i + palindromeRadiusArr[i] < str.length && i - palindromeRadiusArr[i] > -1) {
                // if the character at i + radius(palindrome) is the same as the character at i - radius,
                // the radius of palindrome at i is increased by 1
                // otherwise, break the loop
                if (str[i + palindromeRadiusArr[i]] == str[i - palindromeRadiusArr[i]]) {
                    palindromeRadiusArr[i]++;
                } else {
                    break;
                }
            }
            // if i + radius(palindrome) exceeds the right border, update the right border and the center
            if (i + palindromeRadiusArr[i] > right) {
                right = i + palindromeRadiusArr[i];
                center = i;
                max = Math.max(max, palindromeRadiusArr[i]);
            }
        }
        return max - 1;
    }

    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
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
        int testTimes = 5000000;
        System.out.println("Test begin: ");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (maxPalindromeLength(str) != right(str)) {
                System.out.println("Test failed!");
            }
        }
        System.out.println("Test success!");
    }
}
