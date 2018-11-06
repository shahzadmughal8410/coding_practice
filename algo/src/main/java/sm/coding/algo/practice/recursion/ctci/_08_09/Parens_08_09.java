/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class Parens_08_09 {

	/**
8.9 Parens: Implement an algorithm to print all valid (Le., properly opened and closed) combinations
of n pairs of parentheses.
EXAMPLE
Input: 3
Output: (( () ) ) , (() ()), (()) () , ()(()), ()()()

Hints: #138, #174, #187, #209, #243, #265, #295 
	 * @param args
	 */
	public static List<String> parens(int n){
		List<String> result = new ArrayList<>();
		parensHelper(n, n, "", result);
		return result;
	}
	
	public static void parensHelper(int left, int right, String sofar, List<String> result) {
		if(left<0 || right<left) {
			return;
		}
		
		if(left==0 && right==0) {
			result.add(sofar);
			return;
		}
		
		if(left>0) {
			parensHelper(left-1, right, sofar+"(", result);
		}
		
		if(right>0) {
			parensHelper(left, right-1, sofar+")", result);

		}
	}
	
	
	public static void main(String[] args) {
		
//		List<String> result = parens(2);
		List<String> result = SolutionDebug.parens(2);
		System.out.println(result);

	}

}

class SolutionDebug {
	public static String indent = "|---";
	public static void debug(Object msg) {
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

	public static List<String> parens(int n){
		List<String> result = new ArrayList<>();
		parensHelper(n, n, "", result);
		return result;
	}
	
	public static void parensHelper(int left, int right, String sofar, List<String> result) {
		debug(String.format("left=%d, right=%d, sofar=%s", left, right, sofar));
		if(left<0 || right<left) {
			debug("Invalid");
			return;
		}
		
		if(left==0 && right==0) {
			result.add(sofar);
			debug("added="+sofar);
			return;
		}
		
		if(left>0) {
			debug("adding (");
			String actual = incrementIndent();
			parensHelper(left-1, right, sofar+"(", result);
			setIndent(actual);
		}else {
			debug("left<1="+left);
		}
		
		if(right>0) {
			debug("adding )");
			String actual = incrementIndent();
			parensHelper(left, right-1, sofar+")", result);
			setIndent(actual);
		}else {
			debug("right<1="+right);
		}
	}
}
