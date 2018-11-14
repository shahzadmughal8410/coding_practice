/**
 * 
 */
package sm.coding.string.leetcode._345;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class ReverseVowelsOfAString_345_Str_2P {

	/**
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

Note:
The vowels does not include the letter "y". 

 https://leetcode.com/problems/reverse-vowels-of-a-string/description/
 https://www.geeksforgeeks.org/reverse-vowels-given-string/

 Submission
https://leetcode.com/submissions/detail/189468756/
You are here! 
Your runtime beats 33.72 % of java submissions. 
	 * @param args
	 */
	public static String reverseVowels(String s) {
		if(null==s || s.length()==0)
			return s;
		
		int start = 0;
		int end = s.length()-1;
		Set<Character> vowels = new HashSet<>();
		Collections.addAll(vowels, 'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
		char[] array = s.toCharArray();
		
		while(start < end) {
			
			if( ! vowels.contains(s.charAt(start))) {
				start++;
			}else if( ! vowels.contains(s.charAt(end))){
				end--;
			}else {
				array[start] = s.charAt(end);
				array[end] = s.charAt(start);
				start++;
				end--;
			}			
		}
		
		return new String(array);
	}
	
	public static void main(String[] args) {
		String s1 = "hello" ;
		String s2 = "leetcode" ;
		
		testEquals("Reverse of ["+s1+"] is", "holle", reverseVowels(s1));
		testEquals("Reverse of ["+s2+"] is", "leotcede ", reverseVowels(s2));

	}
	
	public static void testEquals(String msg, String s1, String s2) {
		if(s1.equals(s2)) {
			System.out.printf("Passed: %s [%s]=[%s] %n", msg, s1, s2);
		}else {
			System.err.printf("Failed: %s [%s]!=[%s] %n", msg, s1, s2);
		}
	}
}
