/**
 * 
 */
package sm.coding.ds.graph.leetcode._200;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author smughal
 *
 */
public class NumberOfIslands_200_DFS_BFS {

	/**
	 * 
SIMILAR QUESRTION: find number of connected components in graph.
 
Given a boolean 2D matrix, find how many islands it has.
An island is a group of connected '1's. For example, the matrix below has 5 islands:
{1, 1, 0, 0, 0},
{0, 1, 0, 0, 1},
{1, 0, 0, 1, 1},
{0, 0, 0, 0, 0},
{1, 0, 1, 0, 1}
* Input matrix may or may not be square
* Group of connected '1's can be in any direction i.e. up, down, sideways or diagonally. There
are 8 of these possible directions
* Individual '1s' are considered an island by themselves. There are 3 of those above, in the last
line
* You don't have to provide co-ordinates of islands; just have to tell the total count of islands
About expected solution
* You are allowed to modify the input matrix
* Use as little extra memory as you can
*  
Submission
https://leetcode.com/submissions/detail/181727812/
You are here! 
Your runtime beats 14.80 % of java submissions.

	 * @param args
	 */
	public static int numIslands_Dfs(char[][] arr) {
		int count = 0;
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		for(int r = 0 ; r<arr.length; r++) {
			for(int c = 0; c<arr[r].length; c++) {
				if(arr[r][c]=='1') {
					numIslandsHelper_Dfs(arr, r, c, rows, cols);
					++count;
				}
			}			
		}
		return count;
	}
	
	public static void numIslandsHelper_Dfs(char[][] arr, int r, int c, int[] rows, int[] cols) {
		arr[r][c] = '0';
		for(int i =0;i<rows.length;i++) {
			int nextR = r+rows[i];
			int nextC = c+cols[i];
			if(isValid(arr, nextR, nextC)) {
				if(arr[nextR][nextC]=='1') {
					numIslandsHelper_Dfs(arr, nextR, nextC, rows, cols);
				}
			}
		}
	} 
	/**
Submitted
https://leetcode.com/submissions/detail/181731278/
You are here! 
Your runtime beats 18.36 % of java submissions.
	 * @param arr
	 * @return
	 */
	public static int numIslands_Bfs(char[][] arr) {
		int count = 0;
		int[] rows = new int[] { 0, 0, -1, 1};
		int[] cols = new int[] {-1, 1,  0, 0};
		for(int r = 0 ; r<arr.length; r++) {
			for(int c = 0; c<arr[r].length; c++) {
				if(arr[r][c]=='1') {
					++count;
					
					Deque<Pair> q = new LinkedList<>();
					q.offer(new Pair(r,c));
					arr[r][c] = '0'; // mark visited
					
					while(!q.isEmpty()) {
						Pair current = q.poll();
						
						for(int i =0; i<rows.length; i++) {
							int nextR = current.r+rows[i];
							int nextC = current.c+cols[i];
							if(isValid(arr, nextR, nextC) && arr[nextR][nextC]=='1') {
								q.offer(new Pair(nextR, nextC));
								arr[nextR][nextC] = '0';
							}
						}
					}
				}
			}			
		}
		return count;
	}

	
	public static boolean isValid(char[][] arr, int r, int c) {
		if(r<0 || c<0 || r>=arr.length || c>=arr[r].length){
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		char[][] arr = new char[][] {
			{'1', '1', '0', '0', '0'},
			{'0', '1', '0', '0', '1'},
			{'1', '0', '0', '1', '1'},
			{'0', '0', '0', '0', '0'},
			{'1', '0', '1', '0', '1'}
		};
		
		System.out.println("DFS "+numIslands_Dfs(arr));
		// as dfs is working in place so re initialize the matrix
		arr = new char[][] {
			{'1', '1', '0', '0', '0'},
			{'0', '1', '0', '0', '1'},
			{'1', '0', '0', '1', '1'},
			{'0', '0', '0', '0', '0'},
			{'1', '0', '1', '0', '1'}
		};
		System.out.println("BFS "+numIslands_Bfs(arr));
	}

}

class Pair {
	int r;
	int c;
	
	public Pair(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

