/**
 * 
 */
package sm.coding.string.leetcode._038;

/**
 * @author shahzadmughal8410
 *
 */
public class CountAndSay_038_Str {

	/**
he count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.
Given an integer n, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"

https://leetcode.com/problems/count-and-say/description/
https://www.geeksforgeeks.org/look-and-say-sequence/
 
	 * @param args
	 */
	public static String countAndSay(int n) {
		if(n<1) {
			return "";
		}
		
		String cur = "1";
		int i = 1;
		
		while(i<n) {
			int count = 1;
			StringBuilder next = new StringBuilder();
			int previous = cur.charAt(0)-'0';
			int c = previous;
			for(int j=1;j<cur.length();j++) {
				c = cur.charAt(j) - '0';
				if(c!=previous) {
					next.append(count);
					next.append(previous);
					count = 1;
				}else {
					++count;
				}
				previous = c;
			}
			next.append(count);
			next.append(c);
			++i;
			cur = next.toString();
//			System.out.println(cur);
		}
		return cur;
	}
	
	public static void main(String[] args) {
		int n = 8;
		System.out.println(n+"="+countAndSay(n));

	}

}
