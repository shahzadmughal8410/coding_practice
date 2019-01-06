/**
 * 
 */
package sm.coding.algo.dp.icf.squares;

/**
 * @author shahzadmughal8410
 *
 */
public class PerfectSquare {

	/**
Given a Positive number n find the least number of perfect squares which sum to the numebr n. 
	 * @param n
	 * @return
	 */
	static int perfectSquare(int n) {
		// 1. init the dp
		int[] dp = new int[n+1];
		
		// 2. base conditions, in java array defaults are zero by default
		
		// 3. loop
		for(int i = 1; i<dp.length; i++) {
			int min = Integer.MAX_VALUE;
			for(int j = 1; j*j <= i; j++) {
				// 4. logic
				min = Math.min(min,  dp[i-j*j]+1);
				dp[i] = min;
			}
		}
		// 5. return
		return dp[n];
	}
	
	public static void main(String[] args) {
		int n;
		n = 13;
		System.out.printf("Minimum perfect squares of %s are %s %n", n , perfectSquare(n));
		
	}
}
