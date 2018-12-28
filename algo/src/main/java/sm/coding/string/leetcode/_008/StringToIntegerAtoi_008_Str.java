/**
 * 
 */
package sm.coding.string.leetcode._008;

/**
 * @author shahzadmughal8410
 *
 */
public class StringToIntegerAtoi_008_Str {

	/**
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

 

Requirements for atoi:

The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

https://leetcode.com/problems/string-to-integer-atoi/description/

Submission
https://leetcode.com/submissions/detail/197516734/
You are here! 
Your runtime beats 32.83 % of java submissions.
 
	 * @param args
	 */
	public static int stringToInt(String str) {
		if (str.trim().isEmpty()) {
			return 0;
		}
		str = str.trim();
		int ans = 0;
		int sign = 1;
		int i = 0;
		if (str.charAt(i) == '-' || str.charAt(i) == '+') {
			sign = str.charAt(i++) == '+' ? 1 : -1;
		}
		while (i < str.length()) {
			int tmp = str.charAt(i) - '0';
			if (tmp < 0 || tmp > 9) {
				break;
			}
			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && Integer.MAX_VALUE % 10 < tmp)) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			} else {
				ans = ans * 10 + tmp;
			}
			++i;
		}
		return sign * ans;
	}
	
	
	public static void main(String[] args) {
//		String s = "";
//		String s = null;
		String s = "  - 012";
//		String s = "  + 012";
//		String s = "   0 1 2";
//		String s = "   0 1994699999999978";
		System.out.println("["+s+"] integer value is = "+stringToInt(s));
		
		s = "hi 123";
		System.out.println("["+s+"] integer value is = "+stringToInt(s));
	}

}
