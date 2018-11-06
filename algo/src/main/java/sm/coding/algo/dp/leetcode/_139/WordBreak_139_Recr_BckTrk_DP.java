/**
 * 
 */
package sm.coding.algo.dp.leetcode._139;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class WordBreak_139_Recr_BckTrk_DP {

	/**
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false


Submission
https://leetcode.com/submissions/detail/177529424/
Time Limit Exceeded
Last executed input:
"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"
["a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"]
	 */
    public static boolean wordBreak_Bruteforce(String s, List<String> wordDict) {
    		return wordBreakHelper_Bruteforce(s, wordDict, 0);
    }

	public static boolean wordBreakHelper_Bruteforce(String s, List<String> wordDict, int start) {
    		if(start==s.length()) {
    			return true;
    		}
    		
    		for(int end=start+1; end<=s.length();end++) {
    			String choosen = s.substring(start, end);
    			if(wordDict.contains(choosen)) {
    				if(wordBreakHelper_Bruteforce(s, wordDict, end)) {
    					return true;
    				}
    			}
    		}
        return false;
    }
	/**
Submission
https://leetcode.com/submissions/detail/178178548/
You are here! 
Your runtime beats 62.49 % of java submissions.
	 * @param s
	 * @param wordDict
	 * @return
	 */
	
    public static boolean wordBreak_Memoization(String s, List<String> wordDict) {
    		Boolean[] dp = new Boolean[s.length()+1];
		return wordBreakHelper_Memoization(s, wordDict, 0, dp);
    }

	public static boolean wordBreakHelper_Memoization(String s, List<String> wordDict, int start, Boolean[] dp) {
		if(dp[start]!=null) {
			return dp[start];
		}
		if(start==s.length()) {
			return true;
		}
		for(int end=start+1; end<=s.length();end++) {
			String choosen = s.substring(start, end);
			if(wordDict.contains(choosen)) {
				if(wordBreakHelper_Memoization(s, wordDict, end, dp)) {
					dp[start] = true;
					return dp[start];
				}
			}
		}
		dp[start] = false;
		return dp[start];
	}

	/**
https://leetcode.com/submissions/detail/177775785/
You are here! 
Your runtime beats 8.59 % of java submissions.
	 * @param s
	 * @param wordDict
	 * @return
	 */
    public static boolean wordBreak_BFS(String s, List<String> wordDict) {
        Set<String> wordDictSet=new HashSet<>(wordDict);
        Deque<Integer> queue = new LinkedList<>();
        int[] visited = new int[s.length()+1];
        queue.add(0);
        visited[0] = 1;
        while (!queue.isEmpty()) {
            int start = queue.remove();
                for (int end = start + 1; end <= s.length(); end++) {
                    if (wordDictSet.contains(s.substring(start, end))) {
                    		// process node                                                       -
	                    	if(visited[end] == 0) {
	                        queue.add(end);
	                        visited[end] = 1;
	                        if (end == s.length()) {
	                            return true;
	                        }
	                    }
                }
            }
        }
        return false;
    }
	
    /**
Submission
https://leetcode.com/submissions/detail/177772095/
You are here! 
Your runtime beats 54.62 % of java submissions.
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak_DP(String s, List<String> wordDict) {
		if(s.length()==0) {
			return true;
		}
		
		boolean[] dp = new boolean[s.length()+1];
		dp[0]=true;
		for(int end=1; end<=s.length();end++) {
			for(int start=0; start<end;start++){
				if(dp[start]) { 
					if(wordDict.contains(s.substring(start, end))) {
						dp[end] = true;
						break;
					}
				}
			}
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
		System.out.println(s+"= BF "+wordBreak_Bruteforce(s, wordDict));
		System.out.println(s+"= MO "+wordBreak_Memoization(s, wordDict));
		System.out.println(s+"=BFS "+wordBreak_BFS(s, wordDict));
		System.out.println(s+"= DP "+wordBreak_DP(s, wordDict));

		s = "catsand";
		wordDict.clear();
		wordDict.add("cat");
		wordDict.add("cats");
		wordDict.add("sand");
		wordDict.add("and");
//		System.out.println(s+"= BF "+wordBreak_Bruteforce(s, wordDict));
		System.out.println(s+"= MO "+wordBreak_Memoization(s, wordDict));
		System.out.println(s+"=BFS "+wordBreak_BFS(s, wordDict));
//		System.out.println(s+"= DP "+wordBreak_DP(s, wordDict));
		System.out.println(s+"= BF "+SolutionDebug.wordBreak_Bruteforce(s, wordDict));
		System.out.println(s+"= DP "+SolutionDebug.wordBreak_DP(s, wordDict));

		s = "applepenapple";
		wordDict.clear();
		wordDict.add("apple");
		wordDict.add("pen");
		System.out.println(s+"= BF "+wordBreak_Bruteforce(s, wordDict));
		System.out.println(s+"= MO "+wordBreak_Memoization(s, wordDict));
		System.out.println(s+"=BFS "+wordBreak_BFS(s, wordDict));
		System.out.println(s+"= DP "+wordBreak_DP(s, wordDict));
		

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
//		debugRow(cols);
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
	
    public static boolean wordBreak_Bruteforce(String s, List<String> wordDict) {
		debugRecr("s="+s+", s.length()="+s.length());
		return wordBreakHelper_Bruteforce(s, wordDict, 0);
}

    public static boolean wordBreakHelper_Bruteforce(String s, List<String> wordDict, int start) {
		debugRecr("s="+s+", start="+start);
		if(start==s.length()) {
			debugRecr("Nothing to choose: s.length()="+s.length());
			return true;
		}
		
		for(int end=start+1; end<=s.length();end++) {
			String choosen = s.substring(start, end);
			debugRecr("s.substring(start, index) => s.substring("+start+", "+end+") = "+choosen+", wordDict.contains('"+choosen+"')="+wordDict.contains(choosen));
			if(wordDict.contains(choosen)) {
				String actual = incrementIndent();
				boolean result = wordBreakHelper_Bruteforce(s, wordDict, end);
				setIndent(actual);
				debugRecr("result="+result);
				if(result) {
					debugRecr("returning true");
					return true;
				}

			}
		}
		debugRecr("returning false");
		return false;
    }
    
    public static boolean wordBreak_DP(String s, List<String> wordDict) {
    		reset();
    		debug("s="+s+", s.length()="+s.length());
		if(s.length()==0) {
			debug("Zero length string, returning true.");
			return true;
		}
		
		boolean[] dp = new boolean[s.length()+1];
		dp[0]=true;
		String startStr =  String.format("%1$"+(6+dp.length*2)+"s", "");
		StringBuilder sb = new StringBuilder("\nstart="+ startStr+", end=0 DP"+Arrays.toString(dp)+"\n");
		tableColumns("start", "end", "dp[start]:25", "s.substring(start, end):60", "wordDict.contains(s.substring(start, end)):85");
		debugColumns();
		for(int end=1; end<=s.length();end++) {
			for(int start=0; start<end;start++){
				debugRow(start, end, "dp[start] => dp["+start+"]="+dp[start], "s.substring(start, end) => s.substring("+start+", "+end+") = "+s.substring(start, end)+ (dp[start] ? "": " SKIP"), "wordDict.contains(s.substring(start, end)) = > wordDict.contains("+s.substring(start, end)+") = "+ (dp[start] ? wordDict.contains(s.substring(start, end)) : "---"));
				if(dp[start]) { 
					if(wordDict.contains(s.substring(start, end))) {
						dp[end] = true;
						break;
					}
				}
			}
			startStr =  String.format("%1$-"+(6+dp.length*2)+"s", IntStream.range(0, end).boxed().collect(Collectors.toList()).toString());
			sb.append("start="+ startStr+", end="+end+" DP"+Arrays.toString(dp)+"\n");
		}
		debug(sb.toString());
		return dp[dp.length-1];
    }
}
