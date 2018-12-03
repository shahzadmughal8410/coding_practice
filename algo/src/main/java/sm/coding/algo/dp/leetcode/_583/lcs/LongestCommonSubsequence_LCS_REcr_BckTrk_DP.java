/**
 * 
 */
package sm.coding.algo.dp.leetcode._583.lcs;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class LongestCommonSubsequence_LCS_REcr_BckTrk_DP {

	/**
LCS Problem Statement: Given two sequences, find the length of longest subsequence present in both of them. 
A subsequence is a sequence that appears in the same relative order, but not necessarily contiguous. 
For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of “abcdefg”. 
So a string of length n has 2^n different possible subsequences.

It is a classic computer science problem, the basis of diff (a file comparison program that outputs the differences 
between two files), and has applications in bioinformatics.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.
	 */
	public static int lcs_BruteForce(String word1, String word2) {
		return lcs_BruteForce_Helper(word1, word2, word1.length(), word2.length());
	}
	
	public static int lcs_BruteForce_Helper(String word1, String word2, int m, int n) {
		if(m==0 || n==0) {
			return 0;
		}
		
		if(word1.charAt(m-1) == word2.charAt(n-1)) {
			return 1+ lcs_BruteForce_Helper(word1, word2, m-1, n-1);
		}else {
			return Math.max(lcs_BruteForce_Helper(word1, word2, m, n-1), lcs_BruteForce_Helper(word1, word2, m-1, n));
		}
	}
	
	public static int lcs_DP(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		
		if(m==0 || n==0) {
			return 0;
		}
		
		int[][] dp = new int[m+1][n+1];
		
		for(int i = 1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = 1+ dp[i-1][j-1];
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String word1;
		String word2;
		int lcs;
		
		word1 = "AGGTAB";
		word2 = "GXTXAYB";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("LCS BF="+lcs_BruteForce(word1, word2));
		System.out.println("LCS DP="+lcs_DP(word1, word2));

		word1 = "aedfhr";
		word2 = "abcdgh";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("LCS BF="+SolutionDebug.lcs_BruteForce(word1, word2));
		System.out.println("LCS DP="+SolutionDebug.lcs_DP(word1, word2));

		word1 = "sea";
		word2 = "eat";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("LCS BF="+lcs_BruteForce(word1, word2));
		System.out.println("LCS DP="+lcs_DP(word1, word2));

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

	public static String grid(int grid[][]) {
		StringBuilder output = new StringBuilder("\n");
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[r].length; c++) {
				output.append("|"+grid[r][c]);
			}
			output.append("|\n");
		}
		return output.toString();
	}

	public static int lcs_BruteForce(String word1, String word2) {
		debugRecr("word1="+word1+", word2="+word2);
		int lcs = lcs_BruteForce_Helper(word1, word2, word1.length(), word2.length());
		debugRecr("lcs="+lcs);
		return lcs;
	}
	
	public static int lcs_BruteForce_Helper(String word1, String word2, int m, int n) {
		debugRecr("m="+m+", n="+n);
		if(m==0 || n==0) {
			debugRecr("Either word1 or word2 is zero length, lcs ZERO.");
			return 0;
		}
		debugRecr("word1.charAt(m-1) == word2.charAt(n-1) => word1.charAt("+m+"-1) == word2.charAt("+n+"-1) => "+word1.charAt(m-1) +"=="+ word2.charAt(n-1));
		if(word1.charAt(m-1) == word2.charAt(n-1)) {
			debugRecr("Matched");
			int result = 1+ lcs_BruteForce_Helper(word1, word2, m-1, n-1);
			debugRecr("result="+result);
			return result;
		}else {
			int max = Math.max(lcs_BruteForce_Helper(word1, word2, m, n-1), lcs_BruteForce_Helper(word1, word2, m-1, n));
			debugRecr("max="+max);
			return max;
		}
	}
	
	public static int lcs_DP(String word1, String word2) {
		reset();
		int m = word1.length();
		int n = word2.length();
		debug("word1="+word1+", word2="+word2+", m="+m+", n="+n);
		
		if(m==0 || n==0) {
			return 0;
		}
		
		int[][] dp = new int[m+1][n+1];
		
		for(int i = 1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				debug("word1.charAt(i-1) == word2.charAt(j-1) => word1.charAt("+i+"-1) == word2.charAt("+j+"-1) => "+word1.charAt(i-1) +"=="+ word2.charAt(j-1));
				if(word1.charAt(i-1) == word2.charAt(j-1)) {
					dp[i][j] = 1+ dp[i-1][j-1];
					debug("dp[i][j] => dp["+i+"]["+j+"] => "+(dp[i][j]));
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
					debug("dp[i][j] => dp["+i+"]["+j+"] => "+(dp[i][j]));
				}
			}
		}
		debug(grid(dp));
		debug("lcs="+dp[dp.length-1][dp[0].length-1]);
		return dp[dp.length-1][dp[0].length-1];
	}
}