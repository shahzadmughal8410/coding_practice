/**
 * 
 */
package sm.coding.algo.dp.icf.buyandsellwine;

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
public class QTest_MaxProfitBuyAndSellWine_Recr_Bck_Trk_DP {

	/**
Maximum profit from sale of wines
Given n wines in a row, with integers denoting the cost of each wine respectively. 
Each year you can sale the first or the last wine in the row. However, 
the price of wines increases over time. Let the initial profits from the 
wines be P1, P2, P3…Pn. On the Yth year, the profit from the ith wine will be Y*Pi. 
For each year, your task is to print “beg” or “end” denoting whether first or last wine should be sold. 
Also, calculate the maximum profit from all the wines.
	 */
	
	
    /**
     * Description: Backtracking function to find maximum profit.
     * @param year : starts from 1 to N-1
     * @param be : beginning of price array
     * @param en : end of price array
     * @return : maximized profit
     */
    public static int maxProfit_Bruteforce(int year, int[] prices){
    		return maxProfitHelper_Bruteforce(1, 0, prices.length-1, prices);
    }
    public static int maxProfitHelper_Bruteforce(int year, int beg, int end, int[] prices){
        if(beg>end) {
            return 0;
        }
        int begProfit = maxProfitHelper_Bruteforce(year+1, beg+1, end, prices)+year*prices[beg];
        int endProfit = maxProfitHelper_Bruteforce(year+1, beg, end-1, prices) + year*prices[end];
        int max = Math.max(begProfit,endProfit);
        return max;
    }

    public static int maxProfit_Memoization(int year, int[] prices){
    		Integer[][] dp = new Integer[prices.length][prices.length];
    		return maxProfit_Memoization(1, 0, prices.length-1, prices, dp);
    }

    public static int maxProfit_Memoization(int year, int beg, int end, int[] prices, Integer[][] dp){
        if(beg > end) {
            return 0;
        }

        if(dp[beg][end] != null) {
            return dp[beg][end];  
        }

        int begProfit = maxProfit_Memoization(year+1, beg+1, end, prices, dp) + year*prices[beg];
        int endProfit = maxProfit_Memoization(year+1, beg, end-1, prices, dp)+year*prices[end];

        dp[beg][end] = Math.max(begProfit,endProfit);
        return dp[beg][end];
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int prices[] = new int[]{1,4,2,3};
		System.out.println("BF Max profit is ="+maxProfit_Bruteforce(prices.length, prices));
		System.out.println("MM Max profit is ="+maxProfit_Memoization(prices.length, prices));
		
		prices = new int[]{ 2, 4, 6, 2, 5 }; 
//		System.out.println("BF Max profit is ="+maxProfit_Bruteforce(prices.length, prices));
//		System.out.println("MM Max profit is ="+maxProfit_Memoization(prices.length, prices));
		System.out.println("BF Max profit is ="+SolutionDebug.maxProfit_Bruteforce(prices.length, prices));
		System.out.println("MM Max profit is ="+SolutionDebug.maxProfit_Memoization(prices.length, prices));

		prices = new int[]{ 2, 4, 6, 2, 5, 6, 3, 4 }; 
		System.out.println("BF Max profit is ="+maxProfit_Bruteforce(prices.length, prices));
		System.out.println("MM Max profit is ="+maxProfit_Memoization(prices.length, prices));

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

	public static String grid(Integer grid[][]) {
		return grid(grid, null, null, "*", "@");
	}

	public static String grid(Integer grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
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
	
	public static List asList(Object [] list, int padding, Object value) {
		List l = new ArrayList<>();
		for(int i=0; i<padding; i++) {
			l.add(value);
		}
		l.addAll(Arrays.asList(list));
		return l;
	}
	
	
    public static int maxProfit_Bruteforce(int year, int[] prices){
    		debugRecr("year="+year+", prices="+Arrays.stream(prices).boxed().collect(Collectors.toList()));
    		int max = maxProfitHelper_Bruteforce(1, 0, prices.length-1, prices);
    		debugRecr("max="+max+", bfCount="+bfCount);
		return max;
	}
    static int bfCount = 0;
	public static int maxProfitHelper_Bruteforce(int year, int beg, int end, int[] prices){
		++bfCount;
		debugRecr("year="+year+", beg="+beg+", end="+end+", bfCount="+bfCount);
	    if(beg>end) {
	    		debugRecr("Base case, beg>end => "+beg+">"+end+" = "+(beg>end));
	        return 0;
	    }
	    String actual = incrementIndent();
	    int begProfit = maxProfitHelper_Bruteforce(year+1, beg+1, end, prices)+year*prices[beg];
	    int endProfit = maxProfitHelper_Bruteforce(year+1, beg, end-1, prices) + year*prices[end];
	    setIndent(actual);
	    int max = Math.max(begProfit,endProfit);
	    debugRecr("begProfit="+begProfit+", endProfit="+endProfit+", max="+max);
	    return max;
	}
	
    public static int maxProfit_Memoization(int year, int[] prices){
    		reset();
		debugRecr("year="+year+", prices="+Arrays.stream(prices).boxed().collect(Collectors.toList()));
		Integer[][] dp = new Integer[prices.length][prices.length];
		int max = maxProfit_Memoization(1, 0, prices.length-1, prices, dp);
		debugRecr("max="+max+", mmCount="+mmCount);
		debug("Dp result\n"+grid(dp, Arrays.stream(prices).boxed().collect(Collectors.toList()), Arrays.stream(prices).boxed().collect(Collectors.toList()), "prices", ""));
		return max;
	}
    static int mmCount = 0;
	public static int maxProfit_Memoization(int year, int beg, int end, int[] prices, Integer[][] dp){
		++mmCount;
		debugRecr("year="+year+", beg="+beg+", end="+end+", mmCount="+mmCount);
	
		debugRecr("year="+year+", beg="+beg+", end="+end);
	    if(beg > end) {
	    		debugRecr("Base case, beg>end => "+beg+">"+end+" = "+(beg>end));
	    		return 0;
	    }
	
	    if(dp[beg][end] != null) {
	        return dp[beg][end];  
	    }
	
	    String actual = incrementIndent();
	    int begProfit = maxProfit_Memoization(year+1, beg+1, end, prices, dp) + year*prices[beg];
	    int endProfit = maxProfit_Memoization(year+1, beg, end-1, prices, dp)+year*prices[end];
	    setIndent(actual);

	    if(dp[beg][end] != null) {
	    		debugRecr("Reassigning dp[beg][end], previoud value = "+dp[beg][end]);
	    }
	    dp[beg][end] = Math.max(begProfit,endProfit);
	    debugRecr("begProfit="+begProfit+", endProfit="+endProfit+", dp["+beg+"]["+end+"] ="+dp[beg][end]);
	    return dp[beg][end];
	}

}
