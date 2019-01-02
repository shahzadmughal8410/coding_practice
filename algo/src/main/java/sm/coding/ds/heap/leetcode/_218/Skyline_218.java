/**
 * 
 */
package sm.coding.ds.heap.leetcode._218;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author smughal
 *
 */
public class Skyline_218 {

	/**
	 * 
https://leetcode.com/problems/the-skyline-problem/description/ 
https://www.youtube.com/watch?v=GSBLe8cKu0s
https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java

https://www.geeksforgeeks.org/divide-and-conquer-set-7-the-skyline-problem/
https://briangordon.github.io/2014/08/the-skyline-problem.html

https://www.youtube.com/watch?v=GSBLe8cKu0s
https://www.youtube.com/watch?v=l82ZB4yxuek

https://github.com/mission-peace/interview/blob/master/src/com/interview/geometry/SkylineDrawing.java
https://www.youtube.com/watch?v=Cv0ft2dFz80

Submission
https://leetcode.com/submissions/detail/195342462/
You are here! 
Your runtime beats 76.94 % of java submissions.	 
* @param args
	 */
	public static List<int[]> getSkyline(int[][] buildings){
		List<Point> points = new ArrayList<>();
		List<int[]> result = new ArrayList<>();
		
		for(int[] b:buildings) {
			points.add(new Point(b[0], b[2], true));
			points.add(new Point(b[1], b[2], false));
		}
		
		Comparator<Point> comp = (a,b)->{
			if(a.x != b.x) {
				return a.x-b.x;
			}else {
				// for same start put the building with higher height first
				// for same end put the lower height first
				// if one start and other end, take the start of next building first
				return (a.start ? -a.h : a.h) - (b.start ? -b.h : b.h);
			}
		};
		
		Collections.sort(points, comp);
		
		// Map(height, count) count is needed just so if 2 buildings have same height
		// then we need to know while removing from treeMap
		TreeMap<Integer, Integer> q = new TreeMap<>(); // key=height, val=frequency 
		q.put(0, 1); // when we don;t have any buildings, its the ground level at Zero height.
		
		int previousHeight = 0;
		
		for(Point p:points) {
			if(p.start) {
				q.put(p.h,q.getOrDefault(p.h, 0)+1);
			}else {
				if(q.containsKey(p.h)) {
					q.put(p.h, q.get(p.h) -1);
					if(q.get(p.h)==0) {
						q.remove(p.h);
					}
				}
			}
			int currHeight = q.lastKey();
			if(currHeight!=previousHeight) {
				result.add(new int[] {p.x, currHeight});
				previousHeight = currHeight;
			}
		}		
		return result;
	}
	
	
	public static void main(String[] args) {
//		int[][] buildings = new int[][] {
//				new int[] {1, 4, 3},
//				new int[] {2, 5, 5},
//				new int[] {3, 6, 4},
//				new int[] {7, 8, 8},
//				new int[] {7, 10, 6},
//				new int[] {8, 9, 9},
//				new int[] {9, 10, 4}
//				};

		int[][] buildings = new int[][] {
			new int[] {2,9,10},
			new int[] {3,7,15},
			new int[] {5,12,12},
			new int[] {15,20,10},
			new int[] {19,24,8}
			};

		List<int[]> result = getSkyline(buildings);
		System.out.println("Total key points="+result.size());
		result.forEach(r->System.out.println(Arrays.stream( r ).boxed().collect( Collectors.toList() )));
	}

}

class Point {
	int x;
	int h; // can be named 'y' as well
	boolean start;
	public Point(int x, int h, boolean start) {
		super();
		this.x = x;
		this.h = h;
		this.start = start;
	}
	@Override
	public String toString() {
		return "(" + x + "," + h + ", " + start + ")\n";
	}
	
	
}
