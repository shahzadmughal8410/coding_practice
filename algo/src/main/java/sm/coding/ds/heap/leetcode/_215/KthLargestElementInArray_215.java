/**
 * 
 */
package sm.coding.ds.heap.leetcode._215;

import java.util.PriorityQueue;

/**
 * @author smughal
 *
 */
public class KthLargestElementInArray_215 {

	/**
	 * 
Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example,
Given [3,2,1,5,6,4] and k = 2, return 5.

Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

https://www.geeksforgeeks.org/k-largestor-smallest-elements-in-an-array/
https://leetcode.com/problems/kth-largest-element-in-an-array/description/

	 * @param args
	 */
	
	public static int findKthLargest(int[] input, int k) {
		if(null==input || input.length==0) {
			return Integer.MIN_VALUE;
		}
		
		PriorityQueue<Integer> heap = new PriorityQueue<>(k);
		for(int i =0; i<k; i++) {
			heap.offer(input[i]);
		}
		
		for(int i=k;i<input.length;i++) {
			int min = heap.peek();
			int next = input[i];
			if(next>min) {
				heap.poll();
				heap.offer(next);
			}
		}
		
		return heap.poll();
	}
	
	public static void main(String[] args) {
		int[] input = new int[] {3,2,1,5,6,4};
		int k = 2;
		System.out.println(String.format("%d largest element is %d", 2, findKthLargest(input, k)));

	}

}
