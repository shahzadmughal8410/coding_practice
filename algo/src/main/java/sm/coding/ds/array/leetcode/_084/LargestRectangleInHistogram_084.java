/**
 * 
 */
package sm.coding.ds.array.leetcode._084;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smughal
 *
 */
public class LargestRectangleInHistogram_084 {

	/**
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, 
find the area of largest rectangle in the histogram.

https://leetcode.com/problems/largest-rectangle-in-histogram/description/
https://www.geeksforgeeks.org/largest-rectangle-under-histogram/
https://www.youtube.com/watch?v=ZmnqCZp9bBs
https://github.com/mission-peace/interview/blob/master/src/com/interview/stackqueue/MaximumHistogram.java

Submission
https://leetcode.com/submissions/detail/199366805/
You are here! 
Your runtime beats 65.63 % of java submissions.


	 * @param args
	 */
	public static int largestRectangleArea(int[] heights) {
		Deque<Integer> stack = new LinkedList<>();
		
		int maxArea = 0;
		int area = 0;
		int i=0;
		
		while(i<heights.length) {
			if(stack.isEmpty() || heights[i]>heights[stack.peek()]) {
				stack.push(i++);
			}else {
				int top = stack.pop();
				area = heights[top] * (stack.isEmpty()? i : i - stack.peek() -1);
				maxArea = Math.max(area, maxArea);
			}
		}
		
		while(!stack.isEmpty()) {
			int top = stack.pop();
			area = heights[top] * (stack.isEmpty()? i : i - stack.peek() -1);
			maxArea = Math.max(area, maxArea);
		}
		return maxArea;
	}	
	
	public static void main(String[] args) {
		int[] hist = new int[] {2,1,5,6,2,3};
//		int hist[] = {6, 2, 5, 4, 5, 1, 6};
//		System.out.println("maximum area="+largestRectangleArea(hist));
		System.out.println("maximum area="+SolutionDebug.largestRectangleArea(hist));
	}

}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf("^")!=-1) {
				String[] arr = c.split(Pattern.quote("^"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}
	
	public static int largestRectangleArea(int[] hist) {
		debug("Histogram="+Arrays.stream(hist).boxed().collect(Collectors.toList()));
		debug("    Index="+IntStream.range(0, hist.length).boxed().collect(Collectors.toList()));
		Deque<Integer> stack = new LinkedList<>();
		
		int maxArea = 0;
		int area = 0;
		int i=0;
		
		tableColumns("i^3", "area", "maxArea", "stack.isEmpty()", "hist[stack.peek()] <= hist[i]", "top", "hist[top] * (stack.isEmpty()? i : i - stack.peek() -1)", "stack^10", "description^40");
		debugColumns();
		
		while(i<hist.length) {
			Object[] row = new Object[9];
			row[0] = i;
			row[1] = area;
			row[2] = maxArea;
			row[3] = stack.isEmpty();
			row[4] = !stack.isEmpty() ? "hist["+stack.peek()+"] <= hist["+i+"] -> " +hist[stack.peek()] +" <= "+hist[i] : "";
			row[5] = "";
			row[6] = "";
			row[7] = stack;
			row[8] = "";
			if(stack.isEmpty() || hist[stack.peek()] <= hist[i]) {
				stack.push(i++);
			}else {
				int top = stack.pop();
				row[5] = top;
				row[6] = hist[top]+ " * (" +stack.isEmpty()+ ") ? " +i+ " : (" +i+ " - " +stack.peek()+ " -1) --> " +hist[top]+ " * " +(stack.isEmpty()? i : i - stack.peek() -1)+" --> " + ( hist[top] * (stack.isEmpty()? i : i - stack.peek() -1) );
				area = hist[top] * (stack.isEmpty()? i : i - stack.peek() -1);
				row[8] = "Area of bar at index="+top+"["+hist[top]+"] with units="+(stack.isEmpty()? i : i - stack.peek() -1);
			}
			debugRow(row);
			maxArea = Math.max(area, maxArea);
		}
		
		reset();
		debug("Second Loop");
//		tableColumns("i:3", "area", "maxArea" , "stack:15");
		tableColumns("i^3", "area", "maxArea", "stack.isEmpty()", "top", "hist[top] * (stack.isEmpty()? i : i - stack.peek() -1)", "stack^10", "description^40");
		debugColumns();
		while(!stack.isEmpty()) {
			Object[] row = new Object[8];
			row[0] = i;
			row[1] = area;
			row[2] = maxArea;
			row[3] = stack.isEmpty();
			row[4] = "";
			row[5] = "";
			row[6] = stack;
			row[7] = "";
			int top = stack.pop();
			row[4] = top;
			row[5] = hist[top]+ " * (" +stack.isEmpty()+ ") ? " +i+ " : (" +i+ " - " +stack.peek()+ " -1) --> " +hist[top]+ " * " +(stack.isEmpty()? i : i - stack.peek() -1)+" --> " + ( hist[top] * (stack.isEmpty()? i : i - stack.peek() -1) );
			area = hist[top] * (stack.isEmpty()? i : i - stack.peek() -1);
			row[7] = "Area of bar at index="+top+"["+hist[top]+"] with units="+(stack.isEmpty()? i : i - stack.peek() -1);
			debugRow(row);
			maxArea = Math.max(area, maxArea); 
		}
		return maxArea;
	}	
}
