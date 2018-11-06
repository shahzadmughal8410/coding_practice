/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.boggle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class BoggleSolver_Graph_Recr_BckTrk_Dfs {

	
	static Set<String> dict = new HashSet<>();
	
	static int[] rows = {-1, 1, -1,  1, 0, 1,  0, -1};
	static int[] cols = { 1, 1, -1, -1, 1, 0, -1,  0};
	
	static void boggleSolver(char[][] matrix) {
		boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
		
		for(int r=0; r<matrix.length; r++) {
			for(int c=0; c<matrix[0].length; c++) {
				if(!isVisited[r][c]) { //not visited
					StringBuilder word = new StringBuilder();
					dfs(matrix, r, c, isVisited, word);
					//print(word)
				}
			}
		}		
	}
	
	static void dfs(char[][] matrix, int r, int c, boolean[][] isVisited, StringBuilder word) {
		isVisited[r][c] = true;
		
		word.append(matrix[r][c]);
		
		if(dict.contains(word.toString())) {
			System.out.println(word);
		}
		
		for(int i=0; i<8; i++) {
			int rr = r+rows[i];
			int cc = c+cols[i];
			if(isValid(matrix, rr, cc)) {
				if(!isVisited[rr][cc]) {
					dfs(matrix, rr, cc, isVisited, word);
				}
			}
		}
		
		isVisited[r][c] = false; //backtracking
		word.deleteCharAt(word.length()-1); //backtracking
		
	}
	
	static boolean isValid(char[][] matrix, int r, int c) {		
		if(r<0 || c<0 || r>=matrix.length || c>=matrix[r].length) {
			return false;
		}		
		return true;		
	}
}
