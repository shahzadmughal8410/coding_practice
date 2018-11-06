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
 
	 * @param args
	 */
	public static String longestPalindrome(String s) {
		if(null==s || s.length()==0) {
			return "";
		}
		int start = 0;
		int maxLength = 1;
		int lo, hi;
		int len = s.length();
		
		for(int i=1; i<len; i++) {
			//even
			lo = i-1;
			hi = i;
			while(lo>=0 && hi<len && s.charAt(lo)==s.charAt(hi)) {
				if(hi-lo+1>maxLength) {
					start = lo;
					maxLength = hi-lo+1;
				}
				--lo;
				++hi;
			}
			
			//odd
			lo = i-1;
			hi=i+1;
			while(lo>=0 && hi<len && s.charAt(lo)==s.charAt(hi)) {
				if(hi-lo+1>maxLength) {
					start = lo;
					maxLength = hi-lo+1;
				}
				--lo;
				++hi;
			}
		}
		return s.substring(start,start+maxLength);
	}
	
	public static void main(String[] args) {
//		String s = "";
//		String s = "a";
//		String s = "babad";
//		String s = "cdc";
		String s = "abcdefgeekskeegvbncmf";
		System.out.println("["+"] has the longest palindromic substring ["+longestPalindrome(s)+"]");

	}

}
