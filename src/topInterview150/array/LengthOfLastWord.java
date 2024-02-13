package topInterview150.array;

/**
 * 58. Length of Last Word
 * {@code Param} String: a string consist of upper/lower-case alphabets and empty space characters
 * {@code Return} int: the length of the last word in the string
 */
public class LengthOfLastWord {

    public static int lengthOfLastWord1(String s) {

        int n = s.length();
        int res = 0;

        while (n > 0) {
            n--;

            if (s.charAt(n) != ' ') {
                res++;
            } else if (res > 0){
                return res;
            }
        }
        return res;
    }

    public static int lengthOfLastWord2(String s) {

        s = s.trim();
        return s.length() - s.lastIndexOf(' ') - 1;

    }

    public static void main(String[] args) {

        String s = "Hello World";
        System.out.println(lengthOfLastWord1(s));
        System.out.println(lengthOfLastWord2(s));

        String s2 = "Hello moon ";
        System.out.println(lengthOfLastWord1(s2));
        System.out.println(lengthOfLastWord2(s2));
    }
}
