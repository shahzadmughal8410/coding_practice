/**
 * 
 */
package sm.coding.algo.dp.leetcode._063;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class UniquePathsII_063_Recr_BckTrk_DP {

	/**
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?



An obstacle and empty space is marked as 1 and 0 respectively in the grid.

Note: m and n will be at most 100.

Example 1:

Input:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
Output: 2
Explanation:
There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right


Submission
https://leetcode.com/submissions/detail/179741051/
Time Limit Exceeded

	 * @param grid
	 * @return
	 */
	
    public static int uniquePathsWithObstacles_BruteForce(int[][] grid) {
        return uniquePathsWithObstaclesHelper_BruteForce(grid, grid.length-1, grid[0].length-1);
    }

    public static int uniquePathsWithObstaclesHelper_BruteForce(int[][] grid, int row, int col) {
    		if(row==0 && col==0) {
    			// if first cell is not an obstacle then 1 possible solution is found
    			return grid[row][col]==0 ? 1 : 0; //here return value is count Zero or One, not obstacle or space
    		}    		
    		if(grid[row][col]==0) {
    			int left = isValid(grid, row, col-1) ? uniquePathsWithObstaclesHelper_BruteForce(grid, row, col-1) : 0;
    			int up = isValid(grid, row-1, col) ? uniquePathsWithObstaclesHelper_BruteForce(grid, row-1, col) : 0;
    			return left + up; // as we are starting from end of grid so moves will be inversed
    		}    		
    		return 0;
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/179741607/
You are here! 
Your runtime beats 2.70 % of java submissions.
     */
    static int memoCount = 0;
    public static int uniquePathsWithObstacles_Memoization(int[][] grid) {
	    	memoCount = 0;
	    	int[][] dp = new int[grid.length+1][grid[0].length+1];
        return uniquePathsWithObstaclesHelper_Memoization(grid, grid.length-1, grid[0].length-1, dp);
    }

    public static int uniquePathsWithObstaclesHelper_Memoization(int[][] grid, int row, int col, int[][] dp) {
    		++memoCount;
	    	if(dp[row][col]>0) {
	    		return dp[row][col];
	    	}
    		if(row==0 && col==0) {
    			// if first cell is not an obstacle then 1 possible solution is found
    			dp[row][col] = grid[row][col]==0 ? 1 : 0;
    			return dp[row][col];
    		}    		
    		if(grid[row][col]==0) {
    			int right = isValid(grid, row, col-1) ? uniquePathsWithObstaclesHelper_Memoization(grid, row, col-1, dp) : 0;
    			int down = isValid(grid, row-1, col) ? uniquePathsWithObstaclesHelper_Memoization(grid, row-1, col, dp) : 0;
    			dp[row][col] = right + down; 
    			return dp[row][col];
    		}    		
    		dp[row][col] = 0;
    		return dp[row][col];
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/179747929/
You are here! 
Your runtime beats 49.81 % of java submissions.
     * @param grid
     * @return
     */
    public static int uniquePathsWithObstacles_DP(int[][] grid) {
    		int[][] dp = new int[grid.length+1][grid[0].length+1];
    		dp[0][1] = 1; // dp[1][0] = 1; either of them can be used, this is to make dp[1][1]=1, means there is only 1 way to reach the dp[1][1] which is hte starting point, as dp is row+1,col+1     		
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				if(grid[i-1][j-1]==0) {
					int left = dp[i][j-1];
					int up = dp[i-1][j];
					dp[i][j] = left + up;// as we are starting from previous values of grid so moves will be inversed 
				}
			}
		}
		return dp[dp.length-1][dp[0].length-1];
    }

	/**
Submission
https://leetcode.com/submissions/detail/179798282/
You are here! 
Your runtime beats 100.00 % of java submissions.     
	 * @param grid
	 * @return
	 */
    public static int uniquePathsWithObstacles_DP_Optimized(int[][] grid) {
		int[] dp = new int[grid[0].length+1];
		dp[1] = grid[0][0]==0? 1 : 0;
		for(int i=0; i<grid.length; i++) {
			for(int j=1; j<dp.length; j++) {
				if(grid[i][j-1]==0) {
					int right = dp[j-1];
					dp[j] += right;
				}
				else {
					dp[j]=0;
				}
			}
		}
		return dp[dp.length-1];
    }

    public static boolean isValid(int[][] grid, int row, int col) {
    		if(row>=0 && col>=0) {
    			return true;
    		}
    		return false;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] grid; 
		grid = new int[][] {
							  {0,0,0},
							  {0,1,0},
							  {0,0,0}
							};
		System.out.println(" BF Total possible paths="+uniquePathsWithObstacles_BruteForce(grid));
		System.out.println(" MM Total possible paths="+uniquePathsWithObstacles_Memoization(grid));
		System.out.println(" DP Total possible paths="+uniquePathsWithObstacles_DP(grid));
		System.out.println("DPO Total possible paths="+uniquePathsWithObstacles_DP_Optimized(grid));

		grid = new int[][] {
			  {0,0,0},
			  {0,0,0},
			  {0,0,0}
			};
//			System.out.println(" BF Total possible paths="+uniquePathsWithObstacles_BruteForce(grid));
			System.out.println(" BF Total possible paths="+SolutionDebug.uniquePathsWithObstacles_BruteForce(grid));
			System.out.println(" MM Total possible paths="+uniquePathsWithObstacles_Memoization(grid));
//			System.out.println(" DP Total possible paths="+uniquePathsWithObstacles_DP(grid));
			System.out.println(" DP Total possible paths="+SolutionDebug.uniquePathsWithObstacles_DP(grid));
//			System.out.println("DPO Total possible paths="+uniquePathsWithObstacles_DP_Optimized(grid));
			System.out.println("DPO Total possible paths="+SolutionDebug.uniquePathsWithObstacles_DP_Optimized(grid));

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
	
    public static boolean isValid(int[][] grid, int row, int col) {
		if(row>=0 && col>=0) {
			return true;
		}
		return false;
    }
    
    static int bfCount = 0;
    public static int uniquePathsWithObstacles_BruteForce(int[][] grid) {
    		bfCount = 0;
        return uniquePathsWithObstaclesHelper_BruteForce(grid, grid.length-1, grid[0].length-1);
    }

    public static int uniquePathsWithObstaclesHelper_BruteForce(int[][] grid, int row, int col) {
    		++bfCount;
    		debugRecr("row="+row+", col="+col+", bfCount="+bfCount);
    		if(row==0 && col==0) {
    			debugRecr("Grid exahuasted returning, grid["+row+"]["+col+"]="+grid[row][col]+", return value="+(grid[row][col]==0 ? 1 : 0));
    			// if first cell is not an obstacle then 1 possible solution is found
    			return grid[row][col]==0 ? 1 : 0;
    		}    		
    		debugRecr("grid["+row+"]["+col+"]="+grid[row][col]);
    		if(grid[row][col]==0) {
    			String actual = incrementIndent();
    			int right = isValid(grid, row, col-1) ? uniquePathsWithObstaclesHelper_BruteForce(grid, row, col-1) : 0;
    			int down = isValid(grid, row-1, col) ? uniquePathsWithObstaclesHelper_BruteForce(grid, row-1, col) : 0;
    			setIndent(actual);
    			debugRecr("right="+right+", down="+down+", returning="+(right + down));
    			return right + down;
    		}    		
    		debugRecr("Returning zero.");
    		return 0;
    }
    
    public static int uniquePathsWithObstacles_DP(int[][] grid) {
    		reset();
    		debug("Grid\n"+grid(grid));
		int[][] dp = new int[grid.length+1][grid[0].length+1];
		dp[0][1] = 1;
		debug("Initial DP with Defaults\n"+grid(dp));
		
		tableColumns("i:3", "j:3", "grid[i-1][j-1]:34", "dp[i][j-1]:28", "dp[i-1][j]:26", "dp[i][j]:14");
		debugColumns();
		for(int i=1; i<dp.length; i++) {
			for(int j=1; j<dp[0].length; j++) {
				debugRow(i, j, "grid["+i+"-1]["+j+"-1] => grid["+(i-1)+"]["+(j-1)+"] = "+grid[i-1][j-1],  !(grid[i-1][j-1]==0) ? "-" : "dp["+i+"]["+j+"-1] => dp["+i+"]["+(j-1)+"] = "+dp[i][j-1], 
						 !(grid[i-1][j-1]==0) ? "-" : "dp["+i+"-1]["+j+"] => dp["+(i-1)+"]["+j+"] = "+dp[i-1][j],  !(grid[i-1][j-1]==0) ? "-" : "dp["+i+"]["+j+"]="+(dp[i][j-1] + dp[i-1][j]));
				if(grid[i-1][j-1]==0) {
					int right = dp[i][j-1];
					int down = dp[i-1][j];
					dp[i][j] = right + down; 
				}
				// not needed as by default its zero
	//			else {
	//				dp[i][j] = 0;
	//			}
			}
			debug(i+" DP\n"+grid(dp));
		}
		debug("Result DP\n"+grid(dp));
		debug("Returning dp[dp.length-1][dp[0].length-1] =" +(dp[dp.length-1][dp[0].length-1]));
		return dp[dp.length-1][dp[0].length-1];
	}
    
    public static int uniquePathsWithObstacles_DP_Optimized(int[][] grid) {
    		reset();
		int[] dp = new int[grid[0].length+1];
		dp[1] = grid[0][0]==0? 1 : 0;
		tableColumns("i:3", "j:3", "grid[i][j-1]:32", "dp[j-1]:20", "dp[j]:12");
		debugColumns();
		for(int i=0; i<grid.length; i++) {
			for(int j=1; j<dp.length; j++) {
				debugRow(i, j, "grid["+i+"]["+j+"-1] => grid["+i+"]["+(j-1)+"] = "+grid[i][j-1], !(grid[i][j-1]==0) ? "-" : "dp["+j+"-1] => dp["+(j-1)+"] = "+dp[j-1], 
						!(grid[i][j-1]==0) ? "dp["+j+"] = 0" : "dp["+j+"] = "+dp[j]+dp[j-1] );
				if(grid[i][j-1]==0) {
					int right = dp[j-1];
					dp[j] += right;
				}
				else {
					dp[j]=0;
				}
			}
			debug(i+" DP "+Arrays.stream(dp).boxed().collect(Collectors.toList()));
		}
		debug("Result DP "+Arrays.stream(dp).boxed().collect(Collectors.toList()));
		return dp[dp.length-1];
    }

}
