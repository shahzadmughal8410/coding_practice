/**
 * 
 */
package sm.coding.ds.array.leetcode._243;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class ShortestWordDistance_243_Arr {

	/**
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
Given word1 = “coding”, word2 = “practice”, return 3.
Given word1 = "makes", word2 = "coding", return 1.
Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.

Links
https://leetcode.com/problems/shortest-word-distance/solution/ 
https://www.geeksforgeeks.org/find-the-minimum-distance-between-two-numbers/ 
	 * 
	 * 
	 * @param args
	 */
	public static int shortestDistance(String[] words, String word1, String word2) {
		
		int idx1 = -1; 
		int idx2 = -1;
		int minimumDistance = words.length;
		
		for(int i =0; i<words.length; i++) {
			if(words[i].equals(word1)) {
				idx1 = i;
			}else if(words[i].equals(word2)) {
				idx2 = i;
			}
			if(idx1 != -1 && idx2 != -1) {
				minimumDistance = Math.min(minimumDistance, Math.abs(idx1-idx2));
			}
		}
		return minimumDistance;
	}	
	
	public static void main(String[] args) {
		String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};
		String word1 = "coding";
		String word2 = "practice";
		int minDistance = shortestDistance(words, word1, word2);
		System.out.printf("%s, %s, shortest distance %d , %s %n", word1, word2, minDistance, Arrays.stream(words).collect(Collectors.toList()));

		word1 = "makes";
		word2 = "coding";
		minDistance = shortestDistance(words, word1, word2);
		System.out.printf("%s, %s, shortest distance %d , %s %n", word1, word2, minDistance, Arrays.stream(words).collect(Collectors.toList()));


	}

}
