/**
 * 
 */
package sm.coding.algo.dp.icf.coinplay;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class CoinPlay_Q3_Recr_BckTrk_DP {

	
	public static int solve(int[] coins) {
		int[][] dp = new int[coins.length][coins.length];

		for (int coinsIncluded = 0; coinsIncluded < coins.length; coinsIncluded++) {
			for (int i = 0, j = coinsIncluded; j < coins.length; i++, j++) {
				// a = MV(i+2,j) - Alice chooses i Bob chooses i+1
				// b = MV(i+1,j-1)- Alice chooses i , Bob chooses j OR Alice chooses j , Bob chooses i
				// c = MV(i,j-2)- Alice chooses j , Bob chooses j-1
				int a, b, c;
				if (i + 2 <= j)
					a = dp[i + 2][j];
				else
					a = 0;
				//////////////////////////////////
				if (i + 1 <= j - 1)
					b = dp[i + 1][j - 1];
				else
					b = 0;
				//////////////////////////////////
				if (i <= j - 2)
					c = dp[i][j-2];
				else
					c = 0;
				//////////////////////////////////
				dp[i][j] = Math.max(coins[i] + Math.min(a, b), coins[j]+ Math.min(b, c));
			}
		}
		return dp[0][coins.length - 1];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] coins;
		
		coins = new int[] { 8, 15, 3, 7 };
		System.out.println("Max value="+solve(coins)+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		
		coins = new int[] { 2, 2, 2, 2 };
		System.out.println("Max value="+solve(coins)+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		
		coins = new int[] { 20, 30, 2, 2, 2, 10 };
		System.out.println("Max value="+SolutionDebug.solve(coins)+", coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));		
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
	
	public static int solve(int[] coins) {
		debug("coins="+Arrays.stream(coins).boxed().collect(Collectors.toList()));
		int[][] dp = new int[coins.length][coins.length];
		
		tableColumns("coinsIncluded", "i:3", "j:3", "i + 2 <= j :20", "dp[i + 2][j] :18", "a:3", "i + 1 <= j - 1 :24", "dp[i + 1][j - 1]:24",  "b:3", "i <= j - 2 :20", "dp[i][j-2] :18", "c:3", "dp[i][j]:15", "coins[i]:12", "coins[j]:12");
		debugColumns();
		for (int coinsIncluded = 0; coinsIncluded < coins.length; coinsIncluded++) {
			for (int i = 0, j = coinsIncluded; j < coins.length; i++, j++) {
				// a = MV(i+2,j) - Alice chooses i Bob chooses i+1
				// b = MV(i+1,j-1)- Alice chooses i , Bob chooses j OR Alice chooses j , Bob chooses i
				// c = MV(i,j-2)- Alice chooses j , Bob chooses j-1
				int a, b, c;
				if (i + 2 <= j)
					a = dp[i + 2][j];
				else
					a = 0;
				//////////////////////////////////
				if (i + 1 <= j - 1)
					b = dp[i + 1][j - 1];
				else
					b = 0;
				//////////////////////////////////
				if (i <= j - 2)
					c = dp[i][j-2];
				else
					c = 0;
				//////////////////////////////////
				dp[i][j] = Math.max(coins[i] + Math.min(a, b), coins[j]+ Math.min(b, c));
				debugRow(coinsIncluded, i, j, 
						""+i+" + 2 <= "+j+" = "+(i + 2 <= j), !(i + 2 <= j) ? "-" : "p["+i+" + 2]["+j+"] = "+(dp[i + 2][j]), a,
						""+i+" + 1 <= "+j+" - 1 = "+(i + 1 <= j - 1), !(i + 1 <= j - 1) ? "-" : "dp["+i+" + 1]["+j+" - 1] = "+(dp[i + 1][j - 1]), b, 
						""+i+" <= "+j+" - 2 = "+(i <= j - 2), !(i <= j - 2) ? "-" : "dp["+i+"]["+j+"-2] = "+(dp[i][j-2]) , c, 
								"dp["+i+"]["+j+"] = "+dp[i][j], "coins["+i+"]="+coins[i], "coins["+j+"]="+coins[j]);
			}
			debug("dp=\n"+grid(dp));
		}
		debug("dp result=\n"+grid(dp));
		return dp[0][coins.length-1];
	}
}