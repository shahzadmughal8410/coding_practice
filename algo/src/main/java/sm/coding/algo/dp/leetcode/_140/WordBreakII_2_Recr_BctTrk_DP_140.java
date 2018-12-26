/**
 * 
 */
package sm.coding.algo.dp.leetcode._140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * @author shahzadmughal8410
 *
 */
public class WordBreakII_2_Recr_BctTrk_DP_140 {
	
	/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input:
s = "catsanddog"
wordDict = ["cat", "cats", "and", "sand", "dog"]
Output:
[
  "cats and dog",
  "cat sand dog"
]
Example 2:

Input:
s = "pineapplepenapple"
wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
Output:
[
  "pine apple pen apple",
  "pineapple pen apple",
  "pine applepen apple"
]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input:
s = "catsandog"
wordDict = ["cats", "dog", "sand", "and", "cat"]
Output:
[]

	 * @param s
	 * @param wordDict
	 * @return
	 */
	
	/**
	 * Following is my own resursive version, it cannot be ported to memoization or either DP.
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public static List<String> wordBreak_Bruteforce(String s, List<String> wordDict) {
		List<String> result = new ArrayList<>();
		wordBreakHelper_Bruteforce(s, wordDict, 0, result, new ArrayList<>());
		return result;
	}
	
	public static void wordBreakHelper_Bruteforce(String s, List<String> wordDict, int start, List<String> result, List<String> sofar) {
		if(start==s.length()) {
			StringBuilder sb = new StringBuilder();
			sofar.forEach(word->sb.append(word+" "));
			result.add(sb.toString().trim());
		}
		
		for(int end=start+1; end<=s.length();end++) {
			String choosen = s.substring(start, end);
			if(wordDict.contains(choosen)) {
				sofar.add(choosen);
				wordBreakHelper_Bruteforce(s, wordDict, end, result, sofar);
				sofar.remove(sofar.size()-1);
			}
		}
	}
	
	
	/**
	 * Recursive version that is the bases of memoization and DP.

	 * @param s
	 * @param wordDict
	 * @return
	 */
    public static List<String> wordBreak_Bruteforce(String s, Set<String> wordDict) {
        return wordBreakHelper_Bruteforce(s, wordDict, 0);
    }
    public static List<String> wordBreakHelper_Bruteforce(String s, Set<String> wordDict, int start) {
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
        		String choosen = s.substring(start, end);
            if (wordDict.contains(choosen)) {
                List<String> list = wordBreakHelper_Bruteforce(s, wordDict, end);
                for (String l : list) {
                    res.add(choosen + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return res;
    }
    
	/**
Submission
https://leetcode.com/submissions/detail/197075023/
You are here! 
Your runtime beats 26.84 % of java submissions.
	 * @param s
	 * @param wordDict
	 * @return
	 */
	
    public static List<String> wordBreak_Memoization(String s, List<String> wordDict) {
    		Map<Integer, List<String>> dp = new HashMap<>();
        return wordBreakHelper_Memoization(s, wordDict, 0, dp);
    }
    public static List<String> wordBreakHelper_Memoization(String s, List<String> wordDict, int start, Map<Integer, List<String>> dp) {
        if(dp.containsKey(start)) {
    			return dp.get(start);
        }
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
        		String choosen = s.substring(start, end);
            if (wordDict.contains(choosen)) {
                List<String> list = wordBreakHelper_Memoization(s, wordDict, end, dp);
                for (String word : list) {
                    res.add(choosen + (word.equals("") ? "" : " ") + word);
                }
            }
        }
        dp.put(start, res);
        return res;
    }
	

	
	/**
Its a leet code solution and giving memory limit exceeded error.
Submission
https://leetcode.com/submissions/detail/178179272/
Memory Limit Exceeded
Last executed input:
"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]

	 * @param s
	 * @param wordDict
	 * @return
	 */

    public static List<String> wordBreak_DP(String s, List<String> wordDict) {
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        Set<String> wordDictSet = new HashSet<>(wordDict);
        initial.add("");
        dp[0] = initial;
        for (int end = 1; end <= s.length(); end++) {
            LinkedList<String> list = new LinkedList<>();
            for (int start = 0; start < end; start++) {
                if (dp[start].size()>0) {
                		if(wordDictSet.contains(s.substring(start, end))) {	                
	                    for (String word : dp[start]) {
	                        list.add(word + (word.equals("") ? "" : " ") + s.substring(start, end));
	                    }
                		}
                }
            }
            dp[end] = list;
        }
        return dp[dp.length-1];
    }
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "leetcode";
		List<String> wordDict = new ArrayList<>();
		wordDict.add("leet");
		wordDict.add("code");
		System.out.println(s+"= BF "+wordBreak_Bruteforce(s, new HashSet<>(wordDict)));
		System.out.println(s+"= DP "+wordBreak_DP(s, wordDict));

		s = "catsanddog";
		wordDict.clear();
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("sand");
		wordDict.add("and");
		wordDict.add("dog");
		System.out.println(s+"= BF "+SolutionDebug.wordBreak_Bruteforce(s, new HashSet<>(wordDict)));
		System.out.println(s+"= DP "+SolutionDebug.wordBreak_DP(s, wordDict));
	
		s = "applepenapple";
		wordDict.clear();
		wordDict.add("apple");
		wordDict.add("pen");
		System.out.println(s+"= BF "+SolutionDebug.wordBreak_Bruteforce(s, new HashSet<>(wordDict)));
		System.out.println(s+"= DP "+SolutionDebug.wordBreak_DP(s, wordDict));
	}
	
}
	
class SolutionDebug {
	
	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
	//	debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}
	
	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}
	
	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}
	
	public static String indent = "|---";
	public static void debugRecr(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}
	public static void setIndent(String newIndent) {
		indent = newIndent;
	}
	
	public static String grid(boolean grid[][]) {
		StringBuilder output = new StringBuilder("\n");
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[r].length; c++) {
				output.append("|"+grid[r][c]);
			}
			output.append("|\n");
		}
		return output.toString();
	}
    public static List<String> wordBreak_Bruteforce(String s, Set<String> wordDict) {
        return wordBreakHelper_Bruteforce(s, wordDict, 0);
    }
    public static List<String> wordBreakHelper_Bruteforce(String s, Set<String> wordDict, int start) {
    		debugRecr("s="+s+", start="+start);
        LinkedList<String> res = new LinkedList<>();
        if (start == s.length()) {
        		debugRecr("Adding empty string to list.");
            res.add("");
        }
        for (int end = start + 1; end <= s.length(); end++) {
        		debugRecr("s.substring(start, end)="+s.substring(start, end));
            if (wordDict.contains(s.substring(start, end))) {
            		String actual = incrementIndent();
                List<String> list = wordBreakHelper_Bruteforce(s, wordDict, end);
                setIndent(actual);
                debugRecr("list="+list);
                for (String word : list) {
                		debugRecr("s.substring(start, end)="+s.substring(start, end)+", word="+word);
                    res.add(s.substring(start, end) + (word.equals("") ? "" : " ") + word);
                }
            }
        }
        return res;
    }
    
    public static List<String> wordBreak_DP(String s, List<String> wordDict) {
    		reset();
    		debug("s="+s+", wordDict="+wordDict);
        LinkedList<String>[] dp = new LinkedList[s.length() + 1];
        LinkedList<String> initial = new LinkedList<>();
        initial.add("");
        dp[0] = initial;
        for (int end = 1; end <= s.length(); end++) {
            LinkedList<String> list = new LinkedList<>();
            for (int start = 0; start < end; start++) {
                if (dp[start].size()>0) {
                		if(wordDict.contains(s.substring(start, end))) {	                
	                    for (String l : dp[start]) {
	                        list.add(l + (l.equals("") ? "" : " ") + s.substring(start, end));
	                    }
                		}
                }
            }
            dp[end] = list;
        }
        debug("DP="+Arrays.stream(dp).collect(Collectors.toList()));
        return dp[dp.length-1];
    }
}
