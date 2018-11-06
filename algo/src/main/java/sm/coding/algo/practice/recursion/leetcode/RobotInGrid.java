/**
 * 
 */
package sm.coding.algo.practice.recursion.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class RobotInGrid {

	/**
	 * 

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?
https://leetcode.com/problems/unique-paths/description/

	 *
	 */
	
	public static int uniquePaths(int m, int n){
		int[][] arr = new int[m][n] ;
		int[] result = new int[1];
		
		// if robot is at 0,0 
		findPathHelper(0,0, arr, result, new ArrayList<>()); 
		
		return result[0];
	}
	
	private static void findPathHelper(int r, int c, int[][] arr, int[] result, List<Pair> sofar) {

		if(r==arr.length-1 && c==arr[r].length-1) {
			++result[0];
			return;
		}

		if(r<0 || c<0 || r>=arr.length || c>=arr[r].length) {
			return;
		}
		
		
		sofar.add(new Pair(r,c));
		findPathHelper(r+1, c, arr, result, sofar);
		findPathHelper(r, c+1, arr, result, sofar) ;
		sofar.remove(sofar.size()-1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Total possibe solutions="+uniquePaths(3,7));
	}

}

class Pair {
	int r, c;
	
	public Pair(int r , int c) {
		this.r = r;
		this.c = c;
	}

	@Override
	public String toString() {
		return "(" + r + "," + c + ")";
	}
	
	
}
