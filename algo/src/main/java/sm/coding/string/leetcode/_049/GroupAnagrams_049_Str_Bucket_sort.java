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
public class GroupAnagrams_049_Str_Bucket_sort {

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

Submission
https://leetcode.com/submissions/detail/197301742/
You are here! 
Your runtime beats 96.89 % of java submissions.

	 * @param args
	 */
	public static List<List<String>> groupAnagrams_BruteForce(String[] strs){
		if(null==strs || strs.length==0) {
			return null;
		}		
		Map<String, List<String>> m = new LinkedHashMap<>();// to maintain the order in which keys are added
		for(String s:strs) {
			char[] chars = s.toCharArray();
			
//			Arrays.sort(chars);// T=O(n log n), anagrams will result in same string(key) after sorting
//			String key = new String(chars);

			String key = bucketSort(s); // T=O(n) 

			List<String> l = m.get(key);
			if(null==l) {
				l = new ArrayList<>();
				m.put(key, l);
			}
			l.add(s);
		}		
		return new ArrayList<>(m.values());
	}
	
	// sorts string in T=O(n)
	public static String bucketSort(String s) {
		int[] map = new int[26];
		// build the frequency map
		for(int i=0; i<s.length();i++) {
			char c = s.charAt(i);
			int index = c -'a';
			map[index]++;
		}
		
		StringBuilder sb = new StringBuilder();
		// build the sorted string
		for(int i=0; i<map.length; i++) {
			int frequency = map[i];
			for(int j = 0; j<frequency; j++) {
				char c = (char) (i+'a');
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
		
		groupAnagrams_BruteForce(strs).forEach(c->System.out.println(c));
	}

}
