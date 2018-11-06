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
 
	 * @param args
	 */
	public static int firstUniqueCharacterIndex(String s) {
		if(null==s || s.length()==0) {
			return -1;
		}
		Map<Character, FirstIndex> m = new LinkedHashMap<>();
		for(int i = 0; i<s.length();i++) {
			char key = s.charAt(i);
			FirstIndex f = m.computeIfAbsent(key, K -> {return new FirstIndex();}  );
			/* above line is equal to following code
			FirstIndex f = null;
			if(m.containsKey(key)) {
				f = m.get(key);
			}else {
				f = new FirstIndex();
			}
			*/
			
			f.count++;
			if(f.firstIndex==0) {
				f.firstIndex = i;
			}
			m.put(key,f);
		}
		// loop through the keys
		// keys are in insertion order in linked hashmap so the first character with count 1 
		// in first chartacter in string as well
		for(Map.Entry<Character, FirstIndex> entry : m.entrySet()) {
			if(entry.getValue().count==1) {
				return entry.getValue().firstIndex; 
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		String s = "loveleetcode";
		System.out.println("Firest unique charecter index is="+firstUniqueCharacterIndex(s));

	}

}


class FirstIndex {
	int count; 
	int firstIndex;
}