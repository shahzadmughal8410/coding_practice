/**
 * 
 */
package sm.coding.string.leetcode._049;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class GroupAnagrams_049_Str {

	/**
Given an array of strings, group anagrams together.

For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
Return:

[
  ["ate", "eat","tea"],
  ["nat","tan"],
  ["bat"]
]
Note: All inputs will be in lower-case.

https://leetcode.com/problems/group-anagrams/description/
https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together/
https://www.geeksforgeeks.org/given-a-sequence-of-words-print-all-anagrams-together-set-2/
 
	 * @param args
	 */
	public static List<List<String>> groupAnagrams(String[] strs){
		if(null==strs || strs.length==0) {
			return null;
		}
		
		Map<String, List<String>> m = new LinkedHashMap<>();
		for(String s:strs) {
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String key = new String(chars);
			List<String> l = m.get(key);
			if(null==l) {
				l = new ArrayList<>();
				m.put(key, l);
			}
			l.add(s);
		}
		
		return new ArrayList<>(m.values());
	}
	
	public static void main(String[] args) {
		String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
		
		groupAnagrams(strs).forEach(c->System.out.println(c));
	}

}
