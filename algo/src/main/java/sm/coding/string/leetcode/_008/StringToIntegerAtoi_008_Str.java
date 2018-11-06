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
 
	 * @param args
	 */
	public static int stringToInt(String s) {
		if(null==s || s.length()==0) {
			return 0;
		}
		int sum = 0;
		int sign = 0;
		for(int i = 0;i<s.length(); i++) {
			char c = s.charAt(i);
			if(c==' ') {
				continue;
			}
			if(c=='-' && sign==0) {
				sign = -1;
				
			}else if( c >= '0' && c <= '9') { // Character.isDigit(c) can be used isntead
				long lSum = sum;
				sum = (sum*10) + (c-'0');
				lSum= (lSum*10L) + (c-'0');
				if(lSum!=sum) {
					System.err.println("IntegerOutOfRange");
					break;
				}
			}
		}
		if(sign==0) {
			sign = 1;
		}
		return sum*sign;
	}
	
	
	public static void main(String[] args) {
//		String s = "";
//		String s = null;
		String s = "  - 012";
//		String s = "  + 012";
//		String s = "   0 1 2";
//		String s = "   0 1994699999999978";
		System.out.println("["+"] integer value is = "+stringToInt(s));
	}

}
