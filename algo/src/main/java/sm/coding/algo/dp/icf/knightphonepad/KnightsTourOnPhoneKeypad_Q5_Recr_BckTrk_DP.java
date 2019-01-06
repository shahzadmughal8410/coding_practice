/**
 * 
 */
package sm.coding.algo.dp.icf.knightphonepad;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class KnightsTourOnPhoneKeypad_Q5_Recr_BckTrk_DP {

	/**
Given a phone keypad as shown below:

1 2 3
4 5 6
7 8 9
0

How many different 10-digit numbers can be formed starting from 1? The constraint is
that the movement from 1 digit to the next is similar to the movement of the Knight in a
chess game.
For eg. if we are at 1 then the next digit can be either 6 or 8 if we are at 6 then the next
digit can be 1, 7 or 0.


Repetition of digits are allowed - 1616161616 is a valid number. The problem requires
us to just give the count of 10-digit numbers and not necessarily list the numbers.
Find a polynomial time solution, based on Dynamic Programming.


Links
http://stackoverflow.com/questions/2893470/generate-10-digit-number-usinga-phone-keypad 
	 */
	
	public static List<List<Integer>> knightTourPhonePadList_Bruteforce(int length) {
		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> sofar = new ArrayList<>();
		sofar.add(1);
		knightTourPhonePadListHelper_Bruteforce(length-1, 0, 0, result, rowMoves, colMoves, phonePad, sofar);
		return result;
	}
	
	public static void knightTourPhonePadListHelper_Bruteforce(int length, int row, int col, List<List<Integer>> result, int[] rowMoves, int[] colMoves, int[][] phonePad, List<Integer> sofar) {
		if(length == 0) {
			result.add(new ArrayList<>(sofar));
			return;
		}
		
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sofar.add(phonePad[nextRow][nextCol]);
				knightTourPhonePadListHelper_Bruteforce(length-1, nextRow, nextCol, result, rowMoves, colMoves, phonePad, sofar);
				sofar.remove(sofar.size()-1);
			}
		}		
	}
	
	
	public static int knightTourPhonePadCount_Bruteforce(int length) {
		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
		int totalNumnerPossible = knightTourPhonePadCountHelper_Bruteforce(length-1, 0, 0, rowMoves, colMoves, phonePad);
		return totalNumnerPossible;
	}
	
	public static int knightTourPhonePadCountHelper_Bruteforce(int length, int row, int col, int[] rowMoves, int[] colMoves, int[][] phonePad) {
		if(length == 0) {
			return 1;
		}
		int sum = 0;
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sum += knightTourPhonePadCountHelper_Bruteforce(length-1, nextRow, nextCol, rowMoves, colMoves, phonePad);
			}
		}		
		return sum;
	}
	
	public static int knightTourPhonePadCount_Memoization(int length) {
		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
		int digits = 10;// constant 
		Integer[][] dp =new Integer[length][digits];
		int totalNumnerPossible = knightTourPhonePadCountHelper_Memoization(length-1, 0, 0, rowMoves, colMoves, phonePad, dp);
		return totalNumnerPossible;
	}
	
	public static int knightTourPhonePadCountHelper_Memoization(int length, int row, int col, int[] rowMoves, int[] colMoves, int[][] phonePad, Integer[][] dp) {
		int digit = phonePad[row][col];
		if(dp[length][digit]!=null) {
			return dp[length][digit];
		}
		if(length == 0) {
			return 1;
		}
		int sum = 0;
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sum += knightTourPhonePadCountHelper_Memoization(length-1, nextRow, nextCol, rowMoves, colMoves, phonePad, dp);
			}
		}		
		dp[length][digit] = sum;
		return dp[length][digit];
	}
	
	
	public static List<Integer>[] getMoves(){		
		int[][] phonePad = new int[][]{
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
			{-1, 0, -1}
		};

		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		List<Integer>[] moves = new ArrayList[10];
		
		for(int row =0; row<phonePad.length; row++) {
			for(int col =0; col<phonePad[0].length; col++) {
				int digit = phonePad[row][col];
				if(digit != -1) {
					List<Integer> movesForDigit = new ArrayList<>();
					for(int i=0; i<rowMoves.length; i++) {
						int nextRow = row+rowMoves[i];
						int nextCol = col+colMoves[i];
						if(isSafe(phonePad, nextRow, nextCol)) {				
							movesForDigit.add(phonePad[nextRow][nextCol]);
						}
					}
					moves[digit] =  movesForDigit;				
				}
			}
		}
		return moves;
	}
	
	public static int knightTourPhonePadCount_DP(int length) {
		int[][] dp = new int[length][10];
		for(int i =0 ; i<dp[0].length; i++) {
			dp[0][i] = 1;
		}
		List<Integer>[] moves = getMoves();
		// length loop
		for(int len = 1; len<dp.length;len++) {
			// digits loop
			for(int dig = 0; dig<dp[0].length; dig++) {	
				int sum = 0;				
				for(int digit : moves[dig]) {
					sum += dp[len-1][digit];
				}
				dp[len][dig] = sum;
			}			
		}		
		return dp[dp.length-1][dp[0].length-1];
	}

	/**
Constant space solution because:
1- digits are fixed i.e. 0-9 , 10 in total
2- only 2 rows of arrays are needed, it will not change if length is changes i.e. 10, 7 or 5

	 * @param length
	 * @return
	 */
	public static int knightTourPhonePadCount_DP_Optimized(int length) {
		int[] dp1 = new int[10];
		int[] dp2 = new int[10];
		for(int i =0 ; i<dp1.length; i++) {
			dp1[i] = 1;
		}
		List<Integer>[] moves = getMoves();
		// length loop
		for(int len = 1; len<length;len++) {
			// digits loop
			for(int dig = 0; dig<dp1.length; dig++) {	
				int sum = 0;				
				for(int digit : moves[dig]) {
					sum += dp1[digit];
				}
				dp2[dig] = sum;
			}
			// set the dp[0] with dp[1] i.e. lastly computed values
			int[] tmp =dp1;
			dp1 = dp2;
			dp2 = tmp;
		}		
		// return from d[0] as dp zero is the lastly computed
		return dp1[dp1.length-1];
	}
	public static boolean isSafe(int[][] grid, int row, int col) {
		if(row>=0 && row<grid.length && col>=0 && col<grid[0].length && grid[row][col]!=-1) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int length = 5;
		List<List<Integer>> result;
//		result = SolutionDebug.knightTourPhonePadList_Bruteforce(length);
//		System.out.println("Possible numbers with length 10 are "+result.size());
//		result.forEach(r->System.out.println(r));
//		result = knightTourPhonePad_Bruteforce(length);
//		System.out.println("Possible numbers with length "+length+" are "+result.size());
//		System.out.println("BF Possible numbers with length "+length+" are "+SolutionDebug.knightTourPhonePadList_Bruteforce(length));
//		System.out.println("MM Possible numbers with length "+length+" are "+SolutionDebug.knightTourPhonePadCount_Memoization(length));
//		System.out.println("DP Possible numbers with length "+length+" are "+SolutionDebug.knightTourPhonePadCount_DP(length));

		result = knightTourPhonePadList_Bruteforce(length);
		System.out.println("List Possible numbers with length "+length+" are "+result.size());
		System.out.println("  BF Possible numbers with length "+length+" are "+knightTourPhonePadCount_Bruteforce(length));
		System.out.println("  MM Possible numbers with length "+length+" are "+knightTourPhonePadCount_Memoization(length));
		System.out.println("  DP Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP(length));
		System.out.println(" DPO Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP_Optimized(length));
		
		length = 10;
		result = knightTourPhonePadList_Bruteforce(length);
		System.out.println("List Possible numbers with length "+length+" are "+result.size());
		System.out.println("  BF Possible numbers with length "+length+" are "+knightTourPhonePadCount_Bruteforce(length));
		System.out.println("  MM Possible numbers with length "+length+" are "+knightTourPhonePadCount_Memoization(length));
		System.out.println("  DP Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP(length));
		System.out.println(" DPO Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP_Optimized(length));

		length = 7;
		result = knightTourPhonePadList_Bruteforce(length);
		System.out.println("List Possible numbers with length "+length+" are "+result.size());
		System.out.println("  BF Possible numbers with length "+length+" are "+knightTourPhonePadCount_Bruteforce(length));
		System.out.println("  MM Possible numbers with length "+length+" are "+knightTourPhonePadCount_Memoization(length));
		System.out.println("  DP Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP(length));
		System.out.println(" DPO Possible numbers with length "+length+" are "+knightTourPhonePadCount_DP_Optimized(length));

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
		return grid(grid, null, null, "-", "-");
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
	
	static int bfCount = 0;
	public static List<List<Integer>> knightTourPhonePadList_Bruteforce(int length) {
		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1,  1,  2};
		int[] colMoves = new int[]{1, 2,  2,  1, -1, -2, -2, -1};
		
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> sofar = new ArrayList();
		sofar.add(1);
		knightTourPhonePadListHelper_Bruteforce(length-1, 0, 0, result, rowMoves, colMoves, phonePad, sofar);
		result.forEach(r->debugRecr(r));
		debugRecr("result="+result.size());
		debugRecr("bfCount="+bfCount);
		return result;
	}
	
	public static void knightTourPhonePadListHelper_Bruteforce(int length, int row, int col, List<List<Integer>> result, int[] rowMoves, int[] colMoves, int[][] phonePad, List<Integer> sofar) {
		++bfCount;
		debugRecr("length="+length+", row="+row+", col="+col+", sofar="+sofar+", bfCount="+bfCount);
		if(length == 0) {
			debugRecr("Length zero adding to result="+sofar);
			result.add(new ArrayList<>(sofar));
			return;
		}
		
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			debugRecr(i+", nextRow="+nextRow+", nextCol="+nextCol);
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sofar.add(phonePad[nextRow][nextCol]);
				debugRecr("Selected="+phonePad[row][col]+", nextRow="+nextRow+", nextCol="+nextCol);
				String actual = incrementIndent();
				knightTourPhonePadListHelper_Bruteforce(length-1, nextRow, nextCol, result, rowMoves, colMoves, phonePad, sofar);
				setIndent(actual);
				sofar.remove(sofar.size()-1);
			}
		}		
	}
	
	public static boolean isSafe(int[][] grid, int row, int col) {
		if(row>=0 && row<grid.length && col>=0 && col<grid[0].length && grid[row][col]!=-1) {
			debugRecr("Valid move: row="+row+", col="+col+", grid[row][col]="+grid[row][col]);
			return true;
		}
		debugRecr("invalid move: row="+row+", col="+col);
		return false;
	}

	static int mmCount = 0;
	public static int knightTourPhonePadCount_Memoization(int length) {
		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
		Integer[][] dp =new Integer[length][10];
		int totalPossibleNumbers = knightTourPhonePadCountHelper_Memoization(length-1, 0, 0, rowMoves, colMoves, phonePad, dp);
		debugRecr("DP\n"+grid(dp));
		debugRecr("totalPossibleNumbers="+totalPossibleNumbers);
		debugRecr("mmCount="+mmCount);
		return totalPossibleNumbers;
	}
	
	public static int knightTourPhonePadCountHelper_Memoization(int length, int row, int col, int[] rowMoves, int[] colMoves, int[][] phonePad, Integer[][] dp) {
		++mmCount;
		int digit = phonePad[row][col];
		if(dp[length][digit]!=null) {
			return dp[length][digit];
		}
		if(length == 0) {
			return 1;
		}
		int sum = 0;
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sum += knightTourPhonePadCountHelper_Memoization(length-1, nextRow, nextCol, rowMoves, colMoves, phonePad, dp);
			}
		}		
		dp[length][digit] = sum;
		return dp[length][digit];
	}
	
	public static List<Integer>[] getMoves(){		
		int[][] phonePad = new int[][]{
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
			{-1, 0, -1}
		};

		int[] rowMoves = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
		int[] colMoves = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
		List<Integer>[] moves = new ArrayList[10];
		
		for(int row =0; row<phonePad.length; row++) {
			for(int col =0; col<phonePad[0].length; col++) {
				int digit = phonePad[row][col];
				if(digit != -1) {
					List<Integer> movesForDigit = new ArrayList<>();
					for(int i=0; i<rowMoves.length; i++) {
						int nextRow = row+rowMoves[i];
						int nextCol = col+colMoves[i];
						debugRecr(row+", nextRow="+nextRow+", nextCol="+nextCol);
						if(isSafe(phonePad, nextRow, nextCol)) {				
							movesForDigit.add(phonePad[nextRow][nextCol]);
						}
					}
					debugRecr("digit="+digit+", totalMovesForDigit="+movesForDigit.size()+", movesForDigit="+movesForDigit);
					moves[digit] =  movesForDigit;				
				}
			}
		}
		for(int i=0;i<moves.length; i++) {
			debug(i+" = "+moves[i]);
		}
		return moves;
	}
	
	static int dpCount = 0;
	public static int knightTourPhonePadCount_DP(int length) {
		reset();
		Integer[][] dp = new Integer[length][10];
		for(int i =0 ; i<dp[0].length; i++) {
			dp[0][i] = 1;
		}
		
		List<Integer>[] moves = getMoves();
		
		// length loop
		for(int len = 1; len<dp.length;len++) {
			// digits loop
			for(int dig = 0; dig<dp[0].length; dig++) {	
				int sum = 0;				
				for(int digit : moves[dig]) {
					++dpCount;
					sum += dp[len-1][digit];
				}
				dp[len][dig] = sum;
			}			
			debug(len+" DP\n"+grid(dp));
		}		
		debug("dpCount="+dpCount);
		debug("Result DP\n"+grid(dp, IntStream.range(1, length+1).boxed().collect(Collectors.toList()), IntStream.range(0, 10).boxed().collect(Collectors.toList()), "length", "/digit"));
		for(int i=0;i<moves.length; i++) {
			debug(i+" = "+moves[i]);
		}
		return dp[dp.length-1][dp[0].length-1];
	}
}
