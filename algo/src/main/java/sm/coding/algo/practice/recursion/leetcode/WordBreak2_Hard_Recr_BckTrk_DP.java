/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Questions
 * https://leetcode.com/problems/word-break-ii/description/
 * http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
 * 
 * @author smughal
 *
 */
public class WordBreak2_Hard_Recr_BckTrk_DP {

	public static List<String> wordBreak(String input, List<String> dict) {
		List<String> result = new ArrayList<>();
		wordBreakHelper(input, dict, new ArrayList<>(), result);
		return result;
	}
	
	private static void wordBreakHelper(String input, List<String> dict, List<String> choosen, List<String> result) {
		if(input.length()==0) {
			StringBuilder sb = new StringBuilder();
			choosen.forEach(s->sb.append(s).append(" "));
			result.add(sb.toString().trim());
		}
		
		// backtracking problem
		// i should be <= length as if its i<length it will skip the last character as substring is exclusive of end index
		for(int i=0;i<=input.length(); i++) {
			String ch = input.substring(0, i);
			if(dict.contains(ch)) {
				choosen.add(ch);	
				wordBreakHelper(input.substring(i, input.length()), dict, choosen, result);
				choosen.remove(choosen.size()-1);
			}
		}
	}
	
	public static void main(String[] args) {
		List<String> dict = new ArrayList<>();
		dict.add("mobile");
		dict.add("samsung");
		dict.add("sam");
		dict.add("sung");
		dict.add("man");
		dict.add("mango");
		dict.add("icecream");
		dict.add("and");
		dict.add("go");
		dict.add("i");
		dict.add("like");
		dict.add("ice");
		dict.add("cream");
		
		List<String> result;
		
		result = DebugSolution.wordBreakDebug("ilikesamsung", dict);
//		result = wordBreak("ilikesamsung", dict);
		System.out.println("result="+result);
		
		dict.clear();
		
		dict.add("cat");
		dict.add("cats");
		dict.add("and");
		dict.add("sand");
		dict.add("dog");
		
		result = DebugSolution.wordBreakDebug("catsanddog", dict);
//		result = wordBreak("catsanddog", dict);
		System.out.println("result="+result);
		
	}

}

class DebugSolution {
	static String indent = "";
	
	public static List<String> wordBreakDebug(String input, List<String> dict) {
		List<String> result = new ArrayList<>();
		wordBreakHelperDebug(input, dict, new ArrayList<>(), result);
		return result;
	}
	
	private static void wordBreakHelperDebug(String input, List<String> dict, List<String> choosen, List<String> result) {
		if(input.length()==0) {
			debug(choosen);
			StringBuilder sb = new StringBuilder();
			choosen.forEach(s->sb.append(s).append(" "));
			result.add(sb.toString().trim());
		}
		
		// backtracking problem
		// i should be <= length as if its i<length it will skip the last character as substring is exclusive of end index
		for(int i=0;i<=input.length(); i++) {
			String ch = input.substring(0, i);
			if(dict.contains(ch)) {
				debug(ch+" -- Y");
				choosen.add(ch);	
				String indentActual = indent;
				indent = indent+"\t";
				wordBreakHelperDebug(input.substring(i, input.length()), dict, choosen, result);
				choosen.remove(choosen.size()-1);
				indent = indentActual;
			}
			debug(ch+" -- X");
		}
	}
	
	public static void debug(Object msg) {
		System.out.println("DEBUG "+indent+msg);
	}
}
