
package sm.interview.coding_challange.company_5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shahzad mughal
 *
 */
public class GeneratePhoneNumbers {

	
	public static List<List<Integer>> generatePossibleNumbers(int start, int length) {
		List<List<Integer>> result = new ArrayList<>();
		
		if(start<0 || start >9 || length<1) {
			return result;
		}
		
		int[] rowMoves = new int[]{-1, 1,  0, 0}; // up, down, left, right
		int[] colMoves = new int[]{ 0, 0, -1, 1};
		// phone pad as grid i.e. 2d array
		int[][] phonePad = new int[][]{
								{1, 2, 3},
								{4, 5, 6},
								{7, 8, 9},
								{-1, 0, -1}
							};
        Map<Integer, Pair> startingPositions = new HashMap<>();
		// build the starting position map i.e. number-->r,c mapping					
        setupStartingPositions(startingPositions, phonePad);
        
        Pair startPoint = startingPositions.get(start);
        
		List<Integer> sofar = new ArrayList<>();
		sofar.add(start);
		generatePossibleNumbers_Helper(length-1, startPoint.row, startPoint.col, result, rowMoves, colMoves, phonePad, sofar);
		return result;
	}
	/**
	 * This method calculates the possible numbers recursively.
	 * @param length
	 * @param row
	 * @param col
	 * @param result
	 * @param rowMoves
	 * @param colMoves
	 * @param phonePad
	 * @param sofar
	 */
	public static void generatePossibleNumbers_Helper(int length, int row, int col, List<List<Integer>> result, int[] rowMoves, int[] colMoves, int[][] phonePad, List<Integer> sofar) {
		// base case
		if(length == 0) {
			result.add(new ArrayList<>(sofar));
			return;
		}
		// recursive case
		for(int i=0; i<rowMoves.length;i++) {
			int nextRow = row+rowMoves[i];
			int nextCol = col+colMoves[i];
			if(isSafe(phonePad, nextRow, nextCol)) {				
				sofar.add(phonePad[nextRow][nextCol]);
				generatePossibleNumbers_Helper(length-1, nextRow, nextCol, result, rowMoves, colMoves, phonePad, sofar);
				sofar.remove(sofar.size()-1); // back tracking
			}
		}		
	}
	/**
	 * Check if the array index are valid and not a -1, i.e. valid phone number on phone pad
	 * @param grid
	 * @param row
	 * @param col
	 * @return
	 */
	public static boolean isSafe(int[][] grid, int row, int col) {
		if(row>=0 && row<grid.length && col>=0 && col<grid[0].length && grid[row][col]!=-1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Fill up the mapping for each of the number in format number--> row,col
	 * @param startingPositions
	 * @param phonePad
	 */
	public static void setupStartingPositions(Map<Integer, Pair> startingPositions, int[][] phonePad) {
		for(int i= 0; i<phonePad.length; i++) {
			for(int j=0; j<phonePad[0].length; j++) {
				startingPositions.put(phonePad[i][j], new Pair(i,j));
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int start = 1;
		int length = 3;
		List<List<Integer>> result = generatePossibleNumbers(start, length);
		System.out.println("Total possibot numbers starting from "+start+" with length "+length+" are = "+result.size());
		System.out.println(result);

		start = 5;
		length = 1;
		result = generatePossibleNumbers(start, length);
		System.out.println("Total possibot numbers starting from "+start+" with length "+length+" are = "+result.size());
		System.out.println(result);

	}

}

/**
 * Represents a pair of row and column
 * @author shahzad mughal
 *
 */
class Pair {
	int row;
	int col;
	
	public Pair(int row, int col) {
		this.row = row;
		this.col = col;
	}
}