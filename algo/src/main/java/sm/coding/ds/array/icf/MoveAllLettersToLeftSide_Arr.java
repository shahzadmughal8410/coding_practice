/**
 * 
 */
package sm.coding.ds.array.icf;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MoveAllLettersToLeftSide_Arr {

	/**
You're given a character array, which may contain alphabet letters (a to z or A to
Z) as well as numbers (0 to 9, represented as characters), in random order. You
have to make alphabet letters appear on left side, inside the same array.
e.g. If your input is [0,a,1,9,3,z,b,r,6], then in your output, letters a, z, b, and r,
should be seen on left side in the array.
* Original order of letters needs to be preserved.
* Order of numbers doesn't need to be preserved. If a test-case fails, then check if
it's only because the order of numbers is different. If so, then the failure is
irrelevant and can be ignored.
* Repeats are allowed.
* An in-place linear solution is expected
* For languages that have immutable strings, convert the input string into a
Character Array and work in-place on that array. Convert it back to the string
before returning. (For the purpose of this problem, ignore the extra linear space
used in that conversion, as long as you're only using constant space after
conversion to character array)
* Extension: Minimize the number of array-writes in your solution. i.e. Re-read the
problem-statement, and think whether you really need to write/over-write that
letter/number.
Solution: Adapt slightly from this: http://www.geeksforgeeks.org/move-zeroes-end-array/
(there are other ways, but all are very similar. Idea is to do it in linear time)
	 * 
	 * 
inspired by 
https://leetcode.com/problems/move-zeroes/solution/ 

	 * 
	 * Following implementation is T=O(n) & S=O(1)
	 * @param args
	 */
	public static void move(char[] chars) {
		int wi = 0;
		for(int ri=0; ri<chars.length; ri++) {
			//non-zero value found
		if (Character.isLetter(chars[ri]) ) {
			//swap with wi value
			char tmp = chars[wi];
			chars[wi] = chars[ri];
			chars[ri] = tmp;
			wi++;
			}
		}
	}
	
	public static boolean isLetter(char c) {
		return c-'a'>=0 && c-'a'<26 ;
	}
	
	public static boolean isDigit(char c) {
		return c-'0'>=0 && c-'0'<10 ;
	}
	
	public static void swap(char[] arr, int digit, int letter) {
		char tmp = arr[digit];
		arr[digit] = arr[letter];
		arr[letter] = tmp;
	}	
	
	public static void main(String[] args) {
		char[] arr = new char[] {'0','a','1','9','3','z','b','r','6'};
		System.out.println(new String(arr));
		SolutionDebug.move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'0','3','1','9','3','z','b','r','x'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'z','b','r','x','0','3','1','9','3'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'z','b','r','x'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'z','b','r','x','1'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'1','z','b','r','x'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();


		arr = new char[] {'0','3','1','9','3'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'0','3','1','9','3','x'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

		arr = new char[] {'b','0','3','1','9','3'};
		System.out.println(new String(arr));
		move(arr);
		System.out.println(new String(arr));
		System.out.println();

	}

}

class SolutionDebug{

	static StringBuilder format = new StringBuilder();

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
		debugRow(cols);
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}
	
	public static void move(char[] chars) {
		char[] actual = Arrays.copyOf(chars, chars.length); //used for debugging
		
		tableColumns("wi", "ri", "isLetter", "arr:15");
		int wi = 0;
		for(int ri=0; ri<chars.length; ri++) {
			//non-zero value found
			debugRow(wi, ri, Character.isLetter(chars[ri]), new String(chars));
		if (Character.isLetter(chars[ri]) ) {
			//swap with wi value
			char tmp = chars[wi];
			chars[wi] = chars[ri];
			chars[ri] = tmp;
			wi++;
			}
		}
		debug(" actual:"+new String(actual));
		debug("updated:"+new String(chars));
	}
	
}

