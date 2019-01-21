/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode._052;


/**
 * @author shahzadmughal8410
 *
 */
public class NQueens_Recr_BackTrk_052 {

	/**
The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no 
two queens attack each other.

Given an integer n, return the number of distinct solutions to the n-queens puzzle.

Example:

Input: 4
Output: 2
Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

https://leetcode.com/submissions/detail/173435591/
You are here!
Your runtime beats 61.66 % of java submissions.

T= O(n!)
S = O(n)
	 */
	public static int totalNQueens(int n){
		int[][] board = new int[n][n];
		int[] result = new int[1];
		placeQueensHelper(board, result, 0);
		return result[0];
	}
	public static void placeQueensHelper(int[][] board, int[] result, int c) {
		if(c==board.length) {
			result[0]++;
			printBoard(board);
			return;
		}
		
		for(int r=0; r<board.length; r++) {
			if(isSafe(board, r, c)) {
				int tmp = board[r][c];
				board[r][c] = 1;
				placeQueensHelper(board, result, c+1);
				board[r][c] = tmp;
			}
		}
	}
	
	public static boolean isSafe(int[][] board, int row, int col) {
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
		int queens = 4;
		int result = totalNQueens(queens);
		System.out.printf("%d queens has total %d number of solutions.", queens, result);		
	}

}
