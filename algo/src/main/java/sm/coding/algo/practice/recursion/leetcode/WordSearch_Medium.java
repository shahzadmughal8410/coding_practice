package sm.coding.algo.practice.recursion.leetcode;

/**
 * 
 * https://leetcode.com/problems/word-search/description/
 * https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space/3
 * 
 * 
 * 
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.

For example,
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Output:
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.

 * 
 * @author smughal
 *
 */
public class WordSearch_Medium {
	public static boolean exist(char[][] board, String word) {
		for(int i = 0;i<board.length;i++) {
			for(int j = 0;j<board[i].length; j++) {
				if(existHelper(board, word , i, j, 0)) {
					return true;
				}
			}			
		}
		return false;
	}
	/**
	 * Its a kind of DFS as for a particular character we are searching all of its neighbour/childerns. 
	 * 
	 * @param board
	 * @param word
	 * @param i
	 * @param j
	 * @param index
	 * @return
	 */
	public static boolean existHelper(char[][] board, String word, int i , int j, int index) {
		if(index==word.length()) {
			return true;
		}
		if(i<0 || j<0 || i==board.length || j==board[i].length) {
			return false;
		}
		if(board[i][j]!=word.charAt(index)) {
			return false;
		}
		
		board[i][j] ^= 256;
		boolean found = 
				existHelper(board, word , i, j+1, index+1) ||
				existHelper(board, word , i, j-1, index+1) ||				
				existHelper(board, word , i-1, j, index+1) ||
				existHelper(board, word , i+1, j, index+1) 
				;
		board[i][j] ^= 256;
		return found;
	}
	
	public static void main(String[] args) {
		char[][] board = new char[][] {
		new char[] {'A','B','C','E'},
		new char[] {'S','F','C','S'},
		new char[] {'A','D','E','E'}};
						
		System.out.println("ABCCED="+exist(board, "ABCCED"));				
		System.out.println("SEE="+exist(board, "SEE"));				
		System.out.println("ABCB="+exist(board, "ABCB"));				
		System.out.println("ABFC="+exist(board, "ABFC"));				
//		System.out.println("ABCCED="+WordSearch_Medium_Debug.exist(board, "ABCCED"));				
//		System.out.println("SEE="+WordSearch_Medium_Debug.exist(board, "SEE"));				
//		System.out.println("ABCB="+WordSearch_Medium_Debug.exist(board, "ABCB"));				
//		System.out.println("ABFC="+WordSearch_Medium_Debug.exist(board, "ABFC"));				
	}
	
}

class WordSearch_Medium_Debug {
	static String indent = "";
	public static void debug(Object msg) {
		System.out.println("DEBUG "+indent+">"+msg);
	}
	public static String incrementIndent() {
		String indentActual = indent;
		indent = indent+"|---";
		return indentActual;
	}

	public static boolean exist(char[][] board, String word) {
		for(int i = 0;i<board.length;i++) {
			for(int j = 0;j<board[i].length; j++) {
				if(searchHelper(board, word , i, j, 0)) {
					return true;
				}
			}			
		}
		return false;
	}

	
	public static boolean searchHelper(char[][] board, String word, int i , int j, int index) {
		debug("i="+i+", j="+j+", index="+index);
		if(index==word.length()) {
			return true;
		}
		if(i<0 || j<0 || i==board.length || j==board[i].length) {
			return false;
		}
		if(board[i][j]!=word.charAt(index)) {
			return false;
		}
		
		debug("actual='"+board[i][j]+"'");
		board[i][j] ^= 256;
		debug("masked='"+board[i][j]+"'");

		String indentActual = incrementIndent();

		boolean found = 
				searchHelper(board, word , i, j+1, index+1) ||
				searchHelper(board, word , i, j-1, index+1) ||				
				searchHelper(board, word , i-1, j, index+1) ||
				searchHelper(board, word , i+1, j, index+1) 
				;
		board[i][j] ^= 256;
		indent = indentActual;

		debug("found="+found);
		return found;
	}
	
}