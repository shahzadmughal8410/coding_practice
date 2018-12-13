/**
 * 
 */
package sm.coding.string.leetcode._003;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestSubstringWithoutRepeatingCharacters_003_Str_2P {

	/**
Given a string, find the length of the longest substring without repeating characters.

Examples:

Given "abcabcbb", the answer is "abc", which the length is 3.

Given "bbbbb", the answer is "b", with the length of 1.

Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.

https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/

Submission
https://leetcode.com/submissions/detail/194882752/
You are here! 
Your runtime beats 92.26 % of java submissions.
	 * 
	 * @param args
	 */
	public static int lengthOfLongestSubstring(String s) {
		if(null==s || s.length()==0) {
			return 0;
		}
		int maxLength = 0;
		int start = 0;
		int startIndex = -1;
		Map<Character, Integer> map = new HashMap<>();
		for(int end =0; end<s.length(); end++) {
			char c = s.charAt(end);
			if(map.containsKey(c)) {
				start = Math.max(map.get(c), start);
			}

			map.put(c,end+1);
			
			int currentLength = end-start+1;
			if(maxLength < currentLength) {
				maxLength = currentLength;
				startIndex = start;
			}
		}
		System.out.println(s.substring(startIndex, startIndex+maxLength));
		return maxLength;
	}
	
	
	public static void main(String[] args) {
		String s = "abcabcbb";
		System.out.println("Max length non repeating characters="+lengthOfLongestSubstring(s));
//		System.out.println("Max length non repeating characters="+SolutionDebug.findLengthLongestSubstringNoRepeatingCharacters(s));

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
	
	public static int findLengthLongestSubstringNoRepeatingCharacters(String s) {
		if(null==s || s.length()==0) {
			return 0;
		}
		int maxLength = 0;
		Map<Character, Integer> map = new HashMap<>();
		tableColumns("start", "end", "maxLength", "map:10");
		for(int start = 0, end =0; end<s.length(); end++) {
			debugRow(start, end, maxLength, map);
			if(map.containsKey(s.charAt(end))) {
				start = Math.max(map.get(s.charAt(end)), start);
			}
			maxLength = Math.max(maxLength, end-start+1);
			map.put(s.charAt(end),end+1);
		}
		return maxLength;
	}
	
}
