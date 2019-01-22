/**
 * 
 */
package sm.coding.ds.heap.leetcode._295;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @author shahzadmughal8410
 *
 */
public class FindMedianFromDataStream_295 {

	/**

Hard

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?

	 */
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MedianFinder mf = new MedianFinder();
		
		mf.addNum_Debug(5);
		mf.addNum_Debug(3);
		mf.addNum_Debug(10);
		mf.addNum_Debug(11);
		mf.addNum_Debug(8);
		mf.addNum_Debug(13);
		
		System.out.println("min="+mf.min);
		System.out.println("max="+mf.max);
		System.out.println("median="+mf.findMedian());
	}

}

/**
Submission
https://leetcode.com/submissions/detail/194078821/
You are here! 
Your runtime beats 73.42 % of java submissions.

 * @author shahzadmughal8410
 *
 */
class MedianFinder {

    /** initialize your data structure here. */
    // max queue is always larger or equal to min queue
    PriorityQueue<Integer> min; // will be having max value in it
    PriorityQueue<Integer> max; //  will be having min values in it
    
    /** initialize your data structure here. */
    public MedianFinder() {
        min = new PriorityQueue<>();// store max elements in lists
        max = new PriorityQueue<>(1000, Collections.reverseOrder());// store min elements in lists
    }

    // Adds a number into the data structure.
    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()){
            max.offer(min.poll());
        }
    }

    public void addNum_Debug(int num) {
        System.out.println("num="+num);

        max.offer(num);
        min.offer(max.poll());
        if (max.size() < min.size()){
            max.offer(min.poll());
        }

        System.out.println("min="+min);
		System.out.println("max="+max);

    }

    // Returns the median of current data stream
    public double findMedian() {
        if (max.size() == min.size()) return (max.peek() + min.peek()) /  2.0;
        else return max.peek();
    }
}
