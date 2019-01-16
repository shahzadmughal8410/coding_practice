/**
 * 
 */
package sm.interview.coding_challange.dependency_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author shahzadmughal8410
 *
 */
public class DependencyList_TopologicalSort {

	/**
	 * 

A -> { 'B', 'C'},
B -> { 'C', 'D', 'E'},
C -> { 'D', 'E'},
D -> { },
E -> { 'F' },
F -> { } 

Order of dependencies
f, e, d, c, b, a

	 * @param args
	 */
	// T = O (V+E)
	public static List<String> findOrder(Map<String, Set<String>> dependencyList) {
		dependencyList.forEach((k,v) -> System.out.println(k+" --> "+v));

//		Map<String, Integer> map = buildDependencies(dependencyList);
		Map<String, Integer> map = inDegree(dependencyList);
		
		Deque<String> q = new LinkedList<>();
		LinkedList<String> result = new LinkedList<>(); // to store dependencies in reverse order
		
		for(String key: map.keySet()) {
			if(map.get(key)==0) {
				q.offer(key);
			}
		}
		
		while(!q.isEmpty()) {
			String s = q.poll();
			result.push(s); // note use of push instead of add, as we want to maintain the reverse order

			Set<String> n = dependencyList.get(s);
			for(String neighbour :n) {
				map.put(neighbour, map.get(neighbour)-1);
				if(map.get(neighbour)==0) {
					q.offer(neighbour);
				}
			}
			
			// this also works but is not efficient: t = O(V^2 + E)
//			for(String neighbour: dependencyList.keySet()) {
//				List<String> n = dependencyList.get(neighbour);
//				if(n.contains(s)) {
//					map.put(neighbour, map.get(neighbour)-1);
//					if(map.get(neighbour)==0) {
//						q.offer(neighbour);
//					}
//				}
//			}
		}
		
		if(result.size()!=dependencyList.size()) {
			System.out.println("Graph has a cycle");
		}
		
		return result;
	}
	
	public static Map<String, Integer> buildDependencies(Map<String, Set<String>> dependencyList){
		Map<String, Integer> map = new HashMap<>();
		
		for(Map.Entry<String, Set<String>> entry : dependencyList.entrySet()) {
			map.put(entry.getKey(), entry.getValue().size());
		}
		
		return map;
	}
	// T = O(E)
	public static Map<String, Integer> inDegree(Map<String, Set<String>> dependencyList){
		Map<String, Integer> map = new HashMap<>();
		
		for(String key : dependencyList.keySet()) {
			map.put(key, 0);
		}
		
		for(String key : dependencyList.keySet()) {
			for(String neighbours : dependencyList.get(key)) {
				map.put(neighbours, map.get(neighbours)+1);
			}
		}
		
		map.forEach((k,v) -> System.out.println(k+" --> "+v));
		
		return map;
	}
	
	public static void main(String[] args) {
		Map<String, Set<String>> dependencyList = new HashMap<>();
		dependencyList.put("a", new HashSet<>(Arrays.asList("b", "c")));
		dependencyList.put("b", new HashSet<>(Arrays.asList("c", "d", "e")));
		dependencyList.put("c", new HashSet<>(Arrays.asList("d", "e")));
		dependencyList.put("d", new HashSet<>(Arrays.asList()));
		dependencyList.put("e", new HashSet<>(Arrays.asList("f")));
		dependencyList.put("f", new HashSet<>(Arrays.asList()));
//		dependencyList.put("f", new HashSet<>(Arrays.asList("e"))); // for cyclic graph, we will not get the answer
		
		List<String> result = findOrder(dependencyList);		
		System.out.println(result);

	}

}
