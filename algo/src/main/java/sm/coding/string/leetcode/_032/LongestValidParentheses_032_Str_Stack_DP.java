/**
 * 
 */
package sm.coding.string.leetcode._032;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestValidParentheses_032_Str_Stack_DP {

	/**
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

For "(()", the longest valid parentheses substring is "()", which has length = 2.

Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

https://leetcode.com/problems/longest-valid-parentheses/solution/ 
http://n00tc0d3r.blogspot.in/2013/04/longest-valid-parentheses.html 
https://www.geeksforgeeks.org/length-of-the-longest-valid-substring/ 

Submission
https://leetcode.com/submissions/detail/200287954/
You are here! 
Your runtime beats 51.98 % of java submissions. 
	 * @param args
	 */
	public static int longestValidParentheses(String s) {
		if(null==s || s.length()==0) {
			return 0;
		}
		
		int left = 0;
		int right = 0;
		int maxLength = 0;
		// traverse left to right
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='(') {
				left++;
			}else {
				right++;
			}
			if(left==right) {
				maxLength = Math.max(maxLength, right*2);
			}else if(right>left) { // means unbalanced 
				left = 0;
				right = 0;
			}
		}
		//reset let and right
		left = 0;
		right = 0;
		//traverse right to left
		for(int i=s.length()-1;i>=0;i--) {
			if(s.charAt(i)=='(') {
				left++;
			}else {
				right++;
			}
			if(left==right) {
				maxLength = Math.max(maxLength, left*2);
			}else if(left>right) { // means unbalanced 
				left = 0;
				right = 0;
			}
		}
		return maxLength;
	}
	
	public static void main(String[] args) {
		String s = "(()" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);
		s = ")()())" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);
		s = "())()())" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);
		s = "())()()(()" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);
		s = "(()()" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);
		s = "()(()" ;
		System.out.println("Longest valid parenmtheses="+longestValidParentheses(s)+", s="+s);

	}

}
