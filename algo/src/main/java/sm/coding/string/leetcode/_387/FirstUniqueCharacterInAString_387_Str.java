/**
 * 
 */
package sm.coding.string.leetcode._387;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class FirstUniqueCharacterInAString_387_Str {

	/**
Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.

Examples:

s = "leetcode"
return 0.

s = "loveleetcode",
return 2.
Note: You may assume the string contain only lowercase letters.

https://leetcode.com/problems/first-unique-character-in-a-string/description/
https://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/

Submission
https://leetcode.com/submissions/detail/194871465/
You are here! 
Your runtime beats 34.41 % of java submissions. 
	 * @param args
	 */
	public static int firstUniqChar(String s) {
		if(null==s || s.length()==0) {
			return -1;
		}
		Map<Character, Integer> m = new LinkedHashMap<>();
		for(int i = 0; i<s.length();i++) {
			char c = s.charAt(i);
			m.put(c, m.getOrDefault(c, 0)+1);
		}
		// loop through the keys
		// keys are in insertion order in linked hashmap so the first character with count 1 
		// in first chartacter in string as well
		for(int i=0; i<s.length();i++) {
			if(m.get(s.charAt(i))==1) {
				return i; 
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String s = "loveleetcode";
		System.out.println("Firest unique charecter index is="+firstUniqChar(s));

	}

}
