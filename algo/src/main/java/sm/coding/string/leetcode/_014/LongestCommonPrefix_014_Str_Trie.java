/**
 * 
 */
package sm.coding.string.leetcode._014;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestCommonPrefix_014_Str_Trie {

	/**
Write a function to find the longest common prefix string amongst an array of strings.

https://leetcode.com/problems/longest-common-prefix/description/

https://leetcode.com/problems/longest-common-prefix/solution/
https://www.geeksforgeeks.org/longest-common-prefix-set-1-word-by-word-matching/
https://www.geeksforgeeks.org/longest-common-prefix-set-2-character-by-character-matching/
https://www.geeksforgeeks.org/longest-common-prefix-set-3-divide-and-conquer/
https://www.geeksforgeeks.org/longest-common-prefix-set-4-binary-search/
https://www.geeksforgeeks.org/longest-common-prefix-set-5-using-trie/
 
Submission
https://leetcode.com/submissions/detail/169853147/
You are here!
Your runtime beats 12.81 % of java submissions.
 
	 * @param args
	 */
	public static String longestCommonPrefix(String[] words) {
		if(null==words || words.length==0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		TrieNode root = buildTrie(words);
		TrieNode current = root;
		
		while(current.childs.size()==1 && !current.isWord) {
			for(Map.Entry<Character, TrieNode> entry:current.childs.entrySet()) {
				sb.append(entry.getKey());
				current = entry.getValue();
			}
		}
		return sb.toString();
	}
	
	public static TrieNode buildTrie(String[] words) {
		TrieNode root = new TrieNode();
		for(String s:words) {
			TrieNode current = root;
			for(int i=0; i<s.length();i++) {
				char c = s.charAt(i);
				TrieNode child = current.childs.get(c);
				if(null==child) {
					child = new TrieNode();
					current.childs.put(c, child);
				}
				current = child;
			}
			current.isWord=true;
		}
		return root;
	}
	
	public static void main(String[] args) {
		String words[] = {"geeksforgeeks", "geeks","geek", "geezer"};
//		String words[] = {"geeksforgeeks", "geeks","geek", "geezer", "ge"};
//		String words[] = {"geeksforgeeks", "geeks","geek", "geezer", "x"};
		System.out.println("Prefix="+longestCommonPrefix(words));

	}

}

class TrieNode{
	Map<Character, TrieNode> childs;
	boolean isWord;
	
	public TrieNode() {
		childs = new HashMap<>();
	}
}

