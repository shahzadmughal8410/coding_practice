/**
 * 
 */
package sm.coding.ds.heap.leetcode._253;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * @author smughal
 *
 */
public class MeetingRooms2_253 {

	/**
	 *
	 *
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), 
find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.

https://leetcode.com/problems/meeting-rooms-ii/description/ 
https://www.youtube.com/watch?v=GmpyAMpjpUY
https://www.youtube.com/watch?v=118Ie3nPCdc

T=O(n log n)
S=O(n) 

Submission
https://leetcode.com/submissions/detail/194154875/
You are here! 
Your runtime beats 33.02 % of java submissions.
	 * @param args
	 */
	public static int minMeetingRooms(Interval[] intervals) {
		if(null==intervals || intervals.length==0) {
			return 0;
		}
		
		System.out.println("input ="+Arrays.stream(intervals).collect(Collectors.toList()));
		
		Arrays.sort(intervals, (a,b)->{return a.start-b.start;});
		
		System.out.println("sorted="+Arrays.stream(intervals).collect(Collectors.toList()));
		
		PriorityQueue<Integer> minHeap = new PriorityQueue<>(intervals.length);
		minHeap.offer(intervals[0].end);
		
		for(int i =1; i<intervals.length;i++) {
			int start = intervals[i].start;
			int end = minHeap.peek();
			
			if(end<=start) {
				minHeap.poll();
			}
			minHeap.offer(intervals[i].end);
		}
			
		return minHeap.size();
	}
	
	
	public static void main(String[] args) {
		Interval[] intervals = new Interval[] { new Interval(0, 30),new Interval(5, 10),new Interval(15, 20)};
		System.out.println("Rooms required="+minMeetingRooms(intervals));

		intervals = new Interval[] { new Interval(0, 30),new Interval(5, 10),new Interval(40, 50)};
		System.out.println("Rooms required="+minMeetingRooms(intervals));

		intervals = new Interval[] { new Interval(2, 8),new Interval(1, 5),new Interval(3, 4),new Interval(5, 7)};
		System.out.println("Rooms required="+minMeetingRooms(intervals));
	}

}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "Interval [start=" + start + ", end=" + end + "]";
	}
	
	
}
 