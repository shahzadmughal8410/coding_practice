/**
 * 
 */
package sm.coding.string.leetcode._131;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

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
				String ch = input.substring(index, i+1);// as substring end index is exclusive
				choosen.add(ch);
				pdHelper(input, choosen, result, i+1);// starting with next character for recursive call
				choosen.remove(choosen.size()-1); //back tracking
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
		result.forEach(r->System.out.println(r));
		result = partition("aba");
		result.forEach(r->System.out.println(r));

		result = SolutionDebug.partition("abab");
		result.forEach(r->System.out.println(r));

	}
	
}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
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
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}

	public static String indent = "|---";
	public static void debugRecr(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}
	
	public static List<List<String>> partition(String input) {
		List<List<String>> result = new LinkedList<>();
		debugRecr("starting from 0 th as index");
		pdHelper(input, new ArrayList<>(), result, 0);
		return result;
	}

	
	public static void pdHelper(String input, List<String> choosen, List<List<String>> result, int index) {
		debugRecr("index="+index+", choosen="+choosen+", result="+result);
		if(input.length()==index) {
			debugRecr("Found solution choosen="+choosen);
			result.add(new ArrayList<>(choosen));
		}
		for(int i = index; i<input.length(); i++) {
			boolean isPalindrome = isPalindrome(input, index, i);
			String ch = input.substring(index, i+1);
			debugRecr("index="+index+", i="+i+", ch="+ch+", isPalindrome("+index+","+i+")="+isPalindrome);
			if(isPalindrome) {
				choosen.add(ch);
				String actual = incrementIndent();
				pdHelper(input, choosen, result, i+1);
				setIndent(actual);
				choosen.remove(choosen.size()-1); //back tracking
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
}