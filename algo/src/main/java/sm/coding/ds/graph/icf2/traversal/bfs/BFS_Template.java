package sm.coding.ds.graph.icf2.traversal.bfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * BFS
 * Traverse level by level.
 * 
 * Pros:
 * Used to find shortest path.
 * 
 * Cons:
 * But actual path cannot be tracked.
 * 
 * @author shahzadmughal8410
 *
 */
public class BFS_Template {

	/**
	 * BFS Template
	 */
	public void bfs(Node[] nodes) {

		Deque<Node> q = new LinkedList<>();
		// to iterate through the disconnected sub graphs
		for(Node node: nodes) {
			// 1- Starting point, add in queue and mark visited 
			q.offer(node);
			markVisited(node);
		}
		
		// 2- while q is not empty
		while(! q.isEmpty()) {
			// 3- get from queue
			Node n = q.poll();
			// 4- process node
			
			// 5- process child's
			for(Node child: n.getChilds()) {
				// 6- if not visited, add in queue and mark visited
				if(!isVisited(child)) {
					q.add(child);
					markVisited(child);
				}	
			}
		}		
	}
	
	boolean isVisited(Node node){
		return false;
	}
	
	void markVisited(Node node) {
		
	}
}

class Node {

	Node[] getChilds() {
		return null;
	}
}
