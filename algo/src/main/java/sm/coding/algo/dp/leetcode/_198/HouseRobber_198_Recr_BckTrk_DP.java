/**
 * 
 */
package sm.coding.algo.dp.leetcode._198;

/**
 * @author shahzadmughal8410
 *
 */
public class HouseRobber_198_Recr_BckTrk_DP {

	/**
You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.
	 */

	/**
Submission
https://leetcode.com/submissions/detail/176867083/
Time Limit Exceeded
Last executed input:
[114,117,207,117,235,82,90,67,143,146,53,108,200,91,80,223,58,170,110,236,81,90,222,160,165,195,187,
199,114,235,197,187,69,129,64,214,228,78,188,67,205,94,205,169,241,202,144,240]
	 * @param houses
	 * @return
	 */
	public static int rob_Bruteforce(int[] houses) {
		if(houses.length==0) {
			return 0;
		}else if(houses.length==1) {
			return houses[0];
		}
		return robHelper_Bruteforce(houses, houses.length-1);
	}

	public static int robHelper_Bruteforce(int[] houses, int index) {
		// if there are no houses to rob
		if(index<0) {
			return 0;
		}
		// if this is the last house to rob
		if(index==0) {
			return houses[0];
		}		
		return Math.max( houses[index] + robHelper_Bruteforce(houses, index-2),  robHelper_Bruteforce(houses, index-1));
	}

	/**
Submission
https://leetcode.com/submissions/detail/176865497/
You are here! 
Your runtime beats 54.62 % of java submissions.
	 * @param houses
	 * @return
	 */
	public static int rob_DP(int[] houses) {
		if(houses.length==0) {
			return 0;
		}else if(houses.length==1) {
			return houses[0];
		}
		
		int dp[] = new int[houses.length];
		
		dp[0] = houses[0];
		dp[1] = Math.max(houses[0], houses[1]);
		
		for(int i=2;i<dp.length;i++) {
			dp[i] = Math.max( houses[i]+dp[i-2], dp[i-1]);
		}
		return dp[dp.length-1];
	}
	/**
submission
https://leetcode.com/submissions/detail/193089334/
You are here! 
Your runtime beats 94.16 % of java submissions.
	 * @param houses
	 * @return
	 */
	public static int rob_DP_Optamized(int[] houses) {
		if(houses.length==0) {
			return 0;
		}else if(houses.length==1) {
			return houses[0];
		}
		
		int first = houses[0];
		int second = Math.max(houses[0], houses[1]);
		for(int i=2;i<houses.length;i++) {
			int third  = Math.max( houses[i] + first, second);
			first = second;
			second = third;
		}
		return second;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int houses[] ;
		houses = new int[] {1,2,3,1};
		System.out.println(" BF Max robery value = "+rob_Bruteforce(houses));
		System.out.println(" DP Max robery value = "+rob_DP(houses));
		System.out.println("DPO Max robery value = "+rob_DP_Optamized(houses));

		houses = new int[] {2,7,9,3,1};
		System.out.println(" BF Max robery value = "+rob_Bruteforce(houses));
		System.out.println(" DO Max robery value = "+rob_DP(houses));
		System.out.println("DPO Max robery value = "+rob_DP_Optamized(houses));
	}

}
