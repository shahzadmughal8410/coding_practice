/**
 * 
 */
package sm.coding.string.leetcode._344;

/**
 * @author smughal
 *
 */
public class ReverseString_344_Str_2P {

	/**
Write a function that takes a string as input and returns the string reversed.

Example:
Given s = "hello", return "olleh".

https://leetcode.com/problems/reverse-string/description/

https://www.geeksforgeeks.org/write-a-program-to-reverse-an-array-or-string/
	 * 
	 * Calarifying questions:
	 * 1- Does string has control characters i.e. \n?
	 * 2- String fits in memory?
	 * 
	 * Brute force:
	 * Loop through string in reverse order.
	 * 
	 * Optimal Solution:
	 * Use two pointers start,end loop through 0-mid of string swap start/end. 
	 * Use array to replace it in-place.
	 * 
	 * T=O(n)
	 * S=O(n)

Submission
https://leetcode.com/submissions/detail/194873231/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 * @param args
	 */
	public static String reverseString(String s) {
		if(null==s || s.length()==0) {
			return s;
		}		
		char[] arr = s.toCharArray();
		int start = 0;
		int end = s.length()-1;
		
		while(start<end) {
			swap(arr, start++,end--);
		}		
		return new String(arr);		
	}
	
	public static void swap(char[] arr, int a, int b) {
		char tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
	
	public static void main(String[] args) {
		String input = "12345";
		String reverse = reverseString(input);
		System.out.println(input);
		System.out.println(reverse);

		input = "abcd";
		reverse = reverseString(input);
		System.out.println(input);
		System.out.println(reverse);
}

}
