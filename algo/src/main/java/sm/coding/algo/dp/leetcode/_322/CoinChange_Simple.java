/**
 * 
 */
package sm.coding.algo.dp.leetcode._322;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class CoinChange_Simple {

	/**
Constraints: Coin of 1 sent always be present.
	 * @param coins
	 * @param amount
	 * @return
	 */
    public static int coinChange_BruteForce(int[] coins, int amount) {
    	
    		if(amount==0) {
    			return 0;
    		}
    		int min = Integer.MAX_VALUE;
    		for(int i =0; i<coins.length; i++) {
    			if(amount - coins[i] >=0) {
    				min = Math.min(min, coinChange_BruteForce(coins, amount- coins[i]));
    			}
    		}
    		return min+1;
    }

    public static int coinChange_DP(int[] coins, int amount) {
    		int[] dp = new int[amount+1];
    		
    		for(int amt = 1; amt<dp.length; amt++) {
			int min = Integer.MAX_VALUE;
			
			for(int i =0; i<coins.length; i++) {
				if(amt - coins[i] >=0) {
					min = Math.min(min, dp[amt-coins[i]]);
				}
			}			
			dp[amt] =  min+1;
    		}
    		return dp[amount];
    }

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins;
		int amount;
		int minCoins;
		
		coins = new int[] {1,2,5};
		amount = 11;
		minCoins = coinChange_BruteForce(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = coinChange_DP(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));

		coins = new int[] {1,5,10,25};
		amount = 1017;
//		minCoins = coinChange_BruteForce(coins, amount);
//		System.out.printf("%d can be changes with minimum of %d number of coins, using %s %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));
		minCoins = coinChange_DP(coins, amount);
		System.out.printf("%d can be changes with minimum of %d number of coins, using %s %n", amount, minCoins, Arrays.stream(coins).boxed().collect(Collectors.toList()));

	}

}
