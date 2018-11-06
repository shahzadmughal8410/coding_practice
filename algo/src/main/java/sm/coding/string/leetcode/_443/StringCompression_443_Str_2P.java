/**
 * 
 */
package sm.coding.string.leetcode._443;

import java.util.Arrays;

/**
 * @author smughal
 *
 */
public class StringCompression_443_Str_2P {

	/**
	 * 
Given an array of characters, compress it in-place.

The length after compression must always be smaller than or equal to the original array.

Every element of the array should be a character (not int) of length 1.

After you are done modifying the input array in-place, return the new length of the array.


Follow up:
Could you solve it using only O(1) extra space?


Example 1:
Input:
["a","a","b","b","c","c","c"]

Output:
Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]

Explanation:
"aa" is replaced by "a2". "bb" is replaced by "b2". "ccc" is replaced by "c3".
Example 2:
Input:
["a"]

Output:
Return 1, and the first 1 characters of the input array should be: ["a"]

Explanation:
Nothing is replaced.
Example 3:
Input:
["a","b","b","b","b","b","b","b","b","b","b","b","b"]

Output:
Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].

Explanation:
Since the character "a" does not repeat, it is not compressed. "bbbbbbbbbbbb" is replaced by "b12".
Notice each digit has it's own entry in the array.
Note:
All characters have an ASCII value in [35, 126].
1 <= len(chars) <= 1000. 

Submission
https://leetcode.com/submissions/detail/172293828/
You are here!
Your runtime beats 77.15 % of java submissions.

	 * @param args
	 */
	public static int compress(char[] input) {
		if(null==input || input.length==0) {
			return 0;
		}
		
		int readIndex=0;
		int writeIndex = 0;
		while(readIndex<input.length) {	
			char current = input[readIndex];
			int count = 1;
			
			while(readIndex+1<input.length && input[readIndex]==input[readIndex+1]) {
				++readIndex;
				++count;
			}
			
			input[writeIndex++] = current;
			if(count>1) {
				for(char d:Integer.toString(count).toCharArray()) { // String.valueOf(count) can be used as well
					input[writeIndex++] = d;
				}
			}
			++readIndex;
		}
		return writeIndex;
	}
	
	
	public static void main(String[] args) {
		char[] input = new char[] {'a','a','b','b','c','c','c'};
//		char[] input = new char[] {'a'};
//		char[] input = new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'};
		System.out.println(Arrays.toString(input));
		System.out.println("Length="+compress(input));
		System.out.println(Arrays.toString(input));
		
	}

}
