/**
 * 
 */
package sm.coding.ds.graph.icf2.knightstour;

import java.util.Deque;
import java.util.LinkedList;


/**
 * @author shahzadmughal8410
 *
 */
public class KnightsTourOnChessBoard_Graph_BFS {

	// 1- propagate value as part of queue object
	// 2- do BFS level by level, single variable can be used to calculate distance
	// this is mainly because every child of node is not counted as distance, but level will be counted as distance.

	// 1- Approach - value propagation as part of queue object
	public static int shortestPathBfs_ValuePropagation(int[][] board, int srcR, int srcC, int dstR, int dstC) {
		// Boundary condition
		if(srcR>=dstR || srcC>=dstC) {
			return 0;
		}
		// available moves for knight on chessboard
		int[] rows = new int[] {-2, -1, 1, 2, -2, -1, 1, 2}; 
		int[] cols = new int[] {-1, -2, -2, -1, 1, 2, 2, 1}; 

		// matrix to mark nodes as visited
		boolean[][] visited = new boolean[board.length][board[0].length];
		
		Deque<Triplet> q = new LinkedList<>();
		// 1- start, enque and mark visited
		q.offer(new Triplet(srcR,srcC, 0)); //value propagation as part of queue object
		visited[srcR][srcC] = true;
		// two ways to count steps
		// 2- while q is not empty
		while(!q.isEmpty()) {
			// 3- get from  q
			Triplet boardIndex = q.poll();
			
			// 4- process node
			// if we reached destination return path, 
			if(boardIndex.r==dstR && boardIndex.c==dstC) {
				return boardIndex.dist;
			}
			// 5- process child's
			for(int i=0; i<rows.length; i++) {
				int nextR = boardIndex.r+rows[i];
				int nextC = boardIndex.c+cols[i];
				// check the next calculated index is valid
				if(isValid(dstR, dstC, nextR, nextC)) {
					// 6- if index is not visited 
					if(!visited[nextR][nextC]) { 
						q.offer(new Triplet(nextR,nextC, boardIndex.dist+1));
						visited[nextR][nextC] = true;
					}
				}
			}
			
		}
		return -1;
	}
	
	// 2- Approach - level by level BFS 
	public static int shortestPathBfs_level(int[][] board, int srcR, int srcC, int dstR, int dstC) {
		// Boundary condition
		if(srcR>=dstR || srcC>=dstC) {
			return 0;
		}
		// available moves for knight on chessboard
		int[] rows = new int[] {-2, -1, 1, 2, -2, -1, 1, 2}; 
		int[] cols = new int[] {-1, -2, -2, -1, 1, 2, 2, 1}; 

		// matrix to mark nodes as visited
		boolean[][] visited = new boolean[board.length][board[0].length];
		
		Deque<Pair> q = new LinkedList<>();
		// 1- start, enque and mark visited
		q.offer(new Pair(srcR,srcC));
		visited[srcR][srcC] = true;
		// 2- Approach - do BFS level by level, single variable can be used to calculate distance
		int distance = 0;
		// 2- while q is not empty
		while(!q.isEmpty()) {
			// 3- get from  q
			int size = q.size();
			for(int j=0; j<size;j++) {
				Pair boardIndex = q.poll();
				
				// 4- process node
				// if we reached destination return path, 
				if(boardIndex.r==dstR && boardIndex.c==dstC) {
					return distance;
				}
				// 5- process child's
				for(int i=0; i<rows.length; i++) {
					int nextR = boardIndex.r+rows[i];
					int nextC = boardIndex.c+cols[i];
					// check the next calculated index is valid
					if(isValid(dstR, dstC, nextR, nextC)) {
						// 6- if index is not visited 
						if(!visited[nextR][nextC]) { 
							q.offer(new Pair(nextR,nextC));
							visited[nextR][nextC] = true;
						}
					}
				}
			}
			++distance;			
		}
		return -1;
	}
	
	public static boolean isValid(int dstR, int dstC, int nextR, int nextC) {
		if(nextR<0 || nextC<0 || nextR>dstR || nextC>dstC) {
			return false;
		}
		return true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int [][]board = new int[30][30];
		System.out.println("VP  Minimum number of moves required="+shortestPathBfs_ValuePropagation(board, 0, 0, board.length-1, board[0].length-1));
		System.out.println("LBL Minimum number of moves required="+shortestPathBfs_level(board, 0, 0, board.length-1, board[0].length-1));
		
		board = new int[8][8];
		System.out.println("VP  Minimum number of moves required="+shortestPathBfs_ValuePropagation(board, 0, 0, board.length-1, board[0].length-1));
		System.out.println("LBL Minimum number of moves required="+shortestPathBfs_level(board, 0, 0, board.length-1, board[0].length-1));

		board = new int[15][15];
		System.out.println("VP  Minimum number of moves required="+shortestPathBfs_ValuePropagation(board, 0, 0, board.length-1, board[0].length-1));
		System.out.println("LBL Minimum number of moves required="+shortestPathBfs_level(board, 0, 0, board.length-1, board[0].length-1));

		board = new int[20][20];
		System.out.println("VP  Minimum number of moves required="+shortestPathBfs_ValuePropagation(board, 0, 0, board.length-1, board[0].length-1));
		System.out.println("LBL Minimum number of moves required="+shortestPathBfs_level(board, 0, 0, board.length-1, board[0].length-1));

	}

}

class Triplet {
	int r;
	int c;
	int dist;
	
	public Triplet(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Pair [r=" + r + ", c=" + c + ", dist="+dist + "]";
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