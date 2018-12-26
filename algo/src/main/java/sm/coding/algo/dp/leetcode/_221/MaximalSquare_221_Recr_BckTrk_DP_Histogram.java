/**
 * 
 */
package sm.coding.algo.dp.leetcode._221;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class MaximalSquare_221_Recr_BckTrk_DP_Histogram {

	/**
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
	 */

	/**
Submission
https://leetcode.com/submissions/detail/178876428/
Time Limit Exceeded
Last executed input:
	 * @param matrix
	 * @return
	 */
    public static int maximalSquare_BruteForce(char[][] matrix) {
        int max = 0;
        for(int i =0; i<matrix.length; i++) {
            for(int j =0; j<matrix[0].length; j++) {
	            	if(matrix[i][j]=='1') {
	            		max = Math.max(max, maximalSquareHelper_BruteForce(matrix, i, j));
	            	}
            }        	
        }
        return max*max;
    }
	
    public static int maximalSquareHelper_BruteForce(char[][] matrix, int i, int j) {
        if(i==matrix.length || j==matrix[0].length) {
        		return 0;
        }
        
        if(matrix[i][j]!='1') {
        		return 0;
        }
        
        return 1+ Math.min(
        			maximalSquareHelper_BruteForce(matrix, i+1, j), // down 
        			Math.min(
        					maximalSquareHelper_BruteForce(matrix, i, j+1), // right 
        					maximalSquareHelper_BruteForce(matrix, i+1, j+1) // right bottom diagonal
        					)
        			);
    }
    
    /**
Submission
https://leetcode.com/submissions/detail/178913826/
You are here!
Your runtime beats 94.71 % of java submissions.
     * @param matrix
     * @return
     */
    public static int maximalSquare_Memoization(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = 0;
		Integer[][] dp = new Integer[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1') {
					max = Math.max(max, maximalSquareHelper_Memoization(matrix, i, j, dp));
				}
			}
		}
		return max * max;
	}

	public static int maximalSquareHelper_Memoization(char[][] matrix, int i, int j, Integer[][] dp) {

		if (i == matrix.length || j == matrix[0].length) {
			return 0;
		}
		if (dp[i][j] != null) {
			return dp[i][j];
		}

		if (matrix[i][j] != '1') {
			return 0;
		}

		dp[i][j] = 1 + Math.min(
						maximalSquareHelper_Memoization(matrix, i + 1, j, dp), // down
						Math.min(
						maximalSquareHelper_Memoization(matrix, i, j + 1, dp), // right
						maximalSquareHelper_Memoization(matrix, i + 1, j + 1, dp) // right bottom diagonal
				));

		return dp[i][j];
	}

	/**
Submission
https://leetcode.com/submissions/detail/178924494/
You are here!
Your runtime beats 30.08 % of java submissions.
	 * @param matrix
	 * @return
	 */
    public static int maximalSquare_DP(char[][] matrix) {
		if (matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int max = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(i==0 || j==0) {
					dp[i][j] = matrix[i][j] =='1' ? 1 : 0;
				}
				else if (matrix[i][j] == '1') {
					dp[i][j] = 1 + Math.min(
									dp[i - 1][j], // down
									Math.min(
									dp[i][j - 1], // right
									dp[i - 1][j - 1] // right bottom diagonal
							));
				}				
				max = Math.max(max, dp[i][j]);
			}
		}
		return max * max;
	}	
    
	/**
Submission
https://leetcode.com/submissions/detail/197085663/
You are here! 
Your runtime beats 98.40 % of java submissions.
	 * @param matrix
	 * @return
	 */
    public static int maximalSquare_DP_Pattern(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int max = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		
		dp[0][0] = matrix[0][0]=='1' ? 1 : 0;
		max = Math.max(max, dp[0][0]);		
		
		// copy first col from matrix
		for(int i=1;i<dp.length;i++) {
			dp[i][0] = matrix[i][0]=='1' ? 1 : 0;
			max = Math.max(max, dp[i][0]);
		}
		
		// copy first row from matrix
		for(int j=1;j<dp[0].length;j++) {
			dp[0][j] = matrix[0][j]=='1' ? 1 : 0;
			max = Math.max(max, dp[0][j]);
		}
		
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == '1') {
					dp[i][j] = 1 + Math.min(
									dp[i - 1][j], // down
									Math.min(
									dp[i][j - 1], // right
									dp[i - 1][j - 1] // right bottom diagonal
							));
				}				
				max = Math.max(max, dp[i][j]);
			}
		}
		return max * max;
	}	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[][] matrix;
//		matrix = new char[][] {
//			{'1', '0', '1', '0', '0'},
//			{'1', '0', '1', '1', '1'},
//			{'1', '1', '1', '1', '1'},
//			{'1', '0', '1', '1', '1'},
//		};
		matrix = new char[][] {
			{'1'}
		};
		
//		System.out.println("Max rectangle sub matrix="+maximalSquare_BruteForce(matrix));
//		System.out.println("Max rectangle sub matrix="+maximalSquare_Memoization(matrix));
		System.out.println("Max rectangle sub matrix="+maximalSquare_DP(matrix));

		matrix = new char[][] {
			{'1', '0', '1',},
			{'0', '1', '1',},
			{'0', '1', '1',},
			{'1', '0', '0',},
		};
		
		System.out.println("Max rectangle sub matrix="+SolutionDebug.maximalSquare_BruteForce(matrix));
//		System.out.println("Max rectangle sub matrix="+maximalSquare_Memoization(matrix));
		System.out.println("Max rectangle sub matrix="+SolutionDebug.maximalSquare_DP(matrix));

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

	public static String gridChar(char grid[][]) {
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
    public static int maximalSquare_BruteForce(char[][] matrix) {
    		debugRecr("matrix="+Arrays.stream(matrix).collect(Collectors.toList()));
        int max = 0;
        for(int i =0; i<matrix.length; i++) {
            for(int j =0; j<matrix[0].length; j++) {
            		debugRecr("matrix[i][j] => matrix["+i+"]["+j+"] = "+matrix[i][j]);
	            	if(matrix[i][j]=='1') {
	            		debugRecr("Calculating sub matrix starting from matrix[i][j] => matrix["+i+"]["+j+"] = "+matrix[i][j]+" \n");
	            		debugRecr("Matrix\n"+gridChar(matrix));
	            		int newMax = maximalSquareHelper_BruteForce(matrix, i, j);
	            		debugRecr("max="+max+", newMax"+newMax);
	            		max = Math.max(max, newMax);
	            		debugRecr("matrix[i][j] => matrix["+i+"]["+j+"] hs max sub rectangle "+max+" \n\n");
	            	}
            }        	
        }
        debugRecr("max*max => "+max+"*"+max+" = "+ (max*max));
        return max*max;
    }
	
    public static int maximalSquareHelper_BruteForce(char[][] matrix, int i, int j) {
    		debugRecr("i="+i+", j="+j);
        if(i==matrix.length || j==matrix[0].length) {
        		debugRecr("Either i or j is zero");
        		return 0;
        }
        
        if(matrix[i][j]!='1') {
        		debugRecr("matrix["+i+"]["+j+"] = "+matrix[i][j]+" is not One, matrix["+i+"]["+j+"]=0");
        		return 0;
        }
        String actual = incrementIndent();
        int down = maximalSquareHelper_BruteForce(matrix, i+1, j); // down
        int right = maximalSquareHelper_BruteForce(matrix, i, j+1); // right
        int rightBottomDiagonal = maximalSquareHelper_BruteForce(matrix, i+1, j+1); // right bottom diagonal
        debugRecr("down = "+down+", right = "+right+", rightBottomDiagonal = "+rightBottomDiagonal);
        int result = 
        		1+ Math.min(
        			down,
        			Math.min(
        					right, 
        					rightBottomDiagonal
        					)
        			);
        setIndent(actual);
        debugRecr("result="+result);
        return result;
    }
	
    public static int maximalSquare_DP(char[][] matrix) {
    		reset();
    		debug("matrix\n"+gridChar(matrix));
		if (matrix.length == 0 || matrix[0].length == 0) {
			debug("matrix length is zero");
			return 0;
		}
		int max = 0;
		int[][] dp = new int[matrix.length][matrix[0].length];
		debug("dp\n"+grid(dp));
		
		tableColumns("i:3", "j:3", "matrix[i][j]:15", "down=dp[i - 1][j]:20", "right=dp[i][j - 1]:20", "rightBottomDiagonal=dp[i - 1][j - 1]:40", "max", "dp[i][j]:12", "newMax");
		debugColumns();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				String strIJ = "-";
				String down = "-";
				String right = "-";
				String rightBottomDiagonal = "-";
				
				if(i==0 || j==0) {
					dp[i][j] = matrix[i][j] =='1' ? 1 : 0;
					strIJ = "matrix["+i+"]["+j+"]="+matrix[i][j];
				}
				else if (matrix[i][j] == '1') {
					down = "down=dp["+i+" - 1]["+j+"]="+dp[i - 1][j];
					right = "right=dp["+i+"]["+j+" - 1]="+dp[i][j - 1];
					rightBottomDiagonal = "rightBottomDiagonal=dp["+i+" - 1]["+j+" - 1]="+dp[i - 1][j - 1];
					dp[i][j] = 1 + Math.min(
									dp[i - 1][j], // down
									Math.min(
									dp[i][j - 1], // right
									dp[i - 1][j - 1] // right bottom diagonal
							));
				}				
				int newMax = dp[i][j];
				debugRow(i, j, strIJ, down, right, rightBottomDiagonal, max, "dp["+i+"]["+j+"]="+dp[i][j], newMax );
				max = Math.max(max, newMax);
			}
			debug("Iteration "+i+" dp\n"+grid(dp));
		}
		debug("result dp\n"+grid(dp));
		return max * max;
	}	
}
