/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.q3;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class WildCardBinary_Q3_Recr_BackTrk {

	
	/**
Input: 10?
Output: 101, 100
i.e. ? behaves like a wild-card. There are two possibilities for 10?, when that ? is replaced with
either 0 or 1.
Input: 1?0?
Output: 1000, 1001, 1100, 1101
Please write a program that takes given strings as input and produces the suggested output.
Suggested time: 20 minutes.

Links
https://docs.google.com/document/d/1EL3jCB_M844qkMQPJgkjZhS6KMPd4EFmIisCPzIr0Zw/edit#heading=h.k7c9pl4iy2v8

	 */
	public static List<String> wildCard(String input){
		List<String> result = new ArrayList<>();
		wildCardHelper(input, "", 0, result);
		return result;
	}
		
	public static void wildCardHelper(String input, String sofar, int index, List<String> result) {
		// base case, if nothing to choose, then return
		if(index == input.length()) {
			result.add(sofar);
			return;
		}
		
		//recursive case
		if(input.charAt(index) == '?') {
			// use choices of 0 and 1, call one once with 0 and once with 1
			wildCardHelper(input, sofar+0, index+1, result); // implicit backtracking
			wildCardHelper(input, sofar+1, index+1, result); // implicit backtracking
		}else {
			// if input is not the ? then add it in sofar and recurse
			wildCardHelper(input, sofar+input.charAt(index), index+1, result);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String input;
		List<String> result;
		
		input = "10?";
		result = wildCard(input);
		System.out.printf("Possibilities for input %s are %s %n", input, result);

		input = "1?0?";
		result = wildCard(input);
		System.out.printf("Possibilities for input %s are %s %n", input, result);

		input = "???";
		result = wildCard(input);
		System.out.printf("Possibilities for input %s are %s %n", input, result);

	}

}
