/**
 * 
 */
package sm.coding.string.leetcode._336;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class PalindromePairs_336_Str_Trie {

	/**
Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
Example 1:
Given words = ["bat", "tab", "cat"]
Return [[0, 1], [1, 0]]
The palindromes are ["battab", "tabbat"]
Example 2:
Given words = ["abcd", "dcba", "lls", "s", "sssll"]
Return [[0, 1], [1, 0], [3, 2], [2, 4]]
The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

Links
https://www.geeksforgeeks.org/palindrome-pair-in-an-array-of-words-or-strings/ 

https://leetcode.com/problems/palindrome-pairs/discuss/79254/Java-naive-154-ms-O(nk2-+-r)-and-126-ms-O(nk-+-r)-Manacher-+-suffixesprefixes
http://buttercola.blogspot.com/2016/06/leetcode-336-palindrome-pairs.html 
https://leetcode.com/problems/palindrome-pairs/discuss/79254/Java-naive-154-ms-O(nk2-+-r)-and-126-ms-O(nk-+-r)-Manacher-+-suffixesprefixes 
 

 Input = "aaa", "ba", "a" 
 
 Trie
 
root (-1,[0,2])
            | 'a'
          n1 (2,[0,1,2])
    ---------------------
'b' |                 | 'a'
  n2 (1,[1])    n3 (-1,[0])
                      | 'a'
                 n4 (0,[0])         
                 
                 
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
 
	 * @param args
	 */
	public static List<List<Integer>> palindromePairs(String[] words){
		TrieNode root = new TrieNode();
		for(int i=0; i<words.length; i++) {
			add(words[i], i, root);
		}
		List<List<Integer>> result = new ArrayList<>();
		for(int i = 0; i<words.length;i++) {
			search(words[i], i, result , root, words);
		}
		return result;
	}
	
	public static void add(String word, int index, TrieNode root) {
		if(null==word || word.length()==0 || null==root) {
			return;
		}
		TrieNode current = root;
		// create trie in word's reverse order
		for(int i =word.length()-1; i>=0;i--) {
			int childIndex = word.charAt(i) - 'a';
			TrieNode child = current.childs[childIndex];
			if(null==child) {
				child = new TrieNode();
				current.childs[childIndex] = child;
			}
			if(isPalindrome(word, 0, i)) { 
				// current length 0 to length-1 is palindrome
				// than set at parent node that rest of the bottom trie 
				// for this word is palindrome
				current.list.add(index);
			}
			current = child;
		}
		current.index = index;
		current.list.add(index);
	}
	
	public static void search(String word, int index, List<List<Integer>> result, TrieNode root, String[] words) {
		TrieNode current = root;
		for(int i=0; i<word.length(); i++) {
			// when trie is exhausted and word is remaining i.e. word.length>trie height
			if(current.index>=0 && current.index!=index && isPalindrome(word, i, word.length()-1)) {
				result.add(Arrays.asList(index, current.index));
			}
			int childIndex = word.charAt(i) - 'a';
			TrieNode child = current.childs[childIndex];
			if(null==child) {
				return;
			}
			// if chils is not null means word characters are matching so far
			current = child;
		}
		//when word is exhausted and trie is remaining i.e. trie height>word.length
		for(int i:current.list) {
			if(i==index) {
				continue;
			}
			result.add(Arrays.asList(index, i));
		}
	}
	
	public static boolean isPalindrome(String s, int i , int j) {
		while(i<j) {
			if(s.charAt(i++)!=s.charAt(j--)) {
				return false;
			}
		}
		return true;
	}
	

	public static void main(String[] args) {
		String[] words = new String[] {"abcd", "dcba", "lls", "s", "sssll", "hhxx", "hh", "nnn", "n"};
//		String[] words = new String[] {"aaa", "ba", "a" };
//		String[] words = new String[] {"hhxx", "hh"};
//		String[] words = new String[] {"ba", "a" };
//		String[] words = new String[] {"lls", "s" };
		
		List<List<Integer>> result = palindromePairs(words);
		System.out.println("result="+result);
	}

}

class TrieNode {
    TrieNode[] childs = new TrieNode[26];
    int index = -1;
    List<Integer> list = new ArrayList<>();
}

