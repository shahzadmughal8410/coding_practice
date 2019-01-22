/**
 * 
 */
package sm.coding.ds.array.leetcode._057;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class InsertInterval_057 {

	
	/**

Hard
Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

Submission
https://leetcode.com/submissions/detail/202986701/
You are here! 
Your runtime beats 89.74 % of java submissions.


	 * @param intervals
	 * @param newInterval
	 * @return
	 */
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {       
		List<Interval> result = new LinkedList<>();
		int i = 0;
		// add all the intervals ending before newInterval starts
		while (i < intervals.size() && intervals.get(i).end < newInterval.start)
			result.add(intervals.get(i++));
		
		Interval previous = newInterval;
		
		for(; i<intervals.size();i++) {
			Interval current = intervals.get(i);
			if(previous.end>=current.start) {
				// as new interval is not sorted so need to set both start and end
				previous.start = Math.min(previous.start, current.start);
				previous.end = Math.max(previous.end, current.end);
			}else {
				result.add(previous);
				previous = current;
			}
		}
		result.add(previous);
		return result;
    }
    
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Interval> intervals = new ArrayList<>(); 
		intervals.add(new Interval(8,10));
		intervals.add(new Interval(6,7));
		intervals.add(new Interval(9,11));
		intervals.add(new Interval(14,15));
		intervals.add(new Interval(4,5));
		intervals.add(new Interval(13,16));
		intervals.add(new Interval(2,4));

		Collections.sort(intervals, (a,b) -> Integer.compare(a.start, b.start));
		
		System.out.println("Intervals total="+intervals.size());
		System.out.println("Intervals="+intervals);
//		List<Interval> result = merge(intervals);
		List<Interval> result = insert(intervals, new Interval(6,9));
		System.out.println("Merged intervals="+result.size());
		System.out.println(result);	}

}

class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
     
     
}
