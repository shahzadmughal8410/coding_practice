/**
 * 
 */
package sm.coding.string.leetcode._005;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestPalindromicSubstring_005_Str {

	/**
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example:

Input: "babad"

Output: "bab"

Note: "aba" is also a valid answer.
 

Example:

Input: "cbbd"

Output: "bb"

 https://leetcode.com/problems/longest-palindromic-substring/description/
 https://www.geeksforgeeks.org/longest-palindromic-substring-set-1/
 https://www.geeksforgeeks.org/longest-palindromic-substring-set-2/

Submission
https://leetcode.com/submissions/detail/197508761/
You are here! 
Your runtime beats 71.35 % of java submissions.

	 * @param args
	 */
	public static String longestPalindrome(String s) {
		if(null==s || s.length()==0) {
			return "";
		}

		Pair p = new Pair();
		
		for(int i=0; i<s.length(); i++) {
			//odd
			extendPalindromeAroundCenter(s, i, i, p);
			//even
			extendPalindromeAroundCenter(s, i, i+1, p);
		}
		return s.substring(p.start,p.start+p.maxLength);
	}
	
	public static void extendPalindromeAroundCenter(String s, int left, int right, Pair p) {
		while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)) {
			if(right-left+1>p.maxLength) {
				p.start = left;
				p.maxLength = right-left+1;
			}
			left--;
			right++;
		}
	}
	
	public static void main(String[] args) {
//		String s = "";
//		String s = "a";
//		String s = "babad";
//		String s = "cdc";
		String s = "abcdefgeekskeegvbncmf";
		System.out.println("["+s+"] has the longest palindromic substring ["+longestPalindrome(s)+"]");

	}

}

class Pair {
	int start;
	int maxLength;
}
