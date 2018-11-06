/**
 * 
 */
package sm.coding.algo.dp.leetcode._070;

/**
 * @author shahzadmughal8410
 *
 */
public class ClimbingStairs_070_Recr_BckTrk_DP_Fib {

	/**
	 * 
Using global count as pass by reference int array.

Time Limit Exceeded, n=44
	 * @param stairs
	 * @return
	 */
	public static int climbStairs_Bruteforce(int stairs) {
		int[] count = new int[1];
		int[] steps = new int[] {1, 2};
		stairHelper_Bruteforce(stairs, count, steps);
		return count[0];
	}
	
	public static void stairHelper_Bruteforce(int stairs, int[] count, int[] steps) {
		if(stairs==0) {
			count[0]++;
			return;
		}
		
		for(int i=0; i<steps.length; i++) {
            if(stairs-steps[i] >= 0){
            		stairHelper_Bruteforce(stairs-steps[i], count, steps);
            }
		}
	}

	/**
Using return type instead of global variable.

Time Limit Exceeded, n=44

	 * @param stairs
	 * @return
	 */
	public static int climbStairs_Bruteforce_ReturnType(int stairs) {
		int[] steps = new int[] {1, 2, 3};
		return climbStairsHelper_Bruteforce_ReturnType(stairs, steps);
	}
	
	public static int climbStairsHelper_Bruteforce_ReturnType(int stairs, int[] steps) {
		if(stairs==0) {
			return 1;
		}
		int count = 0;
		for(int i=0; i<steps.length; i++) {
            if(stairs-steps[i] >= 0){
			    count +=climbStairsHelper_Bruteforce_ReturnType(stairs-steps[i], steps);
            }
		}
		return count;
	}

	/**
Submission
https://leetcode.com/submissions/detail/174802366/
You are here!
Your runtime beats 34.90 % of java submissions.
	 * @param stairs
	 * @return
	 */
	public static int climbStairs_Memoiazation(int stairs) {
		int[] steps = new int[] {1, 2};
		return climbStairsHelper_Memoiazation(stairs, new int[stairs+1], steps);
	}
	
	public static int climbStairsHelper_Memoiazation(int stairs, int dp[], int[] steps) {
		if(stairs==0) {
			return 1;
		}
		if(dp[stairs]>0) {
			return dp[stairs];
		}
		for(int i=0; i<steps.length; i++) {
            if(stairs-steps[i] >= 0){
			    dp[stairs] +=climbStairsHelper_Memoiazation(stairs-steps[i], dp, steps);
            }
		}
		return dp[stairs];
	}

	/**
Submission
https://leetcode.com/submissions/detail/174802242/
You are here!
Your runtime beats 34.90 % of java submissions.
	 * @param stairs
	 * @return
	 */
	public static int climbStairs_Dp(int stairs) {
		if (stairs == 1) {
			return 1;
		}
		
		int[] steps = new int[] {1, 2};		
		int[] dp = new int[stairs + 1];

		dp[1] = 1;
		dp[2] = 2;

		for (int s = 3; s < dp.length; s++) {
			int count = 0;
			for(int i=0; i<steps.length; i++) {
				count += dp[s - steps[i]];
			}
			dp[s] = count;
		}
		return dp[stairs];
	}

	/**
Submission
https://leetcode.com/submissions/detail/174803684/
You are here!
Your runtime beats 34.90 % of java submissions.
	 * @param stairs
	 * @return
	 */
	public static int climbStairs_Dp_NoArray_Optimal(int stairs) {
		if (stairs == 1) {
			return 1;
		}
		
		int first = 1;
		int second = 2;

		for (int s = 3; s <=stairs; s++) {
			int third = first+second;
			first = second;
			second = third;
		}
		return second;
	}

	/**

Submission
https://leetcode.com/submissions/detail/174895461/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 * @param n
	 * @return
	 */
    // Fibonachi solution optamized for climbStaris by increasing n to n+1
    public int climbStairs_Fibonacchi_Converted_For_Stairs(int n) {
        n = n+1;
		if(n<2) {
			return n;
		}
		int first = 0;
		int second = 1;
		for(int i =2; i<=n; i++) {
			int third = first+second;
			first = second;
			second = third;
		}
		return first+second;
	}

    /**
Submission
https://leetcode.com/submissions/detail/174906239/
You are here! 
Your runtime beats 100.00 % of java submissions.

     * @param n
     * @return
     */
    // Fibonachi solution optamized for climbStaris by increasing n to n+1
    public int climbStairs_Fibonacchi2_Converted_For_Stairs(int n) {
		if(n<2) {
			return n;
		}
		int first = 0;
		int second = 1;
		for(int i =2; i<=n; i++) {
			int third = first+second;
			first = second;
			second = third;
		}
		return first+second;
	}
    
    /**
Fibonacchi using formula
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int stairs;

		stairs= 5;
		System.out.printf("%d stairs can be covered in %d ways. %n", stairs, climbStairs_Bruteforce_ReturnType(stairs));

		stairs= 5;
		System.out.printf("%d stairs can be covered in %d ways. %n", stairs, climbStairs_Dp(stairs));

	}

}
