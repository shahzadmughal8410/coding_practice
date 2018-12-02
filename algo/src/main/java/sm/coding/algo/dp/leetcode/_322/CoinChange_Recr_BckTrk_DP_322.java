/**
 * 
 */
package sm.coding.algo.dp.leetcode._322;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class CoinChange_Recr_BckTrk_DP_322 {

	/**
You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
Example 1:
Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:
Input: coins = [2], amount = 3
Output: -1


Note:
You may assume that you have an infinite number of each kind of coin.


Links
https://leetcode.com/problems/coin-change/description/
https://leetcode.com/problems/coin-change/solution/
https://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/ 

Similar questions
https://www.geeksforgeeks.org/coin-change-dp-7/     
	 */
	

	/**
Submission
https://leetcode.com/submissions/detail/175021908/
Time Limit Exceeded
Last executed input:
[1,2,5]
100
	 * @param coins
	 * @param amount
	 * @return
	 */	
    public static int coinChange(int[] coins, int amount) {
		int result = coinChangeHelper(coins, amount, 0); 
	    return result==Integer.MAX_VALUE ? -1 :result;
	}

	public static int coinChangeHelper(int[] coins, int amount, int sofar) {
			// base case
			if(amount==0) {
				// here we get the maximum value for the number of coins
				// as the function increments for each call, 
				// this approach is not suitable to get the minimum coins, as converting it to momoized form will not work.
				// consider the coinChange_BruteForce
				// in that function we return zero from base condition
				// and count at the end of recursion call, that will lead us to get the minimum coins 
				return sofar;
			}
			
			// recursive case
			int min = Integer.MAX_VALUE;
			for(int i =0; i<coins.length; i++) {
				if(amount - coins[i] >= 0) {
					//choose and explore, unchoose is implicit backtracking
					min = Math.min(min, coinChangeHelper(coins, amount-coins[i], sofar+1)) ; // implicit back tracking   
				}
			}
			return min;
	}	

	/**
Following conversion recursion of memoization is NOT WORKING, wrong solution!
	 * @param coins
	 * @param amount
	 * @return
	 */
    public static int coinChange_Memo_Wrong(int[] coins, int amount) {
    		int[] dp = new int[amount+1]; 
    		Arrays.fill(dp, -1);
		int result = coinChangeHelper_Memo_Wrong(coins, amount, 0, dp); 
	    return result==Integer.MAX_VALUE ? -1 :result;
	}

	public static int coinChangeHelper_Memo_Wrong(int[] coins, int amount, int sofar, int[] dp) {
		if(dp[amount] > 0 || dp[amount]!=Integer.MAX_VALUE) {
			return dp[amount];
		}
	
		// base case
		if(amount==0) {
			return sofar;
		}
		
		// recursive case
		int min = Integer.MAX_VALUE;
		for(int i =0; i<coins.length; i++) {
			if(amount - coins[i] >= 0) {
				//choose and explore, unchoose is implicit backtracking
				min = Math.min(min, coinChangeHelper_Memo_Wrong(coins, amount-coins[i], sofar+1, dp)) ; // implicit back tracking   
			}
		}
		dp[amount] = min;
		return dp[amount];
	}	
	
	
	/**
Submission
https://leetcode.com/submissions/detail/192819715/
Time Limit Exceeded
Last executed input:
[1,2,5]
100
	 * @param coins
	 * @param amount
	 * @return
	 */
    public static int coinChange_BruteForce(int[] coins, int amount) {
    		// base case
    		if(amount==0) { //when amount is zero, we need minimum of zero coins to make change
    			return 0;
    		}
    		
    		// recursive case
    		int min = Integer.MAX_VALUE;
    		for(int i =0; i<coins.length; i++) {
    			if(amount - coins[i] >= 0) {
    				//choose and explore, unchoose is implicit backtracking
    				int newMin = Math.min(min, coinChange_BruteForce(coins, amount-coins[i])) ; // implicit back tracking 
    				min = newMin!=-1 ? newMin : min;
    			}
    		}
    		return min == Integer.MAX_VALUE ? -1 : min+1;
    }

    
	/**
Submission
https://leetcode.com/submissions/detail/175469228/
Time Limit Exceeded
Last executed input:
[186,419,83,408]
6249
	    
	 * @param coins
	 * @param amount
	 * @return
	 */
    public static int coinChange_Memoization(int[] coins, int amount) {
    		int[] dp = new int[amount+1];
    		for (int i = 0; i < dp.length ; i++)
    			dp[i] = -1;
		return coinChangeHelper_Memoization(coins, amount, dp); 
    }

    public static int coinChangeHelper_Memoization(int[] coins, int amount, int[] dp) {
		if(dp[amount] > 0) {
			return dp[amount];
		}

		// base case
		if(amount==0) {
			return 0;
		}
		
		// recursive case
		int min = Integer.MAX_VALUE;
		for(int i =0; i<coins.length; i++) {
			if(amount - coins[i] >= 0) {
				//choose and explore, unchoose is implicit backtracking
				int newMin = Math.min(min, coinChangeHelper_Memoization(coins, amount-coins[i], dp)) ; // implicit back tracking 
				min = newMin!=-1 ? newMin : min;
			}
		}
		dp[amount] = (min == Integer.MAX_VALUE ? -1 : min+1);
		return dp[amount];
    }

    /**
Submission
https://leetcode.com/submissions/detail/192818360/
You are here! 
Your runtime beats 87.88 % of java submissions.

     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange_DP(int[] coins, int amount) {
    		// 1. init dp array
		int[] dp = new int[amount+1];
		for (int i = 0; i < dp.length ; i++) // Arrays.fill(dp, -1);
			dp[i] = -1; 
    		// 2. base case
    		dp[0] = 0;//when amount is zero, we need minimum of zero coins to make change
    		
    		// 3. loop, for recursive case
    		for(int amt = 1; amt<dp.length; amt++) {
			int min = Integer.MAX_VALUE;
			// 4. logic 
			for(int i =0; i<coins.length; i++) {
				if(amt - coins[i] >= 0) {
					int newMin = Math.min(min, dp[amt-coins[i]]) ;  
					min = newMin!=-1 ? newMin : min;
				}
			}
			dp[amt] = (min == Integer.MAX_VALUE ? -1 : min+1);
    		}
		// 5. return	
		return dp[dp.length-1];
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins = new int[] {1,2,5};
		int amount = 3;
		int minCoins = coinChange_BruteForce(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - BF   %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = coinChange_Memoization(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - MEMO %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		
		coins = new int[] {1,2,5};
//		coins = new int[] {5,2,1};
		amount = 11;
		minCoins = coinChange_BruteForce(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - BF   %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = coinChange_Memoization(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - MEMO %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = coinChange_DP(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - DP   %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		
		coins = new int[] {1,2,5};
		amount = 5;
		minCoins = coinChange_BruteForce(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - BF   %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = SolutionDebug.coinChange_Memoization(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - MEMO %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = SolutionDebug.coinChange_DP(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s - DP   %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		
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

    public static int coinChange_Memoization(int[] coins, int amount) {
    		debugRecr("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		int[] dp = new int[amount+1];
		for (int i = 0; i < dp.length ; i++)
			dp[i] = -1;
		debugRecr("dp="+Arrays.stream(dp).boxed().collect(Collectors.toList()));
	return coinChangeHelper_Memoization(coins, amount, 0, dp); 
}

	public static int coinChangeHelper_Memoization(int[] coins, int amount, int sofar, int[] dp) {
		debugRecr("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList())+", dp="+Arrays.stream(dp).boxed().collect(Collectors.toList()));
		if(dp[amount] > 0) {
			return dp[amount];
		}
	
		// base case
		if(amount==0) {
			return 0;
		}
		
		// recursive case
		int min = Integer.MAX_VALUE;
		for(int i =0; i<coins.length; i++) {
			if(amount - coins[i] >= 0) {
				//choose and explore, unchoose is implicit backtracking
				String actual = incrementIndent();
				int newMin = Math.min(min, coinChangeHelper_Memoization(coins, amount-coins[i], sofar, dp)) ; // implicit back tracking 
				min = newMin!=-1 ? newMin : min;
				setIndent(actual);
			}
		}
		dp[amount] = (min == Integer.MAX_VALUE ? -1 : min+1);
		debugRecr("dp["+amount+"]="+dp[amount]);
		return dp[amount];
	}
	
    public static int coinChange_DP(int[] coins, int amount) {
    		reset();
		debug("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		// 1. init dp array
		int[] dp = new int[amount+1];
		for (int i = 0; i < dp.length ; i++)
			dp[i] = -1;
		
		debug("dp="+Arrays.stream(dp).boxed().collect(Collectors.toList()));
		// 2. base case
		dp[0] = 0;
		
		tableColumns("amt", "i", "coins[i]:8", "amt - coins[i]:30", "dp[amt-coins[i]]", "min:10", "newMin", "dp:50");
		debugColumns();
		
		// 3. loop, for recursive case
		for(int amt = 1; amt<dp.length; amt++) {
			int min = Integer.MAX_VALUE;
			// 4. logic 
			for(int i =0; i<coins.length; i++) {
				if(amt - coins[i] >= 0) {
					int newMin = Math.min(min, dp[amt-coins[i]]) ;
					debugRow(amt, i, "coins["+i+"]", "amt - coins["+i+"] => "+amt+" - "+coins[i]+"="+(amt - coins[i] ),  dp[amt-coins[i]], min, newMin, Arrays.stream(dp).boxed().collect(Collectors.toList()));
					min = newMin!=-1 ? newMin : min;
				}
			}			
			dp[amt] = (min == Integer.MAX_VALUE ? -1 : min+1);
//			debug("dp[amt]=>dp["+amt+"] = "+dp[amt]);
		}
	// 5. return	
	return dp[amount];
}
	
	// wrong solution, as its doing MAXIMUM coins instead of minimum coins
//    public static int coinChange_Memo(int[] coins, int amount) {
//    		debugRecr("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
//		int[] dp = new int[amount+1]; 
//		Arrays.fill(dp, -1);
//		int result = coinChangeHelper_Memo(coins, amount, 0, dp); 
//	    return result==Integer.MAX_VALUE ? -1 :result;
//    }
//
//    public static int coinChangeHelper_Memo(int[] coins, int amount, int sofar, int[] dp) {
//	    	debugRecr("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList())+", dp="+Arrays.stream(dp).boxed().collect(Collectors.toList()));
//		if(dp[amount] > 0 && dp[amount]!=Integer.MAX_VALUE) {
//			return dp[amount];
//		}
//	
//		// base case
//		if(amount==0) {
//			return sofar;
//		}
//		
//		// recursive case
//		int min = Integer.MAX_VALUE;
//		for(int i =0; i<coins.length; i++) {
//			if(amount - coins[i] >= 0) {
//				//choose and explore, unchoose is implicit backtracking
//				String actual = incrementIndent();
//				min = Math.min(min, coinChangeHelper_Memo(coins, amount-coins[i], sofar+1, dp)) ; // implicit back tracking
//				setIndent(actual);
//			}
//		}
//		dp[amount] = min;
//		debugRecr("dp["+amount+"]="+dp[amount]);
//		return dp[amount];
//    }
	
}
