/**
 * 
 */
package sm.coding.ds.trie.leetcode._208.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smughal
 *
 */
public class ImplementTrie_PrefixTree_208 {

	/**
	 * 
Implement a trie with insert, search, and startsWith methods.

Note:
You may assume that all inputs are consist of lowercase letters a-z.

 https://www.geeksforgeeks.org/trie-insert-and-search/
 https://www.geeksforgeeks.org/trie-display-content/
 https://www.geeksforgeeks.org/trie-delete/
 https://leetcode.com/problems/implement-trie-prefix-tree/solution/
 https://github.com/mission-peace/interview/blob/master/src/com/interview/suffixprefix/Trie.java

Submission
https://leetcode.com/submissions/detail/190038750/
You are here! 
Your runtime beats 34.60 % of java submissions. 
 
	 * @param args
	 */
	public static void main(String[] args) {
		// Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                         "by", "bye", "their"};
      
        String output[] = {"Not present in trie", "Present in trie"};
      
      
        Trie trie = new Trie();
      
        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);
      
        // Search for different keys
        if(trie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);
         
        if(trie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);
         
        if(trie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);
         
        if(trie.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);

        if(trie.startsWith("an") == true)
            System.out.println("prefix an --- " + output[1]);
        else System.out.println("prefix an --- " + output[0]);

        if(trie.startsWith("xy") == true)
            System.out.println("prefix xy --- " + output[1]);
        else System.out.println("prefix xy --- " + output[0]);

	}

}

class Trie {
	TrieNode root ;
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	public void insert(String word) {
		if(null==word || word.length()==0) {
			return;
		}
		
		TrieNode current = root;
		for(char c:word.toCharArray()) {
			TrieNode next = current.childs.get(c);
			if(null==next) {
				next = new TrieNode();
				current.childs.put(c, next);
			}
			current = next;
		}
		current.isEnd = true;
	}
	
	public boolean search(String word) {
		if(null==word || word.length()==0) {
			return false;
		}
		TrieNode current = root;
		for(char c:word.toCharArray()) {
			TrieNode child = current.childs.get(c);
			if(null==child) {
				return false;
			}
			current = child;
		}
		return current.isEnd;
	}

	public boolean startsWith(String prefix) {
		if(null==prefix || prefix.length()==0) {
			return false;
		}
		TrieNode current = root;
		for(char c:prefix.toCharArray()) {
			TrieNode child = current.childs.get(c);
			if(null==child) {
				return false;
			}
			current = child;
		}
		return true;
	}

}

class TrieNode {
	Map<Character, TrieNode> childs;
	boolean isEnd;
	
	public TrieNode () {
		this.childs = new HashMap<>();
	}
}

