/**
 * 
 */
package sm.coding.algo.practice.recursion.ctci._08_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smughal
 *
 */
public class RobotInGrid_8_2 {

	/**
	 * 
Robot in a Grid: Imagine a robot sitting on the upper left corner of grid with r rows and c columns.
The robot can only move in two directions, right and down, but certain cells are "off limits" such that
the robot cannot step on them. Design an algorithm to find a path for the robot from the top left to
the bottom right.
Hints: #331, #360, #388

	 *
	 */
	
	public static List<Pair> findPath(int[][] arr){
		List<Pair> result = null;
		
		// if robot is at 0,0 
		result = new ArrayList<>();
		findPathHelper(0,0, arr, result, new ArrayList<>());

		return result;
	}
	
	private static boolean findPathHelper(int r, int c, int[][] arr, List<Pair> result, List<Pair> sofar) {

		if(r==arr.length-1 && c==arr[r].length-1) {
			ArrayList<Pair> clone = new ArrayList<>(sofar);
			clone.add(new Pair(r,c));
			result.addAll(clone);
			return true;
		}

		if(r<0 || c<0 || r>=arr.length || c>=arr[r].length || arr[r][c]==1) {
			return false;
		}
		
		
		sofar.add(new Pair(r,c));
		boolean isPathExists = 	findPathHelper(r+1, c, arr, result, sofar) ||
								findPathHelper(r, c+1, arr, result, sofar) ;
		sofar.remove(sofar.size()-1);
		return isPathExists;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] arr1 = new int[][] {
			{0,0,0,0},
			{0,0,0,0},
			{1,1,0,1},
			{0,0,0,0}
		};

		List<Pair> result = findPath(arr1);
		System.out.println("Length="+result.size()+", result="+result);
		
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
