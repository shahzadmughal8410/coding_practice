/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

/**
 * @author smughal
 *
 */
public class ShortestPathInBinaryMaze {

	/**
	 * 
	 * https://www.geeksforgeeks.org/shortest-path-in-a-binary-maze/ 
	 * 
Given a MxN matrix where each element can either be 0 or 1. 
We need to find the shortest path between a given source cell to a destination cell. 
The path can only be created out of a cell if its value is 1.

Expected time complexity is O(MN).

For example –

Input:
mat[ROW][COL]  = {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                  {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                  {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                  {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                  {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                  {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                  {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                  {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                  {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
Source = {0, 0};
Destination = {3, 4};

Output:
Shortest Path is 11  

Solution Notes:
As its a path and we do not need to track the path i.e. index positions that are contributing the path, then use BFS
	 * @param args
	 */
	
	public static int shortestPathBfs(int[][] maze, int srcR, int srcC, int dstR, int dstC) {
		// Boundary condition
		if(srcR>=dstR || srcC>=dstC) {
			return 0;
		}
		// available moves or children's of current node, i.e. top, down, left, right
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		// matrix to mark nodes as visited
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		Deque<Pair> q = new LinkedList<>();
		// 1- start, enque and mark visited
		q.offer(new Pair(srcR,srcC));
		visited[srcR][srcC] = true;
		int path = 0;
		
		// 2- while q is not empty
		while(!q.isEmpty()) {
			// 3- get from  q
			Pair mazeIndex = q.poll();
			
			// 4- process node
			// if we reached destination return path, 
			if(mazeIndex.r==dstR && mazeIndex.c==dstC) {
				break;
			}
			// as q was not empty, increment path 
			++path;
			
			// 5- process child's
			for(int i=0; i<rows.length; i++) {
				int nextR = mazeIndex.r+rows[i];
				int nextC = mazeIndex.c+cols[i];
				// check the next calculated index is valid
				if(isValid(dstR, dstC, nextR, nextC)) {
					// 6- if index is not visited and not a hurdle (zero, 0), then move further
					if(!visited[nextR][nextC] && maze[nextR][nextC]==1) { 
						q.offer(new Pair(nextR,nextC));
						visited[nextR][nextC] = true;
					}
				}
			}
		}
		return path;
	}
	
	
	/**
	 * For shortest path/path always use BFS as dfs is not the optimal solution. 
	 * As dfs is needed when we need to know the elements in the path as well.
	 * For path we don't need the elements of the path. 
	 * 
	 * @param maze
	 * @param srcR
	 * @param srcC
	 * @param dstR
	 * @param dstC
	 * @return
	 */
	public static int shortestPathDfs(int[][] maze, int srcR, int srcC, int dstR, int dstC) {
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		int[] result = new int[] {Integer.MAX_VALUE};
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		// 1- start
		shortestPathDfsHelper(maze, srcR, srcC, dstR, dstC, result, rows, cols, 0, visited);
		
		return result[0];		
	}
	
	public static void shortestPathDfsHelper(int[][] maze, int srcR, int srcC, int dstR, int dstC, int[] result, int[] rows, int[] cols, int lengthSofar, boolean[][] visited) {
		int current = maze[srcR][srcC];
		// if its hurdle we cannot proceed
		if(current==0) {
			return;
		}
		
		// if destination reached record length and return;
		if(srcR==dstR && srcC==dstC) {
			if(lengthSofar<result[0]) {
				result[0]=lengthSofar;
			}
			return;
		}
		// 2- mark visited
		visited[srcR][srcC] = true;
		
		// 3- process node
		
		// 4- process child's
		for(int i=0; i<rows.length;i++) {
			int nextR = srcR+rows[i];
			int nextC = srcC+cols[i];
			// 5- if not visited
			if(isValid(dstR, dstC, nextR, nextC) && visited[nextR][nextC]==false) {
				// 6- recurse
				shortestPathDfsHelper(maze, nextR, nextC, dstR, dstC, result, rows, cols, lengthSofar+1, visited);
			}
		}
		visited[srcR][srcC] = false;
	}
	
	public static boolean isValid(int dstR, int dstC, int nextR, int nextC) {
		if(nextR<0 || nextC<0 || nextR>dstR || nextC>dstC) {
			return false;
		}
		return true;
	}
	
	public static void printMaze(int[][] maze) {
		for(int r=0; r<maze.length;r++) {
			for(int c=0; c<maze[r].length;c++) {
				System.out.print(maze[r][c]+", ");
			}
			System.out.println();
		}
	}
	
	
	public static void main(String[] args) {
		int maze[][] = new int[][]
			   {{1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                {1, 0, 1, 0, 1, 1, 1, 0, 1, 1 },
                {1, 1, 1, 0, 1, 1, 0, 1, 0, 1 },
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1 },
                {1, 1, 1, 0, 1, 1, 1, 0, 1, 0 },
                {1, 0, 1, 1, 1, 1, 0, 1, 0, 0 },
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
                {1, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                {1, 1, 0, 0, 0, 0, 1, 0, 0, 1 }};
                
         printMaze(maze);
         System.out.println("BFS shortestPath="+SolutionDebug.shortestPathBfs(maze, 0, 0, 3, 4));
         System.out.println("DFS shortestPath="+SolutionDebug.shortestPathDfs(maze, 0, 0, 3, 4));
		System.out.println("BFS shortestPath="+shortestPathBfs(maze, 0, 0, 3, 4));
		System.out.println("DFS shortestPath="+shortestPathDfs(maze, 0, 0, 3, 4));
	}

}

class Pair {
	int r;
	int c;
	
	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "Pair [r=" + r + ", c=" + c + "]";
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

	public static String grid(Integer grid[][]) {
		return grid(grid, null, null, "-", "-");
	}

	public static String grid(Integer grid[][], List rowHeader, List colHeader, String rowHeading, String colHeading) {
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
	
	public static boolean isValid(int dstR, int dstC, int nextR, int nextC) {
		if(nextR<0 || nextC<0 || nextR>dstR || nextC>dstC) {
			return false;
		}
		return true;
	}
	
	public static int shortestPathBfs(int[][] maze, int srcR, int srcC, int dstR, int dstC) {
		debug("srcR="+srcR+", srcC="+srcC+", dstR="+dstR+", dstC="+dstC);
		// Boundary condition
		if(srcR>=dstR || srcC>=dstC) {
			debug("Invalid srcR and srcC, returning zero.");
			return 0;
		}
		// available moves or children's of current node, i.e. top, down, left, right
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		// matrix to mark nodes as visited
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		Deque<Pair> q = new LinkedList<>();
		// 1- start, enque and mark visited
		q.offer(new Pair(srcR,srcC));
		visited[srcR][srcC] = true;
		int path = 0;				
		
		// 2- while q is not empty
		while(!q.isEmpty()) {
			debug("q size"+q.size()+", q="+q);
			// 3- get from  q
			Pair mazeIndex = q.poll();
			
			// 4- process node
			// if we reached destination return path, 
			if(mazeIndex.r==dstR && mazeIndex.c==dstC) {
				debug("Destination reached, path="+path);
				break;
			}
			// as q was not empty, increment path 
			++path;
			
			// 5- process child's
			for(int i=0; i<rows.length; i++) {
				int nextR = mazeIndex.r+rows[i];
				int nextC = mazeIndex.c+cols[i];
				// check the next calculated index is valid
				if(isValid(dstR, dstC, nextR, nextC)) {
					debug("maze[nextR][nextC]="+maze[nextR][nextC]+", visited[nextR][nextC]="+visited[nextR][nextC]);
					// 6- if index is not visited and not a hurdle (zero, 0), then move further
					if(!visited[nextR][nextC] && maze[nextR][nextC]==1) { 
						debug("Adding in q nextR="+nextR+", nextC="+nextC);
						q.offer(new Pair(nextR,nextC));
						visited[nextR][nextC] = true;
					}
				}
			}
		}
		debug("returning Final, path="+path);
		return path;
	}
	
	public static int shortestPathDfs(int[][] maze, int srcR, int srcC, int dstR, int dstC) {
		reset();
		debugRecr("srcR="+srcR+", srcC="+srcC+", dstR="+dstR+", dstC="+dstC);
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		int[] path = new int[] {Integer.MAX_VALUE};
		boolean[][] visited = new boolean[maze.length][maze[0].length];
		
		debugRecr("Starting DFS");
		// 1- start
		shortestPathDfsHelper(maze, srcR, srcC, dstR, dstC, path, rows, cols, 0, visited);
		
		return path[0];		
	}
	
	public static void shortestPathDfsHelper(int[][] maze, int srcR, int srcC, int dstR, int dstC, int[] path, int[] rows, int[] cols, int lengthSofar, boolean[][] visited) {
		debugRecr("srcR="+srcR+", srcC="+srcC+", dstR="+dstR+", dstC="+dstC+", lengthSofar="+lengthSofar+", path="+path[0]);
		int current = maze[srcR][srcC];
		// if its hurdle we cannot proceed
		if(current==0) {
			debugRecr("Hurdle found!");
			return;
		}
		
		// if destination reached record length and return;
		if(srcR==dstR && srcC==dstC) {
			debugRecr("Distenation reached");
			if(lengthSofar<path[0]) {
				debugRecr("Min path found up till now = "+lengthSofar );
				path[0]=lengthSofar;
			}
			return;
		}
		// 2- mark visited
		visited[srcR][srcC] = true;
		
		// 3- process node
		
		// 4- process child's
		for(int i=0; i<rows.length;i++) {
			int nextR = srcR+rows[i];
			int nextC = srcC+cols[i];
			// 5- if not visited
			if(isValid(dstR, dstC, nextR, nextC) && visited[nextR][nextC]==false) {
				String actual = incrementIndent();
				// 6- recurse
				shortestPathDfsHelper(maze, nextR, nextC, dstR, dstC, path, rows, cols, lengthSofar+1, visited);
				setIndent(actual);
			}
		}
		//back track
		visited[srcR][srcC] = false;
	}
}