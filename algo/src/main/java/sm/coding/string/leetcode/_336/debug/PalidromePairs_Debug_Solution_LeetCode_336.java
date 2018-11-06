/**
 * 
 */
package sm.coding.string.leetcode._336.debug;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class PalidromePairs_Debug_Solution_LeetCode_336 {

	/**
	 * 
Case:1                  
s1=hhxx
s2=hh  

First reverse:  xxhh (s1 reverse)
				hh   (s2 original) - - not palindrome as its not matching from start

Second reverse: hh   (s2 reverse)
				hhxx (s1 original) - if rest of the original string is palindrome then its palindrome                    								
Case:2                  
s1=lls
s2=s  

First reverse:  sll (s1 reverse) - if rest of the original is palindrome then its palindrome
				s   (s2 original)

Second reverse: s   (s2 reverse)
				lls (s1 original) - not palindrome as its not matching from start   
				
				 
for input "aaa", "ba", "a"                  
root (-1,[0,2])
            | 'a'
          n1 (2,[0,1,2])
    ---------------------
'b' |                 | 'a'
  n2 (1,[1])    n3 (-1,[0])
                      | 'a'
                 n4 (0,[0])         
				 
	 * @author shahzadmughal8410
	 *
	 */
	private static class TrieNode {
	    TrieNode[] next;
	    int index;
	    List<Integer> list;
	    	
	    TrieNode() {
	    	next = new TrieNode[26];
	    	index = -1;
	    	list = new ArrayList<>();
	    }
	}
	    
	public static List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> res = new ArrayList<>();

		TrieNode root = new TrieNode();
		for (int i = 0; i < words.length; i++) {
			addWord(root, words[i], i);
		}
		for (int i = 0; i < words.length; i++) {
			search(words, i, root, res);
		}

		return res;
	}
	    
	private static void addWord(TrieNode root, String word, int index) {
		TrieNode current = root;
		for (int i = word.length() - 1; i >= 0; i--) {
			int j = word.charAt(i) - 'a';
			if (current.next[j] == null) {
				current.next[j] = new TrieNode();
			}
			if (isPalindrome(word, 0, i)) {
				current.list.add(index);
			}
			current = current.next[j];
		}

		current.list.add(index);
		current.index = index;
	}
	    
	private static void search(String[] words, int i, TrieNode root, List<List<Integer>> res) {
		TrieNode current = root;
		String word = words[i];
		for (int j = 0; j < word.length(); j++) {
			//case 1, Second reverse, scenario
			if (current.index >= 0 && current.index != i && isPalindrome(word, j, word.length() - 1)) {
				res.add(Arrays.asList(i, current.index));
				System.out.println("Palindrome pair 1="+words[i]+","+words[current.index]);
			}

			current = current.next[word.charAt(j) - 'a'];
			if (current == null)
				return;
		}
		//Case 2, first reverse, scenario
		for (int j : current.list) {
			if (i == j) {
				continue;
			}
			res.add(Arrays.asList(i, j));
			System.out.println("Palindrome pair 2="+words[i]+","+words[j]);
		}
	}
 
	private static boolean isPalindrome(String word, int i, int j) {
//		System.out.printf("Palindrome=%s, %d, %d, %s =", word, i, j,word.substring(i,j+1));
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--)) {
//				System.out.println("false");
				return false;
			}
	    }
//		System.out.println("true");
	    return true;
	}


	
	public static void main(String[] args) {
		String[] words = new String[] {"abcd", "dcba", "lls", "s", "sssll", "hhxx", "hh", "nnn", "n"};
//		String[] words = new String[] {"aaa", "ba", "a" };
//		String[] words = new String[] {"hhxx", "hh"};
//		String[] words = new String[] {"ba", "a" };
//		String[] words = new String[] {"lls", "s" };
//		String[] words = new String[] {"aa", "" };
//		String[] words = new String[] {"abc"};
//		String[] words = new String[] {"lls","s"};
		
		List<List<Integer>> result = palindromePairs(words);
		System.out.println("result="+result);
	}

}

