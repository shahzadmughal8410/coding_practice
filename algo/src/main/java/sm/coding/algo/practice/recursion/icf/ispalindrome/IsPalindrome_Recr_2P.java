/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.ispalindrome;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class IsPalindrome_Recr_2P {

	public static boolean isPalindrome(String input) {
		return isPalindrome(input, 0, input.length()-1);
	}
	
	public static boolean isPalindrome(String input, int start, int end) {
		if(end<start) {
			return true;
		}		
		return input.charAt(start) == input.charAt(end) && isPalindrome(input, start+1, end-1);
	}
	
	public static boolean isPalindrome_Iterative(String input) {
		if(null==input || input.length()<=1) {
			return true;
		}
		int start = 0;
		int end = input.length()-1;
		
		while(start<end) {
			if(input.charAt(start)!=input.charAt(end)) {
				return false;
			}
			++start;
			--end;
		}
		return true;
	}
	
	public static void main(String[] args) {
		String input = "" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));
		
		input = "a" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));

		input = "civic" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));

		input = "anna" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));

		input = "an1na" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));

		input = "hello" ;
		System.out.printf("[%s]=%s, iterative=%s %n", input, isPalindrome(input), isPalindrome_Iterative(input));

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
	
	
	public static boolean isPalindrome(String input) {
		reset();
		debug("Input="+input);
		tableColumns("input", "start", "end", "charAt(start)", "charAt(end)", "end<start");
		debugColumns();
		return isPalindrome(input, 0, input.length()-1);
	}
	
	public static boolean isPalindrome(String input, int start, int end) {
		debugRow(input, start, end, input.charAt(start), input.charAt(end), end+"<"+start+"="+(end<start));
		if(end<start) {
			return true;
		}		
		return input.charAt(start) == input.charAt(end) && isPalindrome(input, start+1, end-1);
	}
}
