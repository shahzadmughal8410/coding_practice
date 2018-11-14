/**
 * 
 */
package sm.coding.string.leetcode._273;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class IntegerToEnglishWords_273_Str_Recr {

	/**
 Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,

123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

 https://leetcode.com/problems/integer-to-english-words/description/ 
https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand 
https://www.geeksforgeeks.org/program-to-convert-a-given-number-to-words-set-2/ 
https://www.geeksforgeeks.org/convert-number-to-words/ 

Submission
https://leetcode.com/submissions/detail/189490059/
You are here! 
Your runtime beats 43.73 % of java submissions.
	 * @param args
	 */
	public static String numToEnglish(int num) {
		String[] thousands = {"", "Thousand", "Million", "Billion"};
		if(num==0) {
			return "Zero";
		}
		
		int i =0;
		String word = "";
		
		while(num>0) {
			if(num%1000 != 0) {
				word = helper(num%1000) + thousands[i] + " " +word;
			}
			num/=1000;
			++i;
		}
		return word.trim();
	}
	
	public static String helper(int num) {
		String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

		if(num==0) {
			return "";
		}else if(num<20) {
			return lessThan20[num] + " ";
		}else if(num<100) {
			return tens[num/10] + " " + helper(num%10);
		}else {
			return lessThan20[num/100] + " Hundred " + helper(num%100);
		}
	}
	
	
	public static void main(String[] args) {
		int num = 1001234;
//		String english = numToEnglish(num);
		String english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

		num = 0;
		english = numToEnglish(num);
//		english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

		num = 9876;
		english = numToEnglish(num);
//		english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

		num = 98765;
		english = numToEnglish(num);
//		english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

		num = 6473829;
		english = numToEnglish(num);
//		english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

		num = 1234567897;
//		english = numToEnglish(num);
		english = SolutionDebug.numToEnglish(num);
		System.out.printf("%d in english is, [%s] %n", num, english);

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

	public static String numToEnglish(int num) {
		String[] thoussands = {"", "Thousand", "Million", "Billion"};
		if(num==0) {
			return "Zero";
		}
		
		int i =0;
		String word = "";
		tableColumns("num:10", "i:8", "word:100");
		while(num>0) {
			debugColumns();
			debugRow(num, i, word);
			if(num%1000 != 0) {
				word = helper(num%1000) + thoussands[i] + " " +word;
			}
			num/=1000;
			++i;
		}
		debugColumns();
		debugRow(num, i, word);
		reset();
		return word;
	}
	
	public static String helper(int num) {
		String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
		String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
		debugRecr("num="+num);
		if(num==0) {
			String result = "";
			debugRecr("word="+result);
			return result;
		}else if(num<20) {
			String result = lessThan20[num] + " ";
			debugRecr("word="+result);
			return result;
		}else if(num<100) {
			String actual = incrementIndent();
			String result = tens[num/10] + " " + helper(num%10);
			setIndent(actual);
			debugRecr("word="+result);
			return result;
		}else {
			String actual = incrementIndent();
			String result = lessThan20[num/100] + " " + helper(num%100);
			setIndent(actual);
			debugRecr("word="+result);
			return result;
		}
	}
}
