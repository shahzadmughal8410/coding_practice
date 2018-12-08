/**
 * 
 */
package sm.coding.ds.array.leetcode._074;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class SearchA2DMatrix_074_BinSrch {
	
	public static int[] searchMatrix_ReturnType(int[][] matrix, int target) {
		int start = 0;
		int end = matrix.length*matrix[0].length -1;
		
		while(start<=end) {
			int mid = ((end-start)/2 ) +start;
			int[] rc = getRowColFromIndex(mid, matrix[0].length);
			
			if(matrix[rc[0]][rc[1]] == target) {
				return rc;
			}
			
			if(matrix[rc[0]][rc[1]] > target) {// search in left half 
				end = mid-1;
			}else {// search in right half
				start = mid+1;
			}
		}
		return null;
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/193951361/
You are here! 
Your runtime beats 37.44 % of java submissions.

	 * @param matrix
	 * @param target
	 * @return
	 */
	public static boolean searchMatrix(int[][] matrix, int target) {
		if(null==matrix || matrix.length==0 || matrix[0].length==0) {
			return false;
		}
		
		int start = 0;
		int end = matrix.length*matrix[0].length -1;
		
		while(start<=end) {
			int mid = ((end-start)/2 ) +start;
			int[] rc = getRowColFromIndex(mid, matrix[0].length);
			
			if(matrix[rc[0]][rc[1]] == target) {
				return true;
			}
			
			if(matrix[rc[0]][rc[1]] > target) {// search in left half 
				end = mid-1;
			}else {// search in right half
				start = mid+1;
			}
		}
		return false;
	}
	
    public static int[] getRowColFromIndex(int s, int cols) { 
        // Given a square num s, return board coordinates (r, c) 
        int row = s / cols;
        int col = s % cols;
        return new int[] {row, col};
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] board = null;
		int rows = 2;
		int cols = 3;
		board = new int[rows][cols] ;
		int k = 1;
		for(int i =0; i<board.length; i++) {
			for(int j =0; j<board[0].length; j++) {
				board[i][j] = k++;
			}
		}
		
		System.out.println(SolutionDebug.grid(board));
		for(int i=0;i<rows*cols ; i++) {
			int [] rc = getRowColFromIndex(i,cols);
			System.out.println(i+" --> "+rc[0]+","+ rc[1]+", board value="+board[rc[0]][rc[1]]);
		}
		
		
		for(int i=1;i<=rows*cols ; i++) {
			int[] rc = searchMatrix_ReturnType(board, i);
			System.out.println(i+" , searched index="+rc[0]+", "+rc[1]+", searched value = "+board[rc[0]][rc[1]]+" == "+i+" = "+(board[rc[0]][rc[1]]==i));
		}

		rows = 5;
		cols = 3;
		board = new int[rows][cols] ;
		k = 1;
		for(int i =0; i<board.length; i++) {
			for(int j =0; j<board[0].length; j++) {
				board[i][j] = k++;
			}
		}
		
		System.out.println(SolutionDebug.grid(board));
		for(int i=0;i<rows*cols ; i++) {
			int [] rc = getRowColFromIndex(i,cols);
			System.out.println(i+" --> "+rc[0]+","+ rc[1]+", board value="+board[rc[0]][rc[1]]);
		}
		
		
		for(int i=1;i<=rows*cols ; i++) {
			int[] rc = searchMatrix_ReturnType(board, i);
			System.out.println(i+" , searched index="+rc[0]+", "+rc[1]+", searched value = "+board[rc[0]][rc[1]]+" == "+i+" = "+(board[rc[0]][rc[1]]==i));
		}

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
		return grid(grid, null, null, "-", "-");
	}

	public static String grid(int grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
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
}