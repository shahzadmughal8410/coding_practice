/**
 * 
 */
package sm.coding.string.leetcode._131;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class PalindromePartitioning_131_Str_Recr_BckTrk_DP {
	
	/**
	 * 
	 * Bonus Question: Palindromic Decomposition A “Palindromic Decomposition” of string S, 
	 * is a decomposition of the string into substrings, such that all those substrings are valid palindromes. 
	 * A single character is considered a valid palindrome for this problem. 
	 * Print out all possible palindromic decompositions of a given string. 
	 * 
	 * e.g. 
	 * Input: abracadabra 
	 * Output: 
	 * a|b|r|a|c|a|d|a|b|r|a| 
	 * a|b|r|a|c|ada|b|r|a| 
	 * a|b|r|aca|d|a|b|r|a|
	 * 
	 * 
	https://leetcode.com/problems/palindrome-partitioning/description/
	https://www.geeksforgeeks.org/given-a-string-print-all-possible-palindromic-partition/ 

Submission
https://leetcode.com/submissions/detail/190410675/
You are here! 
Your runtime beats 83.25 % of java submissions.
	 * @author smughal
	 *
	 */
	public static List<List<String>> partition(String input) {
		List<List<String>> result = new LinkedList<>();
		pdHelper(input, new ArrayList<>(), result, 0);
		return result;
	}

	
	public static void pdHelper(String input, List<String> choosen, List<List<String>> result, int index) {
		if(input.length()==index) {
			result.add(new ArrayList<>(choosen));
		}
		for(int i = index; i<input.length(); i++) {
			if(isPalindrome(input, index, i)) {
				String ch = input.substring(index, i+1);
				choosen.add(ch);
				pdHelper(input, choosen, result, i+1);
				choosen.remove(choosen.size()-1);
			}
		}		
	}
	
	public static boolean isPalindrome(String s, int start, int end) {
		while (start <= end) { // works with both (start < end) [here middle character is not compared for odd length which is fine] and (start <= end) 
			if (s.charAt(start++) != s.charAt(end--)) {
				return false;
			}
	    }
	    return true;
	}
	
	/**
	 * Some how not working !!! but logic is correct
	 * @param str
	 * @param index
	 * @param result
	 */
	public static void pd_CW(String str, int index, List<String> result) {
		System.out.println("'"+str+"', '"+index+"', "+result);
		if(index>=str.length()) {
			System.out.println(result);
		}
		
		for(int end = index+1; end<=str.length(); end++) {
			String sub = str.substring(index,  end);
			if(isPalindrome(str, index, end-1)) {
				result.add(sub);
				pd_CW(str, index+1, result);
				result.remove(result.size()-1);
			}
		}
	}


	
//	public static boolean isPalindrome(String s) {
//		StringBuilder sb = new StringBuilder(s);
//		return s.equals(sb.reverse().toString());
//	}
	
	public static void main(String[] args) {
		List<List<String>> result;
//		result = pd("apple");
//		result = DebugSolution.pd("abba");
//		System.out.println("result="+result);
		
		
//		result = pd("abba");
//		result = DebugSolution.pd("abba");
//		result = DebugSolution.pd("aab");
//		result = DebugSolution.pd("abracadabra");
		result = partition("abracadabra");
//		result = partition("aba");
		result.forEach(r->System.out.println(r));

	}
	
}

class DebugSolution {
	static String indent = "";
	
	public static void debug(Object msg) {
		System.out.println("DEBUG "+indent+msg);
	}
	
	public static List<String> pd(String input) {
		List<String> result = new ArrayList<>();
		pdHelper(input, new ArrayList<>(), result);
		return result;
	}

	
	public static void pdHelper(String input, List<String> choosen, List<String> result) {
		debug("input=["+input+"], choosen="+choosen);
		if(input.length()==0) {
			debug(choosen);
			result.add(choosen.toString());
		}
		for(int i = 1; i<=input.length(); i++) {
			String ch = input.substring(0, i);
			if(PalindromePartitioning_131_Str_Recr_BckTrk_DP.isPalindrome(input, 0, i-1)) {
				debug("ch="+ch+" -- Y");
				choosen.add(ch);
				String indentActual = indent;
				indent = indent+"\t";
				pdHelper(input.substring(i, input.length()), choosen, result);
				choosen.remove(choosen.size()-1);
				indent = indentActual;
			}
			debug("ch="+ch+" -- N");
		}
		
	}
	
}
