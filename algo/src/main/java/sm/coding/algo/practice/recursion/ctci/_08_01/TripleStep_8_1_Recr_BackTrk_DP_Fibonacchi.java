/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_01;

/**
 * @author smughal
 *
 */
public class TripleStep_8_1_Recr_BackTrk_DP_Fibonacchi {

	/**
	 * 
8.1 Triple Step: A child is running up a staircase with n steps and can hop either 1 step, 2 steps, or 3
steps at a time. Implement a method to count how many possible ways the child can run up the
stairs.
 
	 * @param args
	 */
			
	public static int count_Bruteforce(int stairs) {
		int[] steps = new int[] {1, 2, 3};
		return countHelper(stairs, steps);
	}
	
	public static int countHelper(int stairs, int[] steps) {
		if(stairs==0) {
			return 1;
		}
		int count = 0;
		for(int i=0;i < steps.length;i++) {
			if(stairs-steps[i]>=0) {
				count += countHelper(stairs-steps[i], steps);
			}
		}
		return count;
	}
		
	public static int count_DP(int stairs) {
		int[] steps = new int[] {1, 2, 3};
		
		// 1. init DP array, as of recursion return type
		int[] dp = new int[stairs+1];
		
		// 2. set defaults
		dp[0] = 1; // there is one way to reach zero stairs i.e. 0 step
		dp[1] = 1; // there is 1 way to reach 1 stairs, i.e. 1 step
		dp[2] = 2; // there are 2 ways to reach 2 stairs, i.e. 1*2 or 2 step.
		
		// 3. loop, instead of recursion call
		for(int i=3; i<dp.length; i++) {
			// 4. logic
			int count = 0;
			for(int s=0; s<steps.length; s++) {
				count += dp[i-steps[s]];
			}
			dp[i] = count;
		}
		// 5. return
		return dp[dp.length-1];
	}
	
	public static int count_DP_Optamized(int stairs) {
		if(stairs<2) {
			return 1;
		}else if(stairs==2) {
			return 2;
		}
		
		int first = 1;  // there is one way to reach zero stairs i.e. 0 step
		int second = 1; // there is 1 way to reach 1 stairs, i.e. 1 step
		int third = 2; // there are 2 ways to reach 2 stairs, i.e. 1*2 or 2 step.
		
		for(int i =3; i<stairs; i++) {
			int fourth = first+second+third;
			first = second;
			second = third;
			third = fourth;
		}
		return first+second+third;
	}
	
	
	
	public static void main(String[] args) {
		int stairs;

		stairs = 3;
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_Bruteforce(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP_Optamized(stairs)));

		stairs = 4;
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_Bruteforce(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP_Optamized(stairs)));

		stairs = 5;
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_Bruteforce(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP(stairs)));
		System.out.println(String.format("steps=%s can be covered in %d ways.", stairs, count_DP_Optamized(stairs)));
}

}
