/**
 * 
 */
package sm.coding.algo.dp.icf.fibonacci;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class Fibonacci_DP {

	
	int fib_Recursion(int n) {
		// base condition
		if(n<2) {
			return n;
		}		
		//recurse
		return fib_Recursion(n-1) + fib_Recursion(n-2);
	}
	
	int fib_Memoization(int n) {
		int[] dp = new int[n+1];
		return fib_MemoizationHelper(n, dp);
	}
	
	int fib_MemoizationHelper(int n, int[] dp) {
		if(n<2) {
			return n;
		}
		if(dp[n]!=0) {
			return dp[n];
		}
		dp[n] = fib_MemoizationHelper(n-1, dp) + fib_MemoizationHelper(n-2, dp);
		return dp[n];
	}
	
	static int fib_Iterative_DP_Array(int n) {
		// DP array type = return type of recursion function
		// DP array dimensions = number of changing variables
		// 1. init DP array, 
		// usually DP array is n+1, where additional space for base case
		int[] dp = new int[n+1];
		
		// DP array base values = recursion base condition
		// 2. base condition
		dp[0] = 0;
		dp[1] = 1;	
		
		// 3. loop
		for(int i=2; i<dp.length; i++) {
			// 4. logic
			dp[i] = dp[i-1] + dp[i-2];
		}		
		// 5. return
		return dp[dp.length-1];
	}
	
	/**
	 * Optimal solution
	 * T = O(n)
	 * S = O(1)
	 * 
	 * @param n
	 * @return
	 */
	static int fib_Iterative_DP(int n) {
		if(n<2) {
			return n;
		}
		int first = 0;
		int second = 1;
		for(int i =2; i<n; i++) {
			int third = first+second;
			first = second;
			second = third;
		}
		return first+second;
	}
	
    public static int fib_formula(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n)-Math.pow((1-sqrt5)/2,n);
        return (int)(fibn/sqrt5);
    }
	
	public static void main(String[] args) {
		
		System.out.println(6+" DP Fibonaci number is "+ SolutionDebug.fib_Iterative_DP_Array(6));
		System.out.println(6+" FR Fibonaci number is "+ fib_formula(6));
		
		for (int i =0; i<20;i++) {
			System.out.println(i+" DP Fibonaci number is "+ fib_Iterative_DP(i));
			System.out.println(i+" FR Fibonaci number is "+ fib_formula(i));
		}
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

	public static int fib_Iterative_DP_Array(int n) {
		debug("n="+n);
		// DP array type = return type of recursion function
		// DP array dimensions = number of changing variables
		// 1. init DP array, 
		// usually DP array is n+1, where additional space for base case
		int[] dp = new int[n+1];
		debug("dp length="+dp.length);
		
		// DP array base values = recursion base condition
		// 2. base condition
		dp[0] = 0;
		dp[1] = 1;	
		
		tableColumns("i:3", "dp[i-1]", "dp[i-2]", "dp[i]");
		debugColumns();
		// 3. loop
		for(int i=2; i<dp.length; i++) {
			// 4. logic
			dp[i] = dp[i-1] + dp[i-2];
			debugRow(i, dp[i-1], dp[i-2], dp[i]);
		}		
		debug(Arrays.stream(dp).boxed().collect(Collectors.toList()));
		debug(IntStream.range(0, dp.length).boxed().collect(Collectors.toList()));
		// 5. return
		return dp[dp.length-1];
	}

}
