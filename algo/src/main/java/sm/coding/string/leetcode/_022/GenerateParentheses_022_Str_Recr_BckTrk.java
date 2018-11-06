/**
 * 
 */
package sm.coding.string.leetcode._022;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class GenerateParentheses_022_Str_Recr_BckTrk {

	/**
 Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]


https://leetcode.com/problems/generate-parentheses/description/
https://leetcode.com/problems/generate-parentheses/solution/
 
	 * @param args
	 */
	public static List<String> generateParenthesis(int n){
		List<String> result = new ArrayList<>();
		generateParenthesisHelper(n, "", 0, 0, result);
		return result;
	}
	
	public static void generateParenthesisHelper(int n, String sofar, int open, int close, List<String> result){
		
		if(n*2==sofar.length()) {
			result.add(sofar);
			return;
		}
		
		if(open<n) {
			generateParenthesisHelper(n, sofar+"(", open+1, close, result);
		}
		
		if(close<open) {
			generateParenthesisHelper(n, sofar+")", open, close+1, result);
		}
	}
	
	public static void main(String[] args) {
		int n = 3;
		List<String> result = generateParenthesis(3);
		System.out.println("Total valid combinations for "+n+" pairs: "+result.size());
		result.forEach(r->System.out.println(r));

	}

}
