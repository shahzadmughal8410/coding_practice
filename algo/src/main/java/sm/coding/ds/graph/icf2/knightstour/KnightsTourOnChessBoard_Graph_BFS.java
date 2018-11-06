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

	
	public static int shortestPathBfs(int[][] board, int srcR, int srcC, int dstR, int dstC) {
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
		q.offer(new Pair(srcR,srcC, 0));
		visited[srcR][srcC] = true;
		
		// 2- while q is not empty
		while(!q.isEmpty()) {
			// 3- get from  q
			Pair boardIndex = q.poll();
			
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
						q.offer(new Pair(nextR,nextC, boardIndex.dist+1));
						visited[nextR][nextC] = true;
					}
				}
			}
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
		System.out.println("Minimum number of moves required="+shortestPathBfs(board, 0, 0, board.length-1, board[0].length-1));

	}

}

class Pair {
	int r;
	int c;
	int dist;
	
	public Pair(int r, int c, int dist) {
		this.r = r;
		this.c = c;
		this.dist = dist;
	}

	@Override
	public String toString() {
		return "Pair [r=" + r + ", c=" + c + ", dist="+dist + "]";
	}
}