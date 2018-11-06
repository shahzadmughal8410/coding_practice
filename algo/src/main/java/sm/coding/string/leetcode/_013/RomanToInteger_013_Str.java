/**
 * 
 */
package sm.coding.string.leetcode._013;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class RomanToInteger_013_Str {

	/**
	 * Given a roman numeral, convert it to an integer.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 * 
	 * https://leetcode.com/problems/roman-to-integer/description/
	 * https://www.geeksforgeeks.org/converting-roman-numerals-decimal-lying-1-3999/
	 * 
	 * SYMBOL VALUE I 1 IV 4 V 5 IX 9 X 10 XL 40 L 50 XC 90 C 100 CD 400 D 500 CM
	 * 900 M 1000
	 * 
	 * SYMBOL VALUE I 1 V 5 X 10 L 50 C 100 D 500 M 1000
	 * 
	 * @param args
	 */
	public static int romanToDecimal(String roman) {
		if (null == roman || roman.length() == 0) {
			return 0;
		}

		Map<Character, Integer> m = new HashMap<>();
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);

		int sum = 0;

		for (int i = 0; i < roman.length(); i++) {
			char c = roman.charAt(i);
			if (i == roman.length() - 1) {
				sum += m.get(c);
				continue;
			}
			char next = roman.charAt(i + 1);
			if (m.get(c) >= m.get(next)) {
				sum += m.get(c);
			} else {
				sum -= m.get(c);
			}
		}
		return sum;
	}

	public static void main(String[] args) {
//		https://duckduckgo.com/?q=3999+in+roman&atb=v102-1_f&ia=answer
//		https://www.google.com/search?ei=Y7eNWtOlEM6EtQWO-rSgCQ&q=2999+in+roman&oq=2999+in+roman&gs_l=psy-ab.3..0i7i30k1j0j0i30k1l2j0i5i30k1j0i8i30k1l3.7240.7774.0.8029.4.4.0.0.0.0.110.416.1j3.4.0....0...1c.1.64.psy-ab..0.4.416...0i67k1.0.3PG6xdJnhDY		
//		String roman = "MCMIV"; // 1904
//		String roman = "MMMCMXCIX"; // 3999
		String roman = "MMD"; // 2500
		System.out.println("Integer form of Roman Numeral" + " is " + romanToDecimal(roman));

	}

}
