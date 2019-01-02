/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode._051;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class NQueens_Recr_BackTrk_051 {

	/**
 The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that 
 no two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

Example:

Input: 4
Output: [
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.

Submission
Submission
https://leetcode.com/submissions/detail/173437663/ 
You are here!
Your runtime beats 53.01 % of java submissions.

	 */

	public static List<List<String>> solveNQueens(int n){
		int[][] board = new int[n][n];
		List<List<String>> result = new ArrayList<>();
		placeQueensHelper(board, result, 0);
		return result;
	}
	public static void placeQueensHelper(int[][] board, List<List<String>> result, int c) {
		if(c==board.length) {
			result.add(convertBoard(board));
			printBoard(board);
			return;
		}
		
		for(int r=0; r<board.length; r++) {
			if(isSafe_Fast(board, r, c)) {
				int tmp = board[r][c];
				board[r][c] = 1;
				placeQueensHelper(board, result, c+1);
				board[r][c] = tmp;
			}
		}
	}

	public static List<String> convertBoard(int board[][]) {
		List<String> result = new ArrayList<>();
		StringBuilder line;
		for(int r=0; r<board.length; r++) {
			line = new StringBuilder();
			for(int c=0; c<board[r].length; c++) {
				char ch = board[r][c]==0? '.' :'Q';
				line.append(ch);
			}
			result.add(line.toString());			
		}
		return result;
	}
	
	/**
Optimized version of is safe method using formula.
for calculating top left to bottom diagonal
row-col == i-j

for calculating top right to bottom left
row+col == i+j

for checking same row 
row == i

For same column, as we are placing queens column by column, 
so no need to check the column

Runtime of this is O(r*c)
	 */
	public static boolean isSafe_Slow(int[][] board, int row, int col) {
		for(int iRow=0; iRow<board.length; iRow++) {
			for(int jCol=0; jCol<col; jCol++) {
				if(board[iRow][jCol] == 1 && 
						(
							row-col == iRow-jCol || // diagonal top left to bottom right
							row+col == iRow+jCol || // diagonal top right to bottom left
							row == iRow //same row
							
						) 
						) {
					return false;
				}
			}
			
		}
		return true;
	}

	/**
Runtime of the following is O(n)
	 */
	public static boolean isSafe_Fast(int[][] board, int row, int col) {
		//column left to right
		for(int c=col; c>=0; c--) {
			if(board[row][c]==1) {
				return false;
			}
		}
		//upper diagonal left to right
		for(int r=row, c=col; r>=0 && c>=0; r--, c--) {
			if(board[r][c]==1) {
				return false;
			}
		}
		//lower diagonal left to right
		for(int r=row, c=col; r<board.length && c>=0; r++, c--) {
			if(board[r][c]==1) {
				return false;
			}
		}
		return true;
	}	

	
	public static void printBoard(int board[][]) {
		System.out.println();
		String line = "";
		for(int r=0; r<board.length; r++) {
			System.out.println(line);			
			line = "";
			for(int c=0; c<board[r].length; c++) {
				char ch = board[r][c]==0? ' ' :'Q';
				System.out.print("|"+ch);
				line+="--";
			}
			System.out.print("|");
			System.out.println();			
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<List<String>> result = solveNQueens(4);
		result.forEach(board-> {							
							System.out.println("[");
							board.forEach(row->System.out.println("\""+row+"\""));
							System.out.println("]\n");
						}		
					);		
	}

}
