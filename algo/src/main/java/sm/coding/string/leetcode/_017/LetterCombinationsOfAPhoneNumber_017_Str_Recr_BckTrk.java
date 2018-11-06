/**
 * 
 */
package sm.coding.string.leetcode._017;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	public static List<String> letterCombinations(String digits){
		List<String> result = new ArrayList<>();
		if(null==digits || digits.length()==0) {
			return result;
		}
		
		String[] map = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		letterCombinationsHelper(digits, 0, "", map, result);
		return result;
	}
	
	public static void letterCombinationsHelper(String digits, int offset, String sofar, String[] map, List<String> result) {
		if(sofar.length() == digits.length()) {
//		if(offset == digits.length()) {
			result.add(sofar);
			return;
		}
		
		String letters = map[digits.charAt(offset)-'0'];
		for(int i = 0; i<letters.length();i++) {
			// choose
			char c = letters.charAt(i);
			// explore
			letterCombinationsHelper(digits, offset+1, sofar+c, map, result); // implicit backtracking
		}
	}
	
	public static void main(String[] args) {
		String digits = "23";
//		List<String> result = letterCombinations(digits);
		List<String> result = SolutionDebug.letterCombinations(digits);
		System.out.println("Total combinations for digits="+digits+" are "+result.size());
		System.out.println(result);
		
		result.clear();
		result = IterativeSolution.letterCombinations(digits);
		

	}

}
/**
https://leetcode.com/problems/letter-combinations-of-a-phone-number/discuss/164315/Java-using-an-integer-counter-for-premutations-beats-100
 * @author shahzadmughal8410
 *
 */
class IterativeSolution {
    public static List<String> letterCombinations(String digits) {
        char[][] mapping = new char[][] {
            {'a', 'b', 'c'}, // 2
            {'d', 'e', 'f'}, // 3
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'} // 9
        };
        char[] chars = digits.toCharArray();
        int length = chars.length;
        if (length == 0) return Collections.emptyList();
        char[][] charmap = new char[length][];
        int numCombinations = 1;
        for(int i = 0; i < length; i++) {
            char[] choice = mapping[chars[i] - '2']; // '2' is 0 index
            charmap[i] = choice;
            numCombinations *= choice.length;
        }
        List<String> result = new ArrayList<>(numCombinations);
        char[] letters = new char[length];
        for(int c = 0; c < numCombinations; c++) {
            int combination = c;
            for(int i = 0; i < length; i++) {
                char[] choice = charmap[i];
                letters[i] = choice[combination % choice.length];
                combination /= choice.length;
            }
            result.add(new String(letters));
        }
        return result;
    }
}

/**
https://www.jianshu.com/p/34477e310b15
 * @author shahzadmughal8410
 *
 */
class SolutionStack {
	public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl",
                "mno", "pqrs", "tuv", "wxyz" };
        ans.add("");
        for (int i = 0; i < digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            while (ans.peek().length() == i) {
                String t = ans.remove();
                for (char s : mapping[x].toCharArray())
                    ans.add(t + s);
            }
        }
        return ans;
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
