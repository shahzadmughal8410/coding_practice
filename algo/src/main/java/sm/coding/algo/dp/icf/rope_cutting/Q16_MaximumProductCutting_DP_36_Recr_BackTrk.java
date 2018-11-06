/**
 * 
 */
package sm.coding.algo.dp.icf.rope_cutting;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class Q16_MaximumProductCutting_DP_36_Recr_BackTrk {

	/**
Given a rope of length n meters, cut the rope in different parts of integer lengths in a way that maximizes product of lengths of all parts. You must make at least one cut. Assume that the length of rope is more than 2 meters.
Examples:

Input: n = 2
Output: 1 (Maximum obtainable product is 1*1)

Input: n = 3
Output: 2 (Maximum obtainable product is 1*2)

Input: n = 4
Output: 4 (Maximum obtainable product is 2*2)

Input: n = 5
Output: 6 (Maximum obtainable product is 2*3)

Input: n = 10
Output: 36 (Maximum obtainable product is 3*3*4)
	 */
	public static int maxProd_Bruteforce(int n) {
		if(n<2) {
			return 0;			
		}		
		int max = 0;
		for(int i=1; i<n; i++) {
			max = Math.max(max, 
				  Math.max(i* (n-i), 
				  maxProd_Bruteforce(n-i) * i));			
		}		
		return max;
	}

	public static int maxProd_Memoization(int n) {
		int[] dp = new int[n+1];
		return maxProdHelper_Memoization(n, dp);
	}
	
	public static int maxProdHelper_Memoization(int n, int[] dp) {
		if(dp[n]!=0) {
			return dp[n];
		}
		if(n<2) {
			return dp[n];			
		}
		int max = 0;
		for(int i=1; i<n; i++) {
			max = Math.max(max, 
				  Math.max(i* (n-i), 
				  maxProdHelper_Memoization(n-i, dp) * i));			
		}		
		dp[n] = max;
		return dp[n];
	}
	
	public static int maxProd_DP(int n) {
		if(n<2) {
			return 0;			
		}				
		int[] dp = new int[n+1];		
		int max = 0;
		for(int i =1; i<dp.length; i++) {
//			for(int j=1; j<=i; j++) { 
			// above works fine, but following is better, 
			// actually after i/2 it just copies the same value to rest of dp array, 
			// which gets override again in next iteration
			for(int j=1; j<=i/2; j++) { 
				max = Math.max(max, 
					  Math.max( j* (i-j), 
					  dp[i-j] * j));			
			}		
			dp[i] = max;
		}
		return dp[dp.length-1];
	}

	public static int maxProd_Formula_Optimal(int n) {
		if(n<2) {
			return 0;
		}else if(n==2) {
			return 1;
		}else if(n==3) {
			return 2;
		}
		
		int result = 1;
		while(n>4) {
			n-=3;
			result*=3;
		}
		
		return n*result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int n = 2;
		System.out.println(n+"  BF Maximum Product is " + maxProd_Bruteforce(n)); 
		System.out.println(n+"  MM Maximum Product is " + maxProd_Memoization(n)); 
		System.out.println(n+"  DP Maximum Product is " + maxProd_DP(n)); 
		System.out.println(n+"  OP Maximum Product is " + maxProd_Formula_Optimal(n)); 

		n = 3;
		System.out.println(n+"  BF Maximum Product is " + maxProd_Bruteforce(n)); 
		System.out.println(n+"  MM Maximum Product is " + maxProd_Memoization(n)); 
		System.out.println(n+"  DP Maximum Product is " + maxProd_DP(n)); 
		System.out.println(n+"  OP Maximum Product is " + maxProd_Formula_Optimal(n)); 

		n = 4;
		System.out.println(n+"  BF Maximum Product is " + maxProd_Bruteforce(n)); 
		System.out.println(n+"  MM Maximum Product is " + maxProd_Memoization(n)); 
		System.out.println(n+"  DP Maximum Product is " + maxProd_DP(n)); 
		System.out.println(n+"  OP Maximum Product is " + maxProd_Formula_Optimal(n)); 

		n = 6;
//		System.out.println(n+" Maximum Product is " + maxProd(n)); 
		System.out.println(n+"  BF Maximum Product is " + SolutionDebug.maxProd_Bruteforce(n)); 
		System.out.println(n+"  MM Maximum Product is " + maxProd_Memoization(n)); 
		System.out.println(n+"  DP Maximum Product is " + SolutionDebug.maxProd_DP(n)); 
		System.out.println(n+"  OP Maximum Product is " + maxProd_Formula_Optimal(n)); 

		n = 15;
		System.out.println(n+"  BF Maximum Product is " + maxProd_Bruteforce(n)); 
		System.out.println(n+"  MM Maximum Product is " + maxProd_Memoization(n)); 
		System.out.println(n+"  DP Maximum Product is " + maxProd_DP(n)); 
		System.out.println(n+"  OP Maximum Product is " + maxProd_Formula_Optimal(n)); 

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

	public static String grid(int grid[][]) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("-  ");
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		IntStream.range(0, output.length()).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append(r+"->");
			
			// grid data
			for(int c=0; c<grid[r].length; c++) {
				output.append( String.format(" | %1$-"+padding+"s ", grid[r][c]) );
			}
			output.append("|\n");
		}
		return output.toString();
	}

	static int count=0;
	public static int maxProd_Bruteforce(int n) {
		debugRecr("n="+n);
		if(n<2) {
			debugRecr("n is less than 2 returning zero, n="+n +", count="+(++count));
			return 0;			
		}
		
		int max = 0;
		for(int i=1; i<n; i++) {
			int b = i* (n-i);
			String actual = incrementIndent();
			int c = maxProd_Bruteforce(n-i) * i;
			setIndent(actual);
			
			debugRecr("i="+i+", max="+max+", i* (n-i) => "+i+"* ("+n+"-"+i+")="+b+", maxProd(n-i) * i => maxProd("+n+"-"+i+") * "+i+" => "+c+" * "+i+" = "+(c*i));
			max = Math.max(max, 
				  Math.max(b,c 
				  ));			
		}		
		debugRecr("returning max="+max);
		debugRecr("count="+ (++count));
		return max;
	}
	
	public static int maxProd_DP(int n) {
		reset();
		debug("n="+n);
		if(n<2) {
			debug("n<2, returning zero.");
			return 0;			
		}				
		int[] dp = new int[n+1];		
		int max = 0;
		tableColumns("i:3", "j:3", "i/2:7", "j* (i-j):12", "dp[i-j] * j:17", "max", "oldMax", "dp[i]:8");
		debugColumns();
		for(int i =1; i<dp.length; i++) {
//			for(int j=1; j<=i; j++) { // this works fine, but following is better
			for(int j=1; j<=i/2; j++) { 
				int oldMax = max;
				max = Math.max(max, 
					  Math.max( j* (i-j), 
					  dp[i-j] * j));			
				debugRow(i ,j ,i+"/2 ="+(i/2), j+"* ("+i+"-"+j+") = "+(j* (i-j)), "dp["+i+"-"+j+"] * "+j+" = "+(dp[i-j] * j), max, oldMax, "dp["+i+"]="+max);
			}		
			dp[i] = max;
			debug("\ni="+i+" DP "+Arrays.stream(dp).boxed().collect(Collectors.toList())+"\n");
		}
		return dp[dp.length-1];
	}
}
