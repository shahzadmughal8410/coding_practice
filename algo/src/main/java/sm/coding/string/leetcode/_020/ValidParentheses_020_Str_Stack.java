/**
 * 
 */
package sm.coding.string.leetcode._020;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author smughal
 *
 */
public class ValidParentheses_020_Str_Stack {

	/**
	 * 
Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

https://www.geeksforgeeks.org/check-for-balanced-parentheses-in-an-expression/
https://leetcode.com/problems/valid-parentheses/description/
https://leetcode.com/problems/valid-parentheses/discuss/9178/Short-java-solution

Submission
https://leetcode.com/submissions/detail/187685378/
You are here! 
Your runtime beats 21.22 % of java submissions.
	 * 
	 * @param args
	 */
	public static boolean validParentheses(String input) {
		if(input==null || input.length()%2 ==1) {
			return false;
		}
		
		Deque<Character> stack = new LinkedList<>();
		
		for(int i=0; i<input.length();i++) {
			char c = input.charAt(i);
			
			if(c=='(') {
				stack.push(')');
			} else if(c=='{') {
				stack.push('}');
			}else if(c=='[') {
				stack.push(']');
			}else if (stack.isEmpty() || stack.pop()!=c) {
				return false;
			}
		}
		return stack.isEmpty();
	}
	
	public static void main(String[] args) {
//		String input = "(){}[]";
		String input = "({}){()}[{())]";
		System.out.println(validParentheses(input));

	}

}
