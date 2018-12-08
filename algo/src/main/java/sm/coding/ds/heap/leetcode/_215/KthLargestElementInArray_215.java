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

Submission
https://leetcode.com/submissions/detail/194072511/
You are here! 
Your runtime beats 46.60 % of java submissions.
	 * @param args
	 */
	
	public static int findKthLargest(int[] nums, int k) {
		if(null==nums || nums.length==0) {
			return Integer.MIN_VALUE;
		}
		
		PriorityQueue<Integer> heap = new PriorityQueue<>(k+1);
		
		for(int i=0;i<nums.length;i++) {
			heap.offer(nums[i]);
			
			if(heap.size()>k) {
				heap.poll();
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
