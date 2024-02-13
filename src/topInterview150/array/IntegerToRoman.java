package topInterview150.array;

/**
 * 12. Integer to Roman<br>
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.<br>
 * <table>
 *     <tr>
 *         <td>Symbol</td>
 *         <td>Value</td>
 *     </tr>
 *     <tr>
 *         <td>I</td>
 *         <td>1</td>
 *     </tr>
 *     <tr>
 *         <td>V</td>
 *         <td>5</td>
 *     </tr>
 *     <tr>
 *         <td>X</td>
 *         <td>10</td>
 *     </tr>
 *     <tr>
 *         <td>L</td>
 *         <td>50</td>
 *     </tr>
 *     <tr>
 *         <td>C</td>
 *         <td>100</td>
 *     </tr>
 *     <tr>
 *         <td>D</td>
 *         <td>500</td>
 *     </tr>
 *     <tr>
 *         <td>M</td>
 *         <td>1000</td>
 *     </tr>
 * </table>
 * Required:<br>
 * Transform the given integer to a roman numeral.<br>
 */
public class IntegerToRoman {

    // Greedy
    // Time complexity: O(1), Space complexity: O(1)
    public static String intToRoman(int num) {
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < values.length && num > 0; i++) {
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }
}
