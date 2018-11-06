/**
 * 
 */
package sm.coding.algo.dp.leetcode._064;

import java.util.regex.Pattern;

/**
 * @author shahzadmughal8410
 *
 */
public class MinimumPathSum_Recr_BckTrk_DP_064_2Moves {

	/**
Submission
https://leetcode.com/submissions/detail/176113777/
Time Limit Exceeded
Last executed input:
[
[7,1,3,5,8,9,9,2,1,9,0,8,3,1,6,6,9,5],
[9,5,9,4,0,4,8,8,9,5,7,3,6,6,6,9,1,6],
[8,2,9,1,3,1,9,7,2,5,3,1,2,4,8,2,8,8],
[6,7,9,8,4,8,3,0,4,0,9,6,6,0,0,5,1,4],
[7,1,3,1,8,8,3,1,2,1,5,0,2,1,9,1,1,4],
[9,5,4,3,5,6,1,3,6,4,9,7,0,8,0,3,9,9],
[1,4,2,5,8,7,7,0,0,7,1,2,1,2,7,7,7,4],
[3,9,7,9,5,8,9,5,6,9,8,8,0,1,4,2,8,2],
[1,5,2,2,2,5,6,3,9,3,1,7,9,6,8,6,8,3],
[5,7,8,3,8,8,3,9,9,8,1,9,2,5,4,7,7,7],
[2,3,2,4,8,5,1,7,2,9,5,2,4,2,9,2,8,7],
[0,1,6,1,1,0,0,6,5,4,3,4,3,7,9,6,1,9]]
	 * @param grid
	 * @return
	 */
    public static int minPathSum_BruteForce(int[][] grid) {
    		int[] sum = new int[] {Integer.MAX_VALUE};
    		int[][] moves = new int[][] { {0,1}, {1,0}};
//    		int[] rows = new int[] {0  1}; this can be used as well for moves
//    		int[] cols = new int[] {1, 0};
    		minPathSum_Helper(grid, sum, 0, 0, moves, 0, "");
//    		System.out.println("count="+count);
    		return sum[0];
    }
//    static int count;
    public static void minPathSum_Helper(int[][] grid, int[] sum, int r, int c, int[][] moves, int sofar, String items) {
		// mark visited, Not needed as we are moving in forward and back direction, NO backward movement
    	
    		// process node
    		sofar+=grid[r][c];
    		items+=grid[r][c]+", " ;
    		
		if(r==grid.length-1 && c==grid[0].length-1) {
//			++count;
    			if(sofar < sum[0]) {
    				sum[0] = sofar;
    				System.out.println(items);
    			}
    		}
    		
    		for(int i =0; i<moves.length; i++) {
    			int newR = r+moves[i][0];
    			int newC = c+moves[i][1];
    			if(isValid(newR, newC, grid)) {
    				minPathSum_Helper(grid, sum, newR, newC, moves, sofar, items);
    			}
    		}
    }
    
    public static boolean isValid(int r, int c, int[][] grid) {
    		if(r<grid.length && c<grid[0].length) {
    			return true;
    		}
    		return false;
    }
    
    /**
Subnmission
https://leetcode.com/submissions/detail/176122691/
You are here! 
Your runtime beats 94.85 % of java submissions.
     * @param grid
     * @return
     */
    public static int minPathSum_DP(int[][] grid) {
    		int dp[][] = new int[grid.length][grid[0].length];
    		
    		// set default value, as cost of first box cannot be reduced
    		dp[0][0] = grid[0][0];
    		
    		// set values for the first row
    		for(int i = 1; i<grid.length;i++) {
    			dp[i][0] = grid[i][0] + dp[i-1][0]; 
    		}
    		// set values for first column
    		for(int j = 1; j<grid[0].length;j++) {
    			dp[0][j] = grid[0][j] + dp[0][j-1]; 
    		}
    		// set values for the remaining of the grid
    		for(int i =1; i<grid.length;i++) {
    			for(int j =1; j<grid[0].length;j++) {
    				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
    			}
    			
    		}
    		// this is the minimum cost
    		return dp[grid.length-1][grid[0].length-1];
    }

    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] grid = new int[][]
					{
		                {1,3,1},
		                {1,5,1},
		                {4,2,1}
//            {1,3,1,4,5},
//            {1,5,1,4,5},
//            {4,2,1,4,5},
//            {4,2,1,4,5},
//            {4,2,1,4,5}
					};					
		int minCost = minPathSum_BruteForce(grid);
		System.out.println("minCost="+minCost);
		minCost = SolutionDebug.minPathSum_DP(grid);
		System.out.println("minCost="+minCost);
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
		StringBuilder output = new StringBuilder("\n");
		for(int r=0; r<grid.length; r++) {
			for(int c=0; c<grid[r].length; c++) {
				output.append("|"+grid[r][c]);
			}
			output.append("|\n");
		}
		return output.toString();
	}
	
    public static int minPathSum_DP(int[][] grid) {
		int dp[][] = new int[grid.length][grid[0].length];
		
		debug("Grid"+grid(grid));
		
		dp[0][0] = grid[0][0];
		debug("DP"+grid(dp));
		
		for(int i = 1; i<grid.length;i++) {
			dp[i][0] = grid[i][0] + dp[i-1][0]; 
		}
		debug("DP"+grid(dp));
		for(int j = 1; j<grid[0].length;j++) {
			dp[0][j] = grid[0][j] + dp[0][j-1]; 
		}
		debug("DP"+grid(dp));
		
		for(int i =1; i<grid.length;i++) {
			for(int j =1; j<grid[0].length;j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
			}
		}
		debug("DP"+grid(dp));
		return dp[grid.length-1][grid[0].length-1];
    }
}
