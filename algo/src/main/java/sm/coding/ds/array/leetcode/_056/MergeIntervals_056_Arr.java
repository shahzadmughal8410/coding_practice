/**
 * 
 */
package sm.coding.ds.array.leetcode._056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author smughal
 *
 */
public class MergeIntervals_056_Arr {

	/**
	 * 
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].
 
https://leetcode.com/problems/merge-intervals/description/
https://www.geeksforgeeks.org/merging-intervals/ 

Submission
https://leetcode.com/submissions/detail/192515654/
You are here! 
Your runtime beats 38.24 % of java submissions.
	 * @param args
	 */
	public static List<Interval> merge(List<Interval> intervals){
		if(null==intervals || intervals.size()<=1) {
			return intervals;
		}
		List<Interval> result = new LinkedList<>();
		
		Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		
		Interval previous = intervals.get(0);
		
		for(int i=1; i<intervals.size();i++) {
			Interval current = intervals.get(i);
			if(previous.end>=current.start) {
				previous.end = Math.max(previous.end, current.end);
			}else {
				result.add(previous);
				previous = current;
			}
		}
		result.add(previous);
		return result;
	}
	
	public static void main(String[] args) {
//		List<Interval> intervals = new ArrayList<>(); 
//		intervals.add(new Interval(1,3));
//		intervals.add(new Interval(2,6));
//		intervals.add(new Interval(8,10));
//		intervals.add(new Interval(15,18));

//		List<Interval> intervals = new ArrayList<>(); 
//		intervals.add(new Interval(6,8));
//		intervals.add(new Interval(1,9));
//		intervals.add(new Interval(2,4));
//		intervals.add(new Interval(4,7));

		List<Interval> intervals = new ArrayList<>(); 
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(6,7));
		intervals.add(new Interval(9,11));
		intervals.add(new Interval(14,15));
		intervals.add(new Interval(4,5));
		intervals.add(new Interval(13,16));
		intervals.add(new Interval(2,4));

		System.out.println("Intervals total="+intervals.size());
		System.out.println("Intervals="+intervals);
//		List<Interval> result = merge(intervals);
		List<Interval> result = SolutionDebug.merge(intervals);
		System.out.println("Merged intervals="+result.size());
		System.out.println(result);
	}

}

class Interval {
	int start;
	int end;
	
	public Interval(int start, int end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public String toString() {
		return "(" + start + "," + end + ")";
	}
}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
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
	
	public static List<Interval> merge(List<Interval> intervals){
		if(null==intervals || intervals.size()<1) {
			return intervals;
		}
		debug("       Intervals="+intervals);
		List<Interval> result = new LinkedList<>();
		
		Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		debug("Sorted Intervals="+intervals);
		
		Interval previous = intervals.get(0);
		
		for(int i=1; i<intervals.size();i++) {
			Interval current = intervals.get(i);
			
			debug("Previous="+previous);
			debug(" Current="+current);
			debug("previous.end>=current.start ==> "+previous.end+">="+current.start+" = "+(previous.end>=current.start));
			if(previous.end>=current.start) {
				debug("Math.max(previous.end, current.end) ==> Math.max("+previous.end+", "+current.end+") = "+(Math.max(previous.end, current.end)));
				previous.end = Math.max(previous.end, current.end);
			}else {
				debug("Adding previous to output="+previous);
				result.add(previous);
				previous = current;
			}
		}
		debug("After loop adding previous to output="+previous);
		result.add(previous);
		debug("Output="+result);
		return result;
	}
}
