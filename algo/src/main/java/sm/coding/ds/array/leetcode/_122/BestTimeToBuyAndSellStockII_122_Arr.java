/**
 * 
 */
package sm.coding.ds.array.leetcode._122;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class BestTimeToBuyAndSellStockII_122_Arr {

	/**
Say you have an array for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Links
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/solution/ 
https://www.geeksforgeeks.org/stock-buy-sell/ 

Similar questions
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/ 
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/ 
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/ 
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/ 
 
	 * @param args
	 */
	public static int maxProfit(int[] prices) {
		int maxProfit = 0;
		/**
		 * A mathematical property is followed in this algorithm i.e.
		 * 
		 * sum of 100-1 = 99
		 * where as 
		 * sum of (50-1) + (100-50) = 49+50 = 90, which is nothing but 100 -50 +50 -1=99, as +50 and -50 cancel out each other
		 * 
		 */
		for(int i =1; i< prices.length;i++){
			if(prices[i]>prices[i-1]) {
				maxProfit+= prices[i]-prices[i-1];
			}
		}
		return maxProfit;
	}

	public static int maxProfit_Indexes(int[] prices) {
		int maxProfit = 0;
		List<Integer> buyDays = new ArrayList<>();
		List<Integer> sellDays = new ArrayList<>();
		boolean selled = true;
		for(int i =1; i< prices.length;i++){
			if(prices[i]>prices[i-1]) {				
				maxProfit+= prices[i]-prices[i-1];
				if(selled) {
					selled = false;
					buyDays.add(i-1);
				}
			}else {
				sellDays.add(i-1);
				selled = true;
			}
		}
		if(buyDays.size()>sellDays.size()) {
			sellDays.add(prices.length-1);
		}
		System.out.println(" Buy at day="+buyDays);
		System.out.println("Sell at day="+sellDays);
		return maxProfit;
	}

	public static void main(String[] args) {
		int[] arr = new int[] {1, 7, 2, 3, 6, 7, 6, 7};
//		int[] arr = new int[] {100, 180, 260, 310, 40, 535, 695};
//		int maxProfit = maxProfit(arr);
		int maxProfit = SolutionDebug.maxProfit(arr);
		System.out.println("stocks="+Arrays.stream(arr).boxed().collect(Collectors.toList())+", Max profit is "+maxProfit);
		System.out.println(" index="+IntStream.range(0, arr.length).boxed().collect(Collectors.toList()));

		System.out.println("");
		maxProfit = maxProfit_Indexes(arr);
		System.out.println("stocks="+Arrays.stream(arr).boxed().collect(Collectors.toList())+", Max profit is "+maxProfit);
		System.out.println(" index="+IntStream.range(0, arr.length).boxed().collect(Collectors.toList()));

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
	
	public static int maxProfit(int[] prices) {
		int maxProfit = 0;
		tableColumns("i" , "prices[i]>prices[i-1]", "maxProfit");
		debugColumns();
		for(int i =1; i< prices.length;i++){
			debugRow(i , " "+prices[i]+" > "+prices[i-1]+" = "+(prices[i]>prices[i-1] ? "true , "+(prices[i]-prices[i-1]) : "false"), maxProfit);
			if(prices[i]>prices[i-1]) {
				maxProfit+= prices[i]-prices[i-1];
			}
		}
		return maxProfit;
	}}
