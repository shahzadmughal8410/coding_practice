/**
 * 
 */
package sm.coding.algo.dp.leetcode._518;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class CoinChange2_518_Recr_BckTrk_DP {

	/**
Submission
https://leetcode.com/submissions/detail/175911579/
Time Limit Exceeded
Last executed input:
500
[3,5,7,8,9,10,11]

	 * @param amount
	 * @param coins
	 * @return
	 */
    public static int change(int amount, int[] coins) {
    		int[] totalWays = new int[1];
    		change(amount, coins, 0, totalWays);
    		return totalWays[0];
    }
	
    public static void change(int amount, int[] coins, int index, int[] totalWays) {
    		// base case
    		// if amount is zero there is one way to represent it with zero coins
    		if(amount == 0) {
    			totalWays[0]++;
    			return;
    		}
    		
    		//recursive case
    		// as coins order doesn't matter we will use index and start from index in recursion
    		for(int i=index; i<coins.length; i++) {
    			if(amount - coins[i] >=0) {
    				change(amount-coins[i], coins, i, totalWays);
    			}
    		}
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/176239841/
You are here! 
Your runtime beats 77.33 % of java submissions.

     * @param amount
     * @param coins
     * @return
     */
    public static int change_DP(int amount, int[] coins) {
    		int[] dp = new int[amount+1];
    		// For zero amount, there is 1 solution use NO coins
    		dp[0] = 1;
    		
    		for(int coin: coins) {
    			for(int amt = 1; amt<dp.length ; amt++) {
    				if(amt-coin >=0) {
    					dp[amt] += dp[amt-coin];
    				}
    			}
    		}
    		return dp[dp.length-1];
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins;
		int amount;
		int totalWays;
		coins = new int[] {1,2,5};
		amount = 5;
		
		totalWays = SolutionDebug.change_DP(amount, coins);
		System.out.println("Total ways="+totalWays);

		totalWays = change_DP(amount, coins);
		System.out.println("Total ways="+totalWays);

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
	
    public static int change_DP(int amount, int[] coins) {
    		debug("amount="+amount+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		int[] dp = new int[amount+1];
		// For zero amount, there is 1 solution use NO coins
		dp[0] = 1;
		tableColumns("coin", "i:3", "amount", "i>=coin:25", "dp[i]:8", "dp[i-coin]:20", "dp[i] + dp[i-coin]", "dp:30");
		debugColumns();
		for(int coin: coins) {
			for(int amt = 1; amt<dp.length ; amt++) {
				debugRow(coin, amt, amount, "i>=coin => "+amt+">="+coin+" => "+(amt>=coin), (amt>=coin ? "dp["+amt+"]="+dp[amt] :"-"), (amt>=coin ? "dp["+amt+"-"+coin+"] => dp["+(amt-coin)+"] ="+dp[amt-coin] : "-"), (amt>=coin ? dp[amt] + dp[amt-coin] : "-"), Arrays.stream(dp).boxed().collect(Collectors.toList()));
				if(amt-coin>=0) {
					dp[amt] += dp[amt-coin];
				}
			}
		}
		debug("dp["+(dp.length-1)+"]="+dp[dp.length-1]);
		return dp[dp.length-1];
}
}
