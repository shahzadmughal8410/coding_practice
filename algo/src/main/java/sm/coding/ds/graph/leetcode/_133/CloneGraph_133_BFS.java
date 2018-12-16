/**
 * 
 */
package sm.coding.ds.graph.leetcode._133;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author smughal
 *
 */
public class CloneGraph_133_BFS {

	/**
Clone a graph and verify the clone
Given a reference to a connected, undirected graph, clone it and verify it
* Please hard code a graph input in your solution, clone it like the problem asks and return a reference to the clone.
* Then compare the clone to the original graph,
* Structure of each node of the graph has: a value and a list of neighbors.
eg:
class GraphNode {
int val;
// A neighbour List which contains references to
// all the neighbours of a GraphNode
List<GraphNode> neighbours;
public GraphNode( int val) {
this .val = val;
neighbours = new ArrayList<GraphNode>();
}
}
* Edges are not directional, and not weighted. We don't care about what value each node has.
* This is an exercise in cloning, as well as traversing two graphs together (input and cloned),
for comparison.
Implement the below methods:
GraphNode cloneGraph(GraphNode source)
boolean isEqual(GraphNode source, GraphNode dest) 

Submission
https://leetcode.com/submissions/detail/181322662/
You are here! 
Your runtime beats 69.60 % of java submissions.
	 * 
	 * @param args
	 */
	public static UndirectedGraphNode cloneGraph(UndirectedGraphNode source) {
		if(null==source) {
			return null;
		}
		HashMap<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
		Queue<UndirectedGraphNode> q = new LinkedList<>();
		
		UndirectedGraphNode clonedSource = new UndirectedGraphNode(source.label);

		q.offer(source);
		visited.put(source, clonedSource);
		
		while(!q.isEmpty()) {
			UndirectedGraphNode current = q.poll();
			UndirectedGraphNode currentClone = visited.get(current);
			for(UndirectedGraphNode currentChild: current.neighbors) {
				UndirectedGraphNode clonedChild = visited.get(currentChild);
				if(null==clonedChild) {
					q.offer(currentChild);
					clonedChild = new UndirectedGraphNode(currentChild.label);
					visited.put(currentChild, clonedChild);
				}
				// as 1 node can be a neighbor/child of many nodes, so always add as neighbour, not only at the time of creation
				currentClone.neighbors.add(clonedChild);
			}
		}
		return clonedSource;
	}
	
	/**
Submission
https://leetcode.com/submissions/detail/195302797/
You are here! 
Your runtime beats 61.80 % of java submissions.
	 * @param source
	 * @return
	 */
	public static UndirectedGraphNode cloneGraph_BFS_Stack(UndirectedGraphNode source) {
		if(null==source) {
			return null;
		}
		HashMap<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
		Deque<UndirectedGraphNode> stack = new LinkedList<>();
		
		UndirectedGraphNode clonedSource = new UndirectedGraphNode(source.label);

		stack.push(source);
		visited.put(source, clonedSource);
		
		while(!stack.isEmpty()) {
			
			int size = stack.size(); // for level by level processing any collection can be used
			for(int i =0; i<size; i++) {
				
				UndirectedGraphNode current = stack.pop();
				UndirectedGraphNode currentClone = visited.get(current);
				for(UndirectedGraphNode currentChild: current.neighbors) {
					UndirectedGraphNode clonedChild = visited.get(currentChild);
					if(null==clonedChild) {
						stack.push(currentChild);
						clonedChild = new UndirectedGraphNode(currentChild.label);
						visited.put(currentChild, clonedChild);
					}
					// as 1 node can be a neighbor/child of many nodes, so always add as neighbour, not only at the time of creation
					currentClone.neighbors.add(clonedChild);
				}
			}
		}
		return clonedSource;
	}
	
	
	public static boolean equals(UndirectedGraphNode n1, UndirectedGraphNode n2) {
		
		HashMap<UndirectedGraphNode, UndirectedGraphNode> visited = new HashMap<>();
		Queue<UndirectedGraphNode> q = new LinkedList<>();

		q.offer(n1);
		visited.put(n1, n2);
		
		while(!q.isEmpty()) {
			UndirectedGraphNode node1 = q.poll();
			UndirectedGraphNode node2 = visited.get(node1);
			if(node2==null || node1.label!=node2.label || node1.neighbors.size()!=node2.neighbors.size()) {
				return false;
			}
			
			for(int i =0; i<node2.neighbors.size();i++) {
				UndirectedGraphNode child1 = node1.neighbors.get(i);
				UndirectedGraphNode child2 = visited.get(child1);
				
				if(null==child2) {
					child2 = node2.neighbors.get(i);
					q.offer(child1);
					visited.put(child1, child2);
				}
			}
		}
		return true;
	}	
	
	public static UndirectedGraphNode print(UndirectedGraphNode n) {
		if(null== n) {
			return null;
		}
		HashMap<UndirectedGraphNode, Boolean> visited = new HashMap<>();
		Queue<UndirectedGraphNode> q = new LinkedList<>();

		q.offer(n);
		visited.put(n, Boolean.TRUE);
		System.out.println("Printing graph from node: "+n);
		while(!q.isEmpty()) {
			UndirectedGraphNode node = q.poll();
			System.out.print(node.label+"-->");
			for(UndirectedGraphNode child:node.neighbors) {
				if(!visited.containsKey(child)) {					
					q.offer(child);
					visited.put(child, Boolean.TRUE);
				}
				System.out.print(child);
			}
			System.out.println();
		}
		return null;
	}
	
	public static void main(String[] args) {
		UndirectedGraphNode three = new UndirectedGraphNode(3);
		UndirectedGraphNode two = new UndirectedGraphNode(2);
		two.neighbors.add(three);
		UndirectedGraphNode one = new UndirectedGraphNode(1);
		one.neighbors.add(two);
		
		three.neighbors.add(one);
		
		System.out.println("Original graph");
		print(one);
		
		UndirectedGraphNode clone = cloneGraph(one);
		System.out.println("Cloned graph");
		print(clone);
		
		UndirectedGraphNode differentGraph = new UndirectedGraphNode(3);
		
		System.out.println("Equals="+equals(one, clone));
		System.out.println();
		
		System.out.println("Graph one");
		print(one);
		System.out.println("Different Graph");
		print(differentGraph);
		System.out.println("Equals="+equals(one, differentGraph));
		System.out.println();
		
		System.out.println("Graph one");
		print(one);
		System.out.println("Graph two");
		print(two);
		System.out.println("Equals="+equals(one, two));
		
	}

}


class UndirectedGraphNode {

    int label;
    // A neighbour List which contains references to
    // all the neighbours of a GraphNode
    List<UndirectedGraphNode> neighbors;

    public UndirectedGraphNode(int x) {
        this.label = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }

	@Override
	public String toString() {
		return "(val="+label+", hashcode=" + hashCode() + ", neighboursSize="+neighbors.size()+", neighboursHashcode=" + neighbors.hashCode() + ")";
	}
    
    

}