/**
 * 
 */
package sm.coding.string.leetcode._266;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class PalindromePermutation_266_Str {

	/**
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

https://leetcode.com/problems/palindrome-permutation/description/
CTCI 1.4

Submission
https://leetcode.com/submissions/detail/169099225/
You are here! 
Your runtime beats 69.21 % of java submissions. 
 
	 * 
	 * @param args
	 */
	public static boolean canPermutePalindrome(String s) {
		if(null==s || s.length()==0) {
			return false;
		}
		
		Set<Character> set = new HashSet<>();
		for(int i=0; i<s.length();i++) {
			if(!set.add(s.charAt(i))) {
				set.remove(s.charAt(i));
			}
		}
		return set.size()<=1;
	}
	
	
	public static void main(String[] args) {
		String s = "code";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));
		s = "aab";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));
		s = "carerac";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));
		s = "aabb";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));
		s = "aabbc";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));
		s = "aabbcd";
		System.out.println(s+", is a permutation of palindrome="+canPermutePalindrome(s));

	}

}
