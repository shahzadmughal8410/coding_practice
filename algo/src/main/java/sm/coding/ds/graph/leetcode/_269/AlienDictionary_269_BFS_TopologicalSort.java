/**
 * 
 */
package sm.coding.ds.graph.leetcode._269;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class AlienDictionary_269_BFS_TopologicalSort {

	/**
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

Example 1:

Input:
[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]

Output: "wertf"
Example 2:

Input:
[
  "z",
  "x"
]

Output: "zx"
Example 3:

Input:
[
  "z",
  "x",
  "z"
] 

Output: "" 

Explanation: The order is invalid, so return "".
Note:

You may assume all letters are in lowercase.
You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.

Submission
https://leetcode.com/submissions/detail/182599292/
You are here! 
Your runtime beats 11.48 % of java submissions.
	 * @param words
	 * @return
	 */
    public static String alienOrder(String[] words) {
    		Map<Character, Set<Character>> graph = buildGraph(words);
    		return topologicalSorting(graph);
    }

    private static String topologicalSorting(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = getIndegree(graph);
        Queue<Character> queue = new LinkedList<>();
        for (Character key : indegree.keySet()) {
            // indegree zero means no dependency for this node
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Character head = queue.poll();
            sb.append(head);
            for (Character neighbor : graph.get(head)) {
                // indegree zero means no dependency for this node
            		// only process nodes who has no dependency
                indegree.put(neighbor, indegree.get(neighbor) - 1);
                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        if (sb.length() != indegree.size()) {
            return "";
        }
        return sb.toString();
    }
    
    public static Map<Character, Set<Character>> buildGraph(String[] words) {
    		Map<Character, Set<Character>> graph = new LinkedHashMap<>();
    		
    		// add vertices for all the unique characters
    		for (String word : words) {
			for (int i = 0; i < word.length(); i++) {
				graph.putIfAbsent(word.charAt(i), new HashSet<>());
			}
		}    		
    		
    		for(int i=0; i<words.length-1;i++) {
    			String word1 = words[i];
    			String word2 = words[i+1];
    			
    			int length = Math.min(word1.length(), word2.length());
    			for(int j=0; j<length; j++) {
    				if(word1.charAt(j)!=word2.charAt(j)) {
    					char key = word1.charAt(j);
    					graph.get(key).add(word2.charAt(j));
    					break;
    				}
    			}
    			
    		}
    		return graph;
    }
    
    private static Map<Character, Integer> getIndegree(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> indegree = new HashMap<>();
        for (Character key : graph.keySet()) {
            indegree.put(key, 0);
        }
        
        for (Character key : graph.keySet()) {
            for (Character neighbour : graph.get(key)) {
                indegree.put(neighbour, indegree.get(neighbour) + 1);
            }
        }     
        
        return indegree;
    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] words;
		words = new String[] {
		         "wrt",
		         "wrf",
		         "er",
		         "ett",
		         "rftt"
		};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
		words = new String[] {"z","x"};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
		words = new String[] {"z","x","z"};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
		words = new String[] {"z","z"};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
		words = new String[] {"za","zb","ca","cb"};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
		words = new String[] {"caa", "aaa", "aab"};
		System.out.println("Alien language alphabats order="+alienOrder(words));
		
	}

}
