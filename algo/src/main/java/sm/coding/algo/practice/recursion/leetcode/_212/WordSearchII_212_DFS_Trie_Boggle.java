package sm.coding.algo.practice.recursion.leetcode._212;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import sm.coding.algo.practice.recursion.leetcode.WordSearch_Medium;

/**
 * 
 * https://leetcode.com/problems/word-search-ii/description/
 * https://discuss.leetcode.com/topic/7907/accepted-very-short-java-solution-no-additional-space/3
 * 
 * 
 * 
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where 
"adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

 * 
 * @author smughal
 *
 */
public class WordSearchII_212_DFS_Trie_Boggle {
	
	/**
Submission
https://leetcode.com/submissions/detail/181851315/
You are here! 
Your runtime beats 41.49 % of java submissions.
	 * @param board
	 * @param words
	 * @return
	 */
	public static List<String> findWords(char[][] board, String[] words) {
		List<String> foundWords = new ArrayList<>();
		Trie root = buildTrie(words);
		
		int rows[] = new int[] { 1, -1, 0,  0}; // top, down, right, left
		int cols[] = new int[] { 0,  0, 1, -1};
		
		for(int r=0; r<board.length;r++) {
			for(int c=0;c<board[r].length;c++) {
				char nextCh = board[r][c];
				Trie nextNode = root.childs[nextCh-'a'];
				if(null!=nextNode) {
					findWords(board, nextNode, foundWords, r, c, rows, cols, ""+nextCh);
				}
			}
		}
		return foundWords;
    }	

	public static List<String> findWords_Boggle(char[][] board, String[] words) {
		List<String> foundWords = new ArrayList<>();
		Trie root = buildTrie(words);
		// only difference between boggle and word search is the diretions, boggel has 8 moves
		int rows[] = new int[] { 1, -1, 0,  0, -1,  1, -1, 1}; // top, down, right, left
		int cols[] = new int[] { 0,  0, 1, -1, -1, -1,  1, 1};
		
		for(int r=0; r<board.length;r++) {
			for(int c=0;c<board[r].length;c++) {
				char nextCh = board[r][c];
				Trie nextNode = root.childs[nextCh-'a'];
				if(null!=nextNode) {
					findWords(board, nextNode, foundWords, r, c, rows, cols, ""+nextCh);
				}
			}
		}
		return foundWords;
    }	

	
	public static void findWords(char[][] board, Trie node, List<String> result, int r, int c, int rows[], int cols[], String sofar){
		if(node.isEnd) {
			result.add(sofar);
			node.isEnd = false; //de-dup
			// do not return and proceed with the rest of the word in trie for match.
		}
		
		char ch = board[r][c];		
		board[r][c]='#';
		
		for(int i=0; i<rows.length; i++) {
			int nextR = r+rows[i];
			int nextC = c+cols[i];
			if(isValid(board, nextR, nextC) && board[nextR][nextC]!='#') {
				char nextCh = board[nextR][nextC];
				Trie nextNode = node.childs[nextCh-'a'];
				if(null!=nextNode) {
					findWords(board, nextNode, result, nextR, nextC, rows, cols, sofar+nextCh);
				}
			}
		}
		board[r][c]=ch;// back tracking
	}
	
	public static Trie buildTrie(String[] words) {
		Trie root = new Trie();
		for(String word:words) {
			Trie current = root;
			for(char c:word.toCharArray()) {
				int index = c-'a';
				if(current.childs[index]==null) {
					current.childs[index] = new Trie();
				}
				current = current.childs[index];
			}
			current.isEnd = true;
		}
		return root;
	}
	
	public static boolean isValid(char[][] board, int r, int c) {
		if(r<0 || c<0 || r>=board.length || c>=board[r].length){
			return false;
		}
		return true;
	}

	/**
	 * Brute force solution without Trie based on the word search, 
	 * search each word and if found add it in result and then return when finished with all of the words.
	 * 
	 * @param board
	 * @param words
	 * @return
	 */
	public static List<String> findWords_BruteForce(char[][] board, String[] words) {
		List<String> result = new ArrayList<>();
		for(String word:words) {
			if(WordSearch_Medium.exist(board, word)) {
				result.add(word);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		char[][] board = new char[][] {
		new char[] {'o','a','a','n'},
		new char[] {'e','t','a','e'},
		new char[] {'i','h','k','r'},
		new char[] {'i','f','l','v'}
		};
		
		String [] words = new String[] {"oath","pea","eat","rain"};
						
//		System.out.println(findWords_BruteForce(board, words));				
		System.out.println(findWords(board, words));				
		System.out.println(SolutionDebug.findWords(board, words));
		
		board = new char[][] {
			new char[] {'g','i','z'},
			new char[] {'u','e','k'},
			new char[] {'q','s','e'},
		};
		words = new String[] {"geeks", "for", "quiz", "go", "seek"};
		
		System.out.println("Boggle="+findWords_Boggle(board, words));				

		
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

	public static String grid(char grid[][]) {
		return grid(grid, null, null, "-", "-");
	}

	public static String grid(char grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
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
	
	public static boolean isValid(char[][] board, int r, int c) {
		if(r<0 || c<0 || r>=board.length || c>=board[r].length){
			return false;
		}
		return true;
	}
	
	
	public static List<String> findWords(char[][] board, String[] words) {
		debugRecr("words="+Arrays.asList(words));
		debugRecr("board\n"+grid(board));
		List<String> foundWords = new ArrayList<>();
		Trie root = buildTrie(words);

		int rows[] = new int[] { 1, -1, 0,  0}; // top, down, right, left
		int cols[] = new int[] { 0,  0, 1, -1};
		
		for(int r=0; r<board.length;r++) {
			for(int c=0;c<board[r].length;c++) {
				char nextCh = board[r][c];
				Trie nextNode = root.childs[nextCh-'a'];
				debugRecr("Word is trie starting with letter ["+nextCh+"] = "+nextNode);
				if(null!=nextNode) {
					findWords(board, nextNode, foundWords, r, c, rows, cols, ""+nextCh);
				}
			}
		}
		debugRecr("foundWords="+foundWords);
		return foundWords;
    }	
	
	public static void findWords(char[][] board, Trie node, List<String> result, int r, int c, int rows[], int cols[], String sofar){
		debug("node="+node+", r="+r+", c="+c);
		if(node.isEnd) {
			debug("matched word="+node.isEnd+", sofar="+sofar);
			result.add(sofar);
			node.isEnd = false; //de-dup
			// do not return and proceed with the rest of the word in trie for match.
		}
		
		char ch = board[r][c];		
		board[r][c]='#';
		String actual = incrementIndent();
		for(int i=0; i<rows.length; i++) {
			int nextR = r+rows[i];
			int nextC = c+cols[i];
			boolean isValid = isValid(board, nextR, nextC);
			debugRecr("nextR="+nextR+", nextC="+nextC+" isValid="+isValid+", board["+nextR+"]["+nextC+"]=" + (!isValid ? "-" : board[nextR][nextC]));
			if(isValid && board[nextR][nextC]!='#') {
				char nextCh = board[nextR][nextC];
				Trie nextNode = node.childs[nextCh-'a'];
				debugRecr("Word in trie that continues with next character ["+nextCh+"] = "+nextNode);
				if(null!=nextNode) {
					findWords(board, nextNode, result, nextR, nextC, rows, cols, sofar+nextCh);
				}
			}
		}
		setIndent(actual);
		board[r][c]=ch;
	}
	
	public static Trie buildTrie(String[] words) {
		Trie root = new Trie();
		for(String word:words) {
			Trie last = root;
			for(char c:word.toCharArray()) {
				int index = c-'a';
				if(last.childs[index]==null) {
					last.childs[index] = new Trie();
				}
				last = last.childs[index];
			}
			last.isEnd = true;
		}
		return root;
	}
}


class Trie {
	boolean isEnd;
	Trie[] childs = new Trie[26];
	@Override
	public String toString() {
		return "Trie [isEnd=" + isEnd + "]";
	}
}    

