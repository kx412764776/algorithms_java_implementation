package topInterview150.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 13. Roman to Integer<br>
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
 * Transform the given Roman numeral to an integer.<br>
 */
public class RomanToInteger {

    private static final Map<String, Integer> map = new HashMap<>();

    static {
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
    }

    // Time complexity: O(n), Space complexity: O(1)
    public static int romanToInt(String s) {

        String lastSymbol = s.substring(s.length() - 1);
        int lastValue = map.get(lastSymbol);
        int total = lastValue;

        for (int i = s.length() - 2; i >= 0; i--) {
            String currSymbol = s.substring(i, i + 1);
            int currValue = map.get(currSymbol);
            if (currValue < lastValue) {
                total -= currValue;
            } else {
                total += currValue;
            }
            lastValue = currValue;
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("IV"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }
}
