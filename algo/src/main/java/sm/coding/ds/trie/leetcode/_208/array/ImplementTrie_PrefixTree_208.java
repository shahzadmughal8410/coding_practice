/**
 * 
 */
package sm.coding.ds.trie.leetcode._208.array;

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
            trie.add(keys[i]);
      
        // Search for different keys
        if(trie.wordExists("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);
         
        if(trie.wordExists("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);
         
        if(trie.wordExists("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);
         
        if(trie.wordExists("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);

        if(trie.prefixExists("an") == true)
            System.out.println("prefix an --- " + output[1]);
        else System.out.println("prefix an --- " + output[0]);

        if(trie.prefixExists("xy") == true)
            System.out.println("prefix xy --- " + output[1]);
        else System.out.println("prefix xy --- " + output[0]);

	}

}

class Trie {
	TrieNode root ;
	
	public Trie() {
		this.root = new TrieNode();
	}
	
	public void add(String word) {
		if(null==word || word.length()==0) {
			return;
		}
		
		TrieNode current = root;
		for(char c:word.toCharArray()) {
			TrieNode next = current.childs[c-'a'];
			if(null==next) {
				next = new TrieNode();
				current.childs[c-'a'] = next;
			}
			current = next;
		}
		current.isEnd = true;
	}
	
	public boolean wordExists(String word) {
		if(null==word || word.length()==0) {
			return false;
		}
		TrieNode current = root;
		for(char c:word.toCharArray()) {
			TrieNode child = current.childs[c-'a'];
			if(null==child) {
				return false;
			}
			current = child;
		}
		return current.isEnd;
	}

	public boolean prefixExists(String prefix) {
		if(null==prefix || prefix.length()==0) {
			return false;
		}
		TrieNode current = root;
		for(char c:prefix.toCharArray()) {
			TrieNode child = current.childs[c-'a'];
			if(null==child) {
				return false;
			}
			current = child;
		}
		return true;
	}

}

class TrieNode {
	TrieNode[] childs;
	boolean isEnd;
	
	public TrieNode () {
		this.childs = new TrieNode[26];
	}
}

