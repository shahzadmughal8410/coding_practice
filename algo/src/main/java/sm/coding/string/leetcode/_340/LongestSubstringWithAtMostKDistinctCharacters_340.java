/**
 * 
 */
package sm.coding.string.leetcode._340;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestSubstringWithAtMostKDistinctCharacters_340 {

	
	/**
Given a string, find the length of the longest substring T that contains at most k distinct characters.

Example 1:

Input: s = "eceba", k = 2
Output: 3
Explanation: T is "ece" which its length is 3.
Example 2:

Input: s = "aa", k = 1
Output: 2
Explanation: T is "aa" which its length is 2.

Submission
https://leetcode.com/submissions/detail/191032441/
You are here! 
Your runtime beats 48.11 % of java submissions.
	 */
	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
		if(null==s || s.length()==0 || k<=0) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<>();
		int maxLength = 0;
		int start = 0;
		int startIndex = -1; // needed to return the string
		
		for(int end=0;end<s.length();end++) {
			char c = s.charAt(end);
			map.put(c, map.getOrDefault(c, 0)+1);
			
			while(map.size()>k) {
				char startChar = s.charAt(start);
				// decrement the count
				map.put(startChar, map.get(startChar)-1);
				if(map.get(startChar)==0) {
					map.remove(startChar);
				}
				++start;
			}
			int currengthLength = end-start+1;
			// update max length 
			if(maxLength<currengthLength) {
				maxLength = currengthLength;
				startIndex = start;
			}
			
		}
		// to return the string 
		System.out.println(s.substring(startIndex, startIndex+maxLength));
		return maxLength;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "eceba";
		int k = 2;
		int maxLength = lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(s+", k="+k+", maxLength="+maxLength);

		s = "aa";
		k = 1;
		maxLength = lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(s+", k="+k+", maxLength="+maxLength);

		s = "XXXXXXXXXaabcdefghibbdddccbbbgggggghhhhhhh";
		k = 1;
		maxLength = lengthOfLongestSubstringKDistinct(s, k);
		System.out.println(s+", k="+k+", maxLength="+maxLength);

	}

}
