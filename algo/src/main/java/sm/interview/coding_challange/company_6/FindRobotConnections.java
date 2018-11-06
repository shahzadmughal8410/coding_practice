/**
 * 
 */
package sm.interview.coding_challange.company_6;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class FindRobotConnections {

	public static Map<Character, List<Character>> buildGraph(List<Character> robots, List<List<Character>> conns) {
		if (null == robots || null == conns) {
			return null;
		}
		Map<Character, List<Character>> graph = new HashMap<>();

		for (int i = 0; i < robots.size(); i++) {
			graph.putIfAbsent(robots.get(i), new ArrayList<>());
		}
		// all the robots with empty connections
		for (int i = 0; i < conns.size(); i++) {
			List<Character> con = conns.get(i);
			// con always of size 2
			graph.get(con.get(0)).add(con.get(1));
			graph.get(con.get(1)).add(con.get(0));
		}

		// graph is constructed
		return graph;
	}
	/*
	 [[a, b, c, d], [e, f, g]]
	 a -> b
	 b -> a, c
	 c-> b, d
	 d-> c 

	*/
	public static List<List<Character>> traverse(Map<Character, List<Character>> graph, List<Character> robots) {

		List<List<Character>> groups = new ArrayList<>();
		Deque<Character> q = new LinkedList<>();
		Set<Character> visited = new HashSet<>();

		for (Character c : robots) {
			if (!visited.contains(c)) {
				List<Character> group = new ArrayList<>();
				visited.add(c);
				group.add(c);
				q.offer(c);

				while (!q.isEmpty()) {
					Character node = q.poll();
					for (Character child : graph.get(node)) {
						if (!visited.contains(child)) {
							visited.add(child);
							group.add(child);
							q.offer(child);
						}
					}
				}
				groups.add(group);
			}
		}
		return groups;
	}

	public static List<List<Character>> findConnections(List<Character> robots, List<List<Character>> conns) {
		Map<Character, List<Character>> graph = buildGraph(robots, conns);
		List<List<Character>> groups = traverse(graph, robots);
		return groups;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// example 1
		List<Character> robots = new String (new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'}).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		char[][] connections = new char[][] {{'a', 'b'}, {'b', 'c'}, {'c', 'd'}, {'e', 'f'}, {'f', 'g'}};
		List<List<Character>> conns = toList(connections);
		List<List<Character>> groups = findConnections(robots, conns);
		groups.forEach(g->System.out.print(g+" "));
		System.out.println();

		// example 2
		robots = new String (new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'}).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		connections = new char[][] {{'a', 'b'}, {'b', 'c'}, {'c', 'f'}, {'f', 'g'}, {'a', 'd'}, {'d', 'e'}};
		conns = toList(connections);
		groups = findConnections(robots, conns);
		groups.forEach(g->System.out.print(g+" "));
		System.out.println();

		// example 3
		robots = new String (new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g'}).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
		connections = new char[][] {{'a', 'b'}, {'a', 'c'}, {'d', 'e'}};;
		conns = toList(connections);
		groups = findConnections(robots, conns);
		groups.forEach(g->System.out.print(g+" "));
		System.out.println();

	}
	
	private static List<List<Character>> toList(char[][] connections){
		List<List<Character>> conns = new ArrayList<>();
		for(int i =0; i<connections.length;i++) {
			char[] arr = connections[i];
			List<Character> c = new ArrayList<>();
			c.add(arr[0]);
			c.add(arr[1]);
			conns.add(c);
		}
		return conns;
	} 

}
