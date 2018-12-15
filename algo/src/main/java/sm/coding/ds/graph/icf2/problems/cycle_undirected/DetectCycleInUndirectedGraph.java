/**
 * 
 */
package sm.coding.ds.graph.icf2.problems.cycle_undirected;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class DetectCycleInUndirectedGraph {

	/**
https://www.geeksforgeeks.org/clone-directed-acyclic-graph/

	 */
	public static boolean hasCycle(Map<Character, Set<Character>> graph) {
		Set<Character> visited = new HashSet<>();
		for(Map.Entry<Character, Set<Character>> entry: graph.entrySet()) {
			if(!visited.contains(entry.getKey())) {
				if(hasCycleHelper(graph, visited, entry.getKey(), null)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean hasCycleHelper(Map<Character, Set<Character>> graph, Set<Character> visited, Character v, Character parent) {
		visited.add(v);
		
		if(null!=graph.get(v)) {
			for(Character neighbors: graph.get(v)) {
				if(!visited.contains(neighbors)) {
					if(hasCycleHelper(graph, visited, neighbors, v)){
						return true;
					}
				// if its already visited and neighbor is not same as parent means a an extra edge detected	
				}else if(neighbors!=parent) {
					return true;					
				}
			}
		}		
		return false;
	}

	public static void removeCycle(Map<Character, Set<Character>> graph) {
		Set<Character> visited = new HashSet<>();
		List<Pair> removeList = new ArrayList<>();// we can not directly remove from map while iterating over it gives ConcurrentModification exception
		for(Map.Entry<Character, Set<Character>> entry: graph.entrySet()) {
			if(!visited.contains(entry.getKey())) {
				removeCycleHelper(graph, visited, entry.getKey(), null, removeList);
			}
		}
		for(Pair p: removeList) {
			graph.get(p.a).remove(p.b);
		}
		System.out.println("removal list="+removeList);
		
	}
	
	public static void removeCycleHelper(Map<Character, Set<Character>> graph, Set<Character> visited, Character v, Character parent, List<Pair> removeList) {
		visited.add(v);
		
		if(null!=graph.get(v)) {
			for(Character neighbors: graph.get(v)) {
				// skip the neighbors if its the same as of parent
				// i.e. bidirectional link between A<-->B
				if(!visited.contains(neighbors)) {
					removeCycleHelper(graph, visited, neighbors, v, removeList);
				}else if(null!=parent && neighbors!=parent) {
					// mark for removal
					Pair p1 = new Pair(v,neighbors);
					Pair p2 = new Pair(neighbors, v);
					removeList.add(p1);					
					removeList.add(p2);			
					System.out.println("to be removed= "+p1+" , "+p2);
				}
			}
		}		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// 1- Just one cycle in graph
		Map<Character, Set<Character>> graph = new HashMap<>();
		
		graph.put('A', new HashSet<>());
		graph.put('B', new HashSet<>());
		graph.put('C', new HashSet<>());
		graph.put('D', new HashSet<>());

		graph.get('A').add('B');
		graph.get('A').add('D');
		
		graph.get('B').add('A');
		graph.get('B').add('C');

		graph.get('C').add('B');
		graph.get('C').add('D');

		graph.get('D').add('A');
		graph.get('D').add('C');
		
		
		boolean cycle = hasCycle(graph);
		
		System.out.println("graph="+graph);
		System.out.println("cycle="+cycle);

		if(cycle) {
			removeCycle(graph);
			cycle = hasCycle(graph);
			System.out.println("graph="+graph);
			System.out.println("cycle="+cycle);
		}
		
		// 2- Two cycle in graph
		graph = new HashMap<>();
		
		graph.put('A', new HashSet<>());
		graph.put('B', new HashSet<>());
		graph.put('C', new HashSet<>());
		graph.put('D', new HashSet<>());

		graph.get('A').add('B');
		graph.get('A').add('D');
		
		graph.get('B').add('A');
		graph.get('B').add('C');

		graph.get('C').add('B');
		graph.get('C').add('D');
		graph.get('C').add('A');// diagonal edge from C-A

		graph.get('D').add('A');
		graph.get('D').add('C');
		
		
		cycle = hasCycle(graph);
		
		System.out.println("graph="+graph);
		System.out.println("cycle="+cycle);

		if(cycle) {
			removeCycle(graph);
			cycle = hasCycle(graph);
			System.out.println("graph="+graph);
			System.out.println("cycle="+cycle);
		}		

		// 3- All vertices connected to each other cycle in graph
		graph = new HashMap<>();
		
		graph.put('A', new HashSet<>());
		graph.put('B', new HashSet<>());
		graph.put('C', new HashSet<>());
		graph.put('D', new HashSet<>());

		graph.get('A').add('B');
		graph.get('A').add('D');
		graph.get('A').add('C');
		
		graph.get('B').add('A');
		graph.get('B').add('C');
		graph.get('B').add('D');

		graph.get('C').add('A');
		graph.get('C').add('B');
		graph.get('C').add('D');

		graph.get('D').add('A');
		graph.get('D').add('B');
		graph.get('D').add('C');
		
		
		cycle = hasCycle(graph);
		
		System.out.println("graph="+graph);
		System.out.println("cycle="+cycle);

		if(cycle) {
			removeCycle(graph);
			cycle = hasCycle(graph);
			System.out.println("graph="+graph);
			System.out.println("cycle="+cycle);
		}		

	}

}

class Pair {
	char a;
	char b;
	
	public Pair(char a, char b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public String toString() {
		return "(" + a + "," + b + ")";
	}
	
}
