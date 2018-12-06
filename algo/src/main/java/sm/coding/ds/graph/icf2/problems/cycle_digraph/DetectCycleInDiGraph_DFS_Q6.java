/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.cycle_digraph;

import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class DetectCycleInDiGraph_DFS_Q6 {

	/**
Given a di-graph check if there is a cycle in the graph.

isVisited
isAlive


https://www.geeksforgeeks.org/detect-cycle-in-a-graph/

	 */
	
	public static boolean isCycle(List<Integer>[] adjList) {
	
		boolean[] isVisited = new boolean[adjList.length];
		boolean[] isInPath = new boolean[adjList.length];
		
		for(int i = 0; i<adjList.length; i++) {
			if(!isVisited[i]) {
				if(dfs(i, isVisited, isInPath, adjList)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static boolean dfs(int n, boolean[] isVisited, boolean[] isInPath, List<Integer>[] adjList) {
		// mark as visited
		isVisited[n] = true;
		isInPath[n] = true;
		
		// process childs
		for(int c:adjList[n]) {
			if(!isVisited[c]) {
				if(dfs(c, isVisited, isInPath, adjList)) {
					return true;
				}
			}
			else if(isInPath[c]) {
				return true;
			}
		}
		isInPath[n] = false;// backtrack
		return false;
	}
	
	public static void main(String[] args) {
		List<Integer>[] adjList = new LinkedList[6];
		adjList[0] = new LinkedList<>();
		adjList[1] = new LinkedList<>();
		adjList[2] = new LinkedList<>();
		adjList[3] = new LinkedList<>();
		adjList[4] = new LinkedList<>();
		adjList[5] = new LinkedList<>();
		
		adjList[0].add(1);
		adjList[0].add(2);
		
		adjList[1].add(2);
		
//		adjList[2].add(0);
		adjList[2].add(3);

//		adjList[3].add(3);

		adjList[4].add(5);
		adjList[5].add(4);

		System.out.println("Cycle present = "+isCycle(adjList));

	}
}
