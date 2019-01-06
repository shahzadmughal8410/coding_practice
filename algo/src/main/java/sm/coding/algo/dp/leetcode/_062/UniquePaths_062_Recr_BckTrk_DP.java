/**
 * 
 */
package sm.coding.algo.dp.leetcode._062;

/**
 * @author shahzadmughal8410
 *
 */
public class UniquePaths_062_Recr_BckTrk_DP {

	
    /**
Submission
https://leetcode.com/submission2s/detail/199288792/
You are here! 
Your runtime beats 25.16 % of java submissions.
     * @param grid
     * @return
     */
    public static int uniquePathsWithObstacles_DP(int m, int n) {
    		int[][] dp = new int[m+1][n+1];
    		dp[0][1] = 1; // dp[1][0] = 1; either of them can be used, this is to make dp[1][1]=1, means there is only 1 way to reach the dp[1][1] which is hte starting point, as dp is row+1,col+1     		
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				int left = dp[i][j-1];
				int up = dp[i-1][j];
				dp[i][j] = left + up;// as we are starting from previous values of grid so moves will be inversed 
			}
		}
		return dp[dp.length-1][dp[0].length-1];
    }
    
    /**
Submkission
https://leetcode.com/submissions/detail/199329409/
You are here! 
Your runtime beats 100.00 % of java submissions.
     * @param m
     * @param n
     * @return
     */
    public static int uniquePaths(int m, int n) {
        int N = n + m - 2;// how much steps we need to do
        int k = m - 1; // number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number 
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for (int i = 1; i <= k; i++)
            res = res * (N - k + i) / i;
        return (int)res;
    }
    
    /**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
