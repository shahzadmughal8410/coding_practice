/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.connected_nodes;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class TwoNodesAreConnected_Graph_BFS_Q7 {

	/**
	 * Given a DAG and src and dest (vertices) they are connected or not.
	 * 
	 * 1- Calarifying questions:
	 * Should I assume graph structure is alrady there classes etc.
	 * If nodes are integer can we assume the following graph structure
	 * LinkedList<Integer>[] adj = new LinkedList<>[];
	 * 
	 * 2- Determine Input/output
	 * input: int src , int dest
	 * output: boolean		
	 * 
	 * T=O(v+e)
	 * S=O(v)
	 * 
	 */
	static boolean isConnected(int src, int dest, LinkedList<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		
		Deque<Integer> q = new LinkedList<>();
		q.offer(src);
		visited[src] = true;
		
		while(! q.isEmpty()) {
			Integer current = q.poll();
			if(current == dest) {
				return true;
			}
			if(adjList[current]!=null) { 
				for(Integer child :adjList[current]) {
					if(!visited[child]) {
						q.offer(child);
						visited[child] = true;
					}
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		LinkedList<Integer>[] adjList = new LinkedList[3];
		adjList[0] = new LinkedList<>();
		adjList[1] = new LinkedList<>();
		adjList[2] = new LinkedList<>();
		
		adjList[0].add(1);
		adjList[0].add(2);
		
		adjList[1].add(2);
		
		System.out.println("0-->2 = "+isConnected(0, 2, adjList));
		System.out.println("2-->0 = "+isConnected(2, 0, adjList));
	}
}
