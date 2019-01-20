/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.connected_components;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class ConnectedComponentsInUndirectedGraph_Graph {

	/**
Count number of connected components in an undirected graph

This can be done using either dfs or bfs.

https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/

	 * 
	 */
	public static void findConnectedComponents_Dfs(List<Integer>[] adjList) {
		boolean[] visited = new boolean[adjList.length];
		
		for(int i =0 ; i<adjList.length; i++) {
			if(!visited[i]) {
				int[] count = new int[1];
				dfs(i, visited, adjList, count);
				System.out.println(" : component number="+count[0]);
			}
		}
	}
	
	static void dfs(int n, boolean[] visited, List<Integer>[] adjList, int[] count) {
		visited[n] = true;
		List<Integer> childs = adjList[n];
		++count[0];
		System.out.print(n+" ");
		for(int c:childs) {
			if(!visited[c]) {
				dfs(c, visited, adjList, count);
			}
			
		}
	}
	
	public static void findConnectedComponents_Bfs(List<Integer>[] adjList) {

		boolean[] visited = new boolean[adjList.length];
		
		for(int i=0; i<adjList.length;i++) {
			int count = 0;
			if(!visited[i] && adjList[i]!=null && adjList[i].size()>0) {
				Deque<Integer> q = new LinkedList<>();
				q.offer(i);
				visited[i] = true;				
				while(!q.isEmpty()) {
					int current = q.poll();					
					++count;
					System.out.print(current+" ");
					for(int vertex:adjList[current]) {
						if(!visited[vertex]) {
							q.offer(vertex);
							visited[vertex] = true;
						}
					}			
					
				}				
				System.out.println(" : component number="+count);
			}
		}
	}
	
	public static void main(String[] args) {
		List<Integer>[] adjList = new LinkedList[5];
		
		adjList[0] = new LinkedList<>();
		adjList[1] = new LinkedList<>();
		adjList[2] = new LinkedList<>();
		adjList[3] = new LinkedList<>();
		adjList[4] = new LinkedList<>();
		
		adjList[0].add(1);
		adjList[0].add(2);
		
		adjList[1].add(0);
		adjList[2].add(1);
//		adjList[2].add(3); this will make graph as 1 component

		adjList[3].add(4);
		adjList[4].add(3);
		
		findConnectedComponents_Dfs(adjList);
		findConnectedComponents_Bfs(adjList);

		
	}
}
