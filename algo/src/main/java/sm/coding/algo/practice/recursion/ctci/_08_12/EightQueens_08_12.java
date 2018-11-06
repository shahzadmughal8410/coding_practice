/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_12;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class EightQueens_08_12 {

	/**
Eight Queens: Write an algorithm to print all ways of arranging eight queens on an 8x8 chess board
so that none of them share the same row, column, or diagonal. In this case, "diagonal" means all
diagonals, not just the two that bisect the board.
Hints: #308, #350, #371 
	 * @param args
	 */
	public static List<List<Pair>> placeQueens(int n){
		int[][] board = new int[n][n];
		List<List<Pair>> result = new ArrayList<>();
		placeQueensHelper(board, new LinkedList<>(), result, 0);
		return result;
	}
	public static void placeQueensHelper(int[][] board, List<Pair> sofar, List<List<Pair>> result, int c) {
		if(c==board.length) {
			result.add(new LinkedList<>(sofar));
			printBoard(board);
			return;
		}
		
		for(int r=0; r<board.length; r++) {
			if(isSafe(board, r, c)) {
				int tmp = board[r][c];
				board[r][c] = 1;
				sofar.add(new Pair(r,c));
				placeQueensHelper(board, sofar, result, c+1);
				board[r][c] = tmp;
				sofar.remove(sofar.size()-1);
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
	
	public static void main(String[] args) {
		List<List<Pair>> result = placeQueens(4);
		result.forEach(r->System.out.println(r));
		System.out.println("Total possible queens placements="+result.size());

	}

}


class Pair{
	int r, c;
	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
	@Override
	public String toString() {
		return "(" + r + "," + c + ")";
	}
	
}