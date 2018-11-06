/**
 * 
 */
package sm.coding.algo.dp.leetcode._583;

/**
 * @author shahzadmughal8410
 *
 */
public class DeleteOperationForTwoStrings_583_LCS_Recr_BckTrk_DP {

	
	/**
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.

Submission
https://leetcode.com/submissions/detail/176558062/
Time Limit Exceeded
Last executed input:
"dinitrophenylhydrazine"
"benzalphenylhydrazone"

	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance_lcs_BruteForce(String word1, String word2) {
		return word1.length()+word2.length() - (2 * minDistance_lcs_BruteForce_Helper(word1, word2, word1.length(), word2.length())) ;
	}
	
	public static int minDistance_lcs_BruteForce_Helper(String word1, String word2, int m, int n) {
		if(m==0 || n==0) {
			return 0;
		}
		if(word1.charAt(m-1) == word2.charAt(n-1)) {
			return 1+ minDistance_lcs_BruteForce_Helper(word1, word2, m-1, n-1);
		}else {
			return Math.max(minDistance_lcs_BruteForce_Helper(word1, word2, m, n-1), minDistance_lcs_BruteForce_Helper(word1, word2, m-1, n));
		}
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/176558672/
You are here! 
Your runtime beats 45.92 % of java submissions.
	 * @param word1
	 * @param word2
	 * @return
	 */
	public static int minDistance_lcs_DP(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();
		
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
		return word1.length()+word2.length() - (2 * dp[m][n]);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String word1;
		String word2;
		
		word1 = "ABCDGH";
		word2= "AEDFHR";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("Min Distance LCS BF="+minDistance_lcs_BruteForce(word1, word2));
		System.out.println("Min Distance LCS DP="+minDistance_lcs_DP(word1, word2));

		word1 = "AGGTAB";
		word2= "GXTXAYB";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("Min Distance LCS BF="+minDistance_lcs_BruteForce(word1, word2));
		System.out.println("Min Distance LCS DP="+minDistance_lcs_DP(word1, word2));

		word1 = "sea";
		word2= "eat";
		System.out.println("word1="+word1+", word2="+word2);
		System.out.println("Min Distance LCS BF="+minDistance_lcs_BruteForce(word1, word2));
		System.out.println("Min Distance LCS DP="+minDistance_lcs_DP(word1, word2));

	}

}
