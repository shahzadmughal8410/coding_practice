/**
 * 
 */
package sm.coding.ds.graph.leetcode._127;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class WordLadder_127_BFS_Graph {
	
	/**
Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output: 5

Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: 0

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

Submission
https://leetcode.com/submissions/detail/193591558/
You are here! 
Your runtime beats 11.82 % of java submissions.

	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
 		if(!wordList.contains(endWord)) {
			return 0;
		}
		Set<String> visited = new HashSet<>();
		Deque<String> q = new LinkedList<>();
		
		q.offer(beginWord);
		visited.add(beginWord);
		
		int transform = 0;
		
		while(!q.isEmpty()) {    			
			++transform; // increment for each level
			// exhaust each level
			int size = q.size();
			for(int i=0; i < size; i++) {
	    			String current = q.poll();
        			if(current.equals(endWord)) {
        				return transform;
        			}
	
	    			for(String next:wordList) {
						if(!visited.contains(next)) {
							if(isNext(current, next)) {
	    						visited.add(next);
	    						q.offer(next);
	    					}
	    				}
	    			}
			}
		}
		return 0;
    }
    
    public static boolean isNext(String a, String b) {
		int diff = 0;
		for(int i =0; i<a.length(); i++) {
			if(a.charAt(i)!=b.charAt(i)) {
				++diff;
			}
		}    		
		return diff==1;
    }	
	
    /**
Following implementation is good if we have Set instead of list for wordDictionary
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return

https://leetcode.com/submissions/detail/193621377/
You are here! 
Your runtime beats 62.65 % of java submissions.
     */
    public static int ladderLength_Set_WordList(String beginWord, String endWord, List<String> list) {             
    	Set<String> wordList = new HashSet<>(list); 
    if(!wordList.contains(endWord)) {
    			return 0;
    		}
    		Set<String> visited = new HashSet<>();
    		Deque<String> q = new LinkedList<>();
    		
    		
    		q.offer(beginWord);
    		visited.add(beginWord);
    		
    		int transform = 0;
    		
    		while(!q.isEmpty()) {    			
    			++transform; // increment for each level
    			// exhaust each level
    			int size = q.size();
    			for(int i=0; i < size; i++) {
        			String current = q.poll();
        			if(current.equals(endWord)) {
        				return transform;
        			}
        			
        			for(String next:getNeighbours_Set_WordList(current, wordList, visited)) {
					visited.add(next);
					q.offer(next);

        			}
    			}
    		}
    		return 0;
    }
    
    public static List<String> getNeighbours_Set_WordList(String word, Set<String> wordList, Set<String> visited){
    	List<String> neighbours = new ArrayList<>();
    		char[] arr = word.toCharArray();
    		for(char ch = 'a'; ch<='z'; ch++) {
    			for(int i=0; i<arr.length; i++) {
    				char tmp = arr[i];
    				// replace each of the characters in word with letters a-z and 
    				// check if it forms a word that is present in dictionary
    				if(arr[i]!=ch) { // if characters not match, create a new word by replacing the character with ch'th character
    					arr[i]=ch;
    					String newWord = new String(arr);// check if new with with 1 different character is in wordList
    					if(!visited.contains(newWord) && wordList.contains(newWord)) {
    						neighbours.add(newWord);
    					}
    					arr[i]=tmp;
    				}
    			}
    		}
    		return neighbours;    				
    }
    

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> words = Arrays.asList(new String[] {"hot","dot","dog","lot","log","cog"});
		String begin = "hit";
		String end = "cog";
		System.out.println("Shortest transformation (List)="+ladderLength(begin, end, words));
		System.out.println("Shortest transformation (Set) ="+ladderLength_Set_WordList(begin, end, new HashSet<>(words)));
	}

}
