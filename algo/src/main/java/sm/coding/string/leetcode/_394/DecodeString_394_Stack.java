/**
 * 
 */
package sm.coding.string.leetcode._394;

import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class DecodeString_394_Stack {

	/**
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

Links
https://leetcode.com/problems/decode-string/description/
https://www.geeksforgeeks.org/decode-string-recursively-encoded-count-followed-substring/

Submission
https://leetcode.com/submissions/detail/168984683/
You are here! 
Your runtime beats 99.01 % of java submissions.

	 * @param args
	 */
	
	public static String decodeString(String s) {
		if (s == null || s.isEmpty())
			return s;
		Deque<String> cstack = new LinkedList<>();
		Deque<Integer> istack = new LinkedList<>();
		String result = "";
		int idx = 0;
		while (idx < s.length()) {
			char ch = s.charAt(idx);
			/*
			 * Number Whenever we encouter a number, push into intger stack
			 */
			if (Character.isDigit(ch)) {
				int count = 0;
				while (Character.isDigit(ch)) {
					count = count * 10 + (ch - '0');
					idx++;
					ch = s.charAt(idx);
				}
				istack.push(count);
			} else if (ch == '[') { // open bracket

				cstack.push(result);
				result = "";
				idx++;

			} else if (ch == ']') { // close bracket

				StringBuilder sb = new StringBuilder(cstack.pop());
				int times = istack.pop();
				for (int i = 0; i < times; i++) {
					sb.append(result);
				}
				result = sb.toString();
				idx++;
			} else { // character found
				result += ch;
				idx++;
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
//		String encoded = "4[a]2[3[b]c]";
//		String encoded = "ax2[3[b]c]";
		String encoded = "2[a]3[b4[c]]";
//		System.out.printf("Encoded = %s, decoded = %s", encoded, decodeString(encoded));
		System.out.printf("Encoded = %s, decoded = %s", encoded, SolutionDebug.decodeString(encoded));

	}

}

class SolutionDebug{

	static StringBuilder format = new StringBuilder();

	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
		debugRow(cols);
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}
	
	public static String decodeString(String s) {
		debug("Encoded = "+s);
		if (s == null || s.isEmpty())
			return s;
		Deque<String> cstack = new LinkedList<>();
		Deque<Integer> istack = new LinkedList<>();
		String result = "";
		int idx = 0;
		tableColumns(String.format("%15s", "idx"), "ch", "count", "result:15", "istack", "cstack", "cst size");
		while (idx < s.length()) {
			char ch = s.charAt(idx);
			debug(String.format("%90s", "ch="+ch).replaceAll(" ", "-"));
			/*
			 * Number Whenever we encouter a number, push into intger stack
			 */
			if (Character.isDigit(ch)) {
				debug(String.format("%90s", "Digit="+ch).replaceAll(" ", "-"));
				int count = 0;
				while (Character.isDigit(ch)) {
					debugRow(String.format("%15s", idx), ch, count, String.format("[%s]", result), istack, cstack, cstack.size());
					count = count * 10 + (ch - '0');
					idx++;
					ch = s.charAt(idx);
				}
				istack.push(count);
				debugRow(String.format("%15s", idx), ch, count, String.format("[%s]", result), istack, cstack, cstack.size());
			} else if (ch == '[') { // open bracket
				debug(String.format("%90s", "Open="+ch).replaceAll(" ", "-"));
				cstack.push(result);
				result = "";
				idx++;
				debugRow(String.format("%15s", idx), ch, 0, String.format("[%s]", result), istack, cstack, cstack.size());
			} else if (ch == ']') { // close bracket
				debug(String.format("%90s", "Close="+ch).replaceAll(" ", "-"));
				StringBuilder sb = new StringBuilder(cstack.pop());
				int times = istack.pop();
				debug(String.format("%90s", "previousResult=["+sb.toString()+"]").replaceAll(" ", "-"));
				debug(String.format("%90s", "adding["+result+"]"+times+" times").replaceAll(" ", "-"));
				for (int i = 0; i < times; i++) {
					sb.append(result);
				}
				result = sb.toString();
				idx++;
				debugRow(String.format("%15s", idx), ch, 0, String.format("[%s]", result), istack, cstack, cstack.size());
			} else { // character found
				debug(String.format("%90s", "Character="+ch).replaceAll(" ", "-"));
				result += ch;
				idx++;
				debugRow(String.format("%15s", idx), ch, 0, String.format("[%s]", result), istack, cstack, cstack.size());
			}
		}
		debug("Decoded = "+result);
		return result;
	}

}
