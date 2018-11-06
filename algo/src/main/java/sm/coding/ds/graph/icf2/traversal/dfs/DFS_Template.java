package sm.coding.ds.graph.icf2.traversal.dfs;


/**
 * DFS
 * Traverse depth of each path first.
 * 
 * Pros:
 * Used to get actual path.
 * 
 * Cons:
 * Shortest path cannot be found.
 * @author shahzadmughal8410
 *
 */
public class DFS_Template {

	/**
	 * DFS Template
	 */
	
	public void dfsDriver(Node[] nodes) {
		// 1- starting point, to process all nodes in disconnected sub graphs in this graph
		for(Node node :nodes) {
			dfs(node);
		}
	}
	
	public void dfs(Node node) {
		// 2- mark visited
		markVisited(node);
		
		// 3- process node
		
		// 4- process child's
		for(Node child: node.getChilds()) { // implicit base condition, proceed only if childs are present
			// 5- if not visited
			if(! isVisited(child)) {
				// 6- recurse
				dfs(child);
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