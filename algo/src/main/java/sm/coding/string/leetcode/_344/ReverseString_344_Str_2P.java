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
	 * 
	 * @param args
	 */
	public static String reverse(String input) {
		if(null==input || input.length()==0) {
			return input;
		}		
		char[] arr = input.toCharArray();
		int mid = input.length()/2;
		for(int i =0, j=input.length()-1;i<mid;i++,j--) {
			char tmp = arr[i];
			arr[i] = arr[j];
			arr[j] = tmp;
		}		
		return new String(arr);		
	}
	
	
	public static void main(String[] args) {
		String input = "12345";
		String reverse = reverse(input);
		System.out.println(input);
		System.out.println(reverse);
	}

}
