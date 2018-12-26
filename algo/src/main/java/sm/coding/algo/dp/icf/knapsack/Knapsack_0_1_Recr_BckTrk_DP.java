/**
 * 
 */
package sm.coding.algo.dp.icf.knapsack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class Knapsack_0_1_Recr_BckTrk_DP {

	/**
Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value 
in the knapsack. In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and 
weights associated with n items respectively. Also given an integer W which represents knapsack capacity, 
find out the maximum value subset of val[] such that sum of the weights of this subset is smaller 
than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

    int val[] = {60, 100, 120}; 
    int wt[] = {10, 20, 30}; 
    int  W = 50; 
    
max weight = 7
val = [1, 4, 5, 7]
 wt = [1, 3, 4, 5]

	 */
	public static int knapsack_BruteForce(int val[], int wt[], int maxWeight){
		return knapsack_BruteForce(val, wt, maxWeight, wt.length);
	}
	
	public static int knapsack_BruteForce(int val[], int wt[], int maxWeight, int index){
		if(maxWeight==0 || index==0) {
			return 0;
		}
		// the item we pick has the weight that can not fit in knapsack, skip item
		if(wt[index-1] > maxWeight) {
			return knapsack_BruteForce(val, wt, maxWeight, index-1);
		}
		else {
			// if item can fit in the knapsack, try including the item as well as skipping the item
			return Math.max(knapsack_BruteForce(val, wt, maxWeight, index-1), knapsack_BruteForce(val, wt, maxWeight-wt[index-1], index-1) +val[index-1] );	
		}
	}
	
	public static int knapsack_DP(int val[], int wt[], int maxWeight){
		if(maxWeight ==0) {
			return 0;
		}
		int[][] dp = new int[val.length+1][maxWeight+1];
		
		for(int i=1; i<dp.length;i++) {
			for(int j=1; j<dp[0].length;j++) {
				if(wt[i-1]>j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]] + val[i-1]);
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
	}
	
	
	public static void main(String[] args) {
	    int val[] = {60, 100, 120}; 
	    int wt[] = {10, 20, 30}; 
	    int maxWeight = 50; 
	    
	    System.out.println("BF="+knapsack_BruteForce(val, wt, maxWeight));
	    System.out.println("DP="+knapsack_DP(val, wt, maxWeight));

	    val = new int[]{1, 4, 5, 7}; 
	    wt = new int[]{1, 3, 4, 5}; 
	    maxWeight = 7; 
	    
	    System.out.println("BF="+SolutionDebug.knapsack_BruteForce(val, wt, maxWeight));
	    System.out.println("DP="+SolutionDebug.knapsack_DP(val, wt, maxWeight));
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
		return grid(grid, null, null, "*", "@");
	}

	public static String grid(int grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
		int padding = 5;
		StringBuilder output = new StringBuilder("\n");
		// output index of columns
		output.append("index");
		output.append( String.format(" | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", "-") );
		int underline = 0;
		for(int i =0; i<grid[0].length; i++) {
			output.append( String.format(" | %1$-"+padding+"s ", i) );
		}
		output.append("|\n");
		underline = output.length();
		
		if(null!=colHeader) {
			output.append("-");
			output.append( String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", rowHeading+colHeading) );
			for(int i =0; i<grid[0].length; i++) {
				output.append( String.format(" | %1$-"+padding+"s ", colHeader.get(i)) );
			}
		}

		output.append("|\n");
		IntStream.range(0, underline).forEach(i->output.append("-"));
		output.append("\n");
		
		for(int r=0; r<grid.length; r++) {
			// output index of rows
			output.append( r+ String.format("     | %1$-"+Math.max(padding, rowHeading.length()+colHeading.length())+"s ", (rowHeader!=null ? rowHeader.get(r) : "" ) )  );
			
			// grid data
			for(int c=0; c<grid[r].length; c++) {
				output.append( String.format(" | %1$-"+padding+"s ", grid[r][c]) );
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
	public static List asList(int [] list, int padding, Object value) {
		List l = new ArrayList<>();
		for(int i=0; i<padding; i++) {
			l.add(value);
		}
		l.addAll(Arrays.stream(list).boxed().collect(Collectors.toList()));
		return l;
	}
	
	public static int knapsack_BruteForce(int val[], int wt[], int maxWeight){
		debugRecr("val="+Arrays.stream(val).boxed().collect(Collectors.toList()));
		debugRecr(" wt="+Arrays.stream(wt).boxed().collect(Collectors.toList()));
		debugRecr("maxWeight="+maxWeight);
		return knapsack_BruteForce(val, wt, maxWeight, wt.length);
	}
	
	public static int knapsack_BruteForce(int val[], int wt[], int maxWeight, int index){
		debugRecr("index="+index);
		if(maxWeight==0 || index==0) {
			debugRecr("Returning Zero");
			return 0;
		}
		debugRecr("wt[index-1] > maxWeight => wt["+index+"-1] > "+maxWeight+" => "+wt[index-1]+">"+maxWeight+" => " +(wt[index-1] > maxWeight));
		// the item we pick has the weight that can not fit in knapsack, skip item 
		if(wt[index-1] > maxWeight) {
			debugRecr("Cannot pick this weight, exceding knapsack maxWeight="+maxWeight+", "+wt[index-1]);
			String actual = incrementIndent();
			int skip = knapsack_BruteForce(val, wt, maxWeight, index-1);
			setIndent(actual);
			return skip;
		}
		else {
			debugRecr("Weight within the knnpsack capacity val["+index+"-1]="+val[index-1]);
			// if item can fit in the knapsack, try including the item as well as skipping the item
			debugRecr("Trying without val["+index+"-1]="+val[index-1]);
			String actual = incrementIndent();
			int without = knapsack_BruteForce(val, wt, maxWeight, index-1);
			setIndent(actual);
			debugRecr("without="+without);
			
			debugRecr("Trying with val["+index+"-1]="+val[index-1]);
			actual = incrementIndent();
			int with = knapsack_BruteForce(val, wt, maxWeight-wt[index-1], index-1)+ val[index-1];
			setIndent(actual);
			debugRecr("with="+with);
			int max = Math.max(without, with);
			debugRecr("max="+max);
			return max;	
		}
	}
	
	public static int knapsack_DP(int val[], int wt[], int maxWeight){
		debug("val="+Arrays.stream(val).boxed().collect(Collectors.toList()));
		debug(" wt="+Arrays.stream(wt).boxed().collect(Collectors.toList()));
		debug("maxWeight="+maxWeight);
		if(maxWeight ==0) {
			return 0;
		}
		int[][] dp = new int[val.length+1][maxWeight+1];
		
		for(int i=1; i<dp.length;i++) {
			for(int j=1; j<dp[0].length;j++) {
				if(wt[i-1]>j) {
					dp[i][j] = dp[i-1][j];
				}else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-wt[i-1]] + val[i-1]);
				}
			}
		}
		debug("DP "+grid(dp, asList(val, 1, '-'), asList(IntStream.range(1, maxWeight+2).toArray(), 1, '-'), "val", "/mxWt"));
		debug("maxValue="+dp[val.length][maxWeight]);
		return dp[val.length][maxWeight];
	}
	
}
