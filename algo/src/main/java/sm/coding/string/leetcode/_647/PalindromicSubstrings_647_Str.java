/**
 * 
 */
package sm.coding.string.leetcode._647;

/**
 * @author shahzadmughal8410
 *
 */
public class PalindromicSubstrings_647_Str {

	/**
Given a string, your task is to count how many palindromic substrings in this string.

The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example 1:
Input: "abc"
Output: 3
Explanation: Three palindromic strings: "a", "b", "c".
Example 2:
Input: "aaa"
Output: 6
Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
Note:
The input string length won't exceed 1000.

https://leetcode.com/problems/palindromic-substrings/solution/ 
https://articles.leetcode.com/longest-palindromic-substring-part-ii/
https://www.geeksforgeeks.org/count-palindrome-sub-strings-string-set-2/
https://www.geeksforgeeks.org/count-palindrome-sub-strings-string/
https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-1/
https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-2/
https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-3-2/
https://www.geeksforgeeks.org/manachers-algorithm-linear-time-longest-palindromic-substring-part-4/

Submission
https://leetcode.com/submissions/detail/189473063/
You are here! 
Your runtime beats 86.18 % of java submissions. 
	 * @param args
	 */
	public static int countSubstrings(String s) {
		if(null==s || s.length()==0) {
			return 0;
		}
		
		int[] count = new int[1];
		
		for(int i =0; i<s.length();i++) {
			extendPalindromeAroundCenter(s, i , i , count); //odd
			extendPalindromeAroundCenter(s, i , i+1 , count); //evenm
		}
		
		return count[0];
	}
	
	public static void extendPalindromeAroundCenter(String s, int left, int right, int count[]) {
		while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)) {
			count[0]++;
			left--;
			right++;
		}
	}
	
	
	public static void main(String[] args) {
		String s = "aaa";
		System.out.printf("Palindromes in [%s] are [%d]", s, countSubstrings(s));
		

	}

}
