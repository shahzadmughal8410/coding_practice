/**
 * 
 */
package sm.coding.string.leetcode._017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class LetterCombinationsOfAPhoneNumber_017_Str_Recr_BckTrk {

	/**
Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

Note:
Although the above answer is in lexicographical order, your answer could be in any order you want. 

https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
https://www.geeksforgeeks.org/find-possible-words-phone-digits/

https://leetcode.com/submissions/detail/172611305/ 
You are here! 
Your runtime beats 96.27 % of java submissions.

	 * @param args
	 */
	public static List<String> letterCombinations_BruteForce(String digits){
		List<String> result = new ArrayList<>();
		if(null==digits || digits.length()==0) {
			return result;
		}
		
		String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		letterCombinationsHelper(digits, 0, "", map, result);
		return result;
	}
	
	public static void letterCombinationsHelper(String digits, int index, String sofar, String[] map, List<String> result) {
		if(sofar.length() == digits.length()) {
			result.add(sofar);
			return;
		}
		
		String letters = map[digits.charAt(index)-'0'];
		for(int i = 0; i<letters.length();i++) {
			// choose
			char c = letters.charAt(i);
			// explore
			letterCombinationsHelper(digits, index+1, sofar+c, map, result); // implicit backtracking
		}
	}
	/**
Submission
https://leetcode.com/submissions/detail/197505073/
You are here! 
Your runtime beats 37.26 % of java submissions.

	 * @param digits
	 * @return
	 */
	public static List<String> letterCombinations_BFS(String digits) {
		LinkedList<String> q = new LinkedList<String>();
		if (digits.isEmpty())
			return q;
		String[] map = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		q.offer("");
		for (int i = 0; i < digits.length(); i++) {
			int d = Character.getNumericValue(digits.charAt(i));
			int size = q.size();     // level order traversal, number of nodes/strings already in the queue
			for (int k = 0; k < size; k++) {
				String previous = q.poll();
				String letters = map[d];
				for (int j=0;j<letters.length(); j++)
					q.offer(previous + letters.charAt(j));
			}
		}
		return q;
	}
	
	public static void main(String[] args) {
		String digits = "23";
//		List<String> result = letterCombinations(digits);
		List<String> result = SolutionDebug.letterCombinations(digits);
		System.out.println("Recursion Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		result = letterCombinations_BFS(digits);
		System.out.println("Itrrative Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		
		digits = "456";
		result = letterCombinations_BruteForce(digits);
		System.out.println("Recursion Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		result = letterCombinations_BFS(digits);
		System.out.println("Itrrative Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		
		digits = "797";
		result = letterCombinations_BruteForce(digits);
		System.out.println("Recursion Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		result = letterCombinations_BFS(digits);
		System.out.println("Itrrative Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		
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
	
	public static List<String> letterCombinations(String digits){
		debugRecr("digits=["+digits+"]");
		List<String> result = new ArrayList<>();
		if(null==digits || digits.length()==0) {
			return result;
		}
		
		String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		debugRecr("Map="+Arrays.asList(map));
		letterCombinationsHelper(digits, 0, "", map, result);
		debugRecr("Rresult size="+result.size());
		debugRecr(result);
		return result;
	}
	
	public static void letterCombinationsHelper(String digits, int offset, String sofar, String[] map, List<String> result) {
		debugRecr("digits="+digits+", offset="+offset+", sofar=["+sofar+"], result="+result) ;
		if(sofar.length() == digits.length()) {
//		if(offset == digits.length()) {
			result.add(sofar);
			debugRecr("Result added=["+sofar+"]");
			return;
		}
		
		String letters = map[digits.charAt(offset)-'0'];
		debugRecr("letters="+letters);
		for(int i = 0; i<letters.length();i++) {
			// choose
			char chsoosen = letters.charAt(i);
			debugRecr("chsoosen=["+chsoosen+"]");
			
			String actual = incrementIndent();
			
			// explore
			letterCombinationsHelper(digits, offset+1, sofar+chsoosen, map, result); // implicit backtracking
			setIndent(actual);
		}
	}

}
