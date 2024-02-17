package topInterview150.twoPointers;

/**
 * 125. Valid Palindrome<br>
 * {@code Param} String: a string s, consisting of printable ASCII characters<br>
 * {@code Return} boolean: true if s is a palindrome, and false otherwise<br>
 * <p>
 *     A palindrome is a string that reads the same backward as forward<br>
 *     {@code Note} For the purpose of this problem, we define s as an empty string<br>
 *     {@code Link} <a href="https://leetcode.com/problems/valid-palindrome/">Valid Palindrome</a><br>
 * </p>
 */
public class ValidPalindrome {

    // Time complexity: O(n)
    // Space complexity: O(1)
    public static boolean isPalindrome(String s) {

        for (int left = 0, right = s.length() - 1; left < right; left++, right--) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isPalindrome2(String s) {
        StringBuilder builder = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                builder.append(Character.toLowerCase(ch));
            }
        }

        String originString = builder.toString();
        String reversedString = builder.reverse().toString();

        return originString.equals(reversedString);
    }

    public static boolean isPalindrome3(String s) {

        StringBuilder builder = new StringBuilder();
        s.chars()
                .filter(Character::isLetterOrDigit)
                .mapToObj(c -> Character.toLowerCase((char)c))
                .forEach(builder::append);

        return builder.toString().contentEquals(builder.reverse());
    }

    // Test case
    public static void main(String[] args) {

        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome2(s));
        System.out.println(isPalindrome3(s));

        String s2 = " ";
        System.out.println(isPalindrome(s2));
        System.out.println(isPalindrome2(s2));
        System.out.println(isPalindrome3(s2));
    }
}
