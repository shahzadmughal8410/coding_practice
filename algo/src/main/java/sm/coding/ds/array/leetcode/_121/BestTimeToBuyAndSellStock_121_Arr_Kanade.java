/**
 * 
 */
package sm.coding.ds.array.leetcode._121;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class BestTimeToBuyAndSellStock_121_Arr_Kanade {

	/**
121 Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.
Example 1:
Input: [7, 1, 5, 3, 6, 4]
Output: 5

max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)


Example 2:
Input: [7, 6, 4, 3, 1]
Output: 0

In this case, no transaction is done, i.e. max profit = 0.

Links
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/solution/
https://www.geeksforgeeks.org/largest-sum-contiguous-subarray/ 
https://leetcode.com/problems/best-time-to-buy-and-sell-stock/discuss/39244/concise-solution-on 

Similar questions
https://leetcode.com/problems/maximum-subarray/description/

Submission
https://leetcode.com/submissions/detail/189264993/
You are here! 
Your runtime beats 99.91 % of java submissions.

	 * @param args
	 */
	public static int maxProfit(int[] arr) { 
		int currentMax = 0;
		int maxSoFar = 0;
		for(int i=1;i<arr.length;i++) { // in kadane i=0 here i=1
			// 	its a mathematical property, 
			// if you do the differences sum for series of numbers i.w. i - (i-1), 
			// it will give you the difference of first and last number
			currentMax += arr[i]-arr[i-1];// same as kadane currentMax += arr[i], here += arr[i]-arr[i-1]

			currentMax = Math.max(0, currentMax); // if less then zero than set reset to zero
			maxSoFar = Math.max(currentMax, maxSoFar);
		}
		return maxSoFar;
	}
	
	public static void main(String[] args) {
		int [] arr = new int[] {7, 1, 5, 3, 6, 4};
//		int maxProfit = maxProfit(arr);
		int maxProfit = SolutionDebug.maxProfit(arr);
		
		System.out.println("Max profit is "+maxProfit+"="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {7, 6, 4, 3, 1};
		maxProfit = maxProfit(arr);		
		System.out.println("Max profit is "+maxProfit+"="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

	}

}

class SolutionDebug{

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
	
	public static int maxProfit(int[] arr) { 
		int currentMax = 0;
		int maxSoFar = 0;
		tableColumns("maxSoFar", "currentMax", "i", "X=arr[i]-arr[i-1]");
		debugColumns();
		for(int i=1;i<arr.length;i++) { // in kadane i=0 here i=1
			debugRow(maxSoFar, currentMax, i, " "+(arr[i]-arr[i-1])+" = "+arr[i]+" - "+arr[i-1]);
			currentMax += arr[i]-arr[i-1];// same as kadane currentMax += arr[i], here += arr[i]-arr[i-1]

			currentMax = Math.max(0, currentMax); // if less then zero than set reset to zero
			maxSoFar = Math.max(currentMax, maxSoFar);
		}
		return maxSoFar;
	}
}
