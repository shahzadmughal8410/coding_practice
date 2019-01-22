/**
 * 
 */
package sm.coding.ds.llstq.leetcode.linked_list._703;

import java.util.PriorityQueue;

/**
 * @author shahzadmughal8410
 *
 */
public class KthLargestElementInAStream_703 {

	/**
Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Your KthLargest class will have a constructor which accepts an integer k and an integer array nums, which contains initial elements from the stream. For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.

Example:

int k = 3;
int[] arr = [4,5,8,2];
KthLargest kthLargest = new KthLargest(3, arr);
kthLargest.add(3);   // returns 4
kthLargest.add(5);   // returns 5
kthLargest.add(10);  // returns 5
kthLargest.add(9);   // returns 8
kthLargest.add(4);   // returns 8
Note: 
You may assume that nums' length ≥ k-1 and k ≥ 1.
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		KthLargest o = new KthLargest(3, new int[] {5,5,5,2,7,6,1});
		System.out.println("Kth largest="+o.add(2));
		for(int i =0; i<100; i++) {
			o.add(i);
		}
		System.out.println("Kth largest="+o.add(2));
	}

}
/**
Submission
https://leetcode.com/submissions/detail/187691285/
You are here! 
Your runtime beats 98.29 % of java submissions.

 * @author shahzadmughal8410
 *
 */
class KthLargest {

	PriorityQueue<Integer> q;
	int k;
	
    public KthLargest(int k, int[] nums) {
    		q = new PriorityQueue<>(k+1); // min heap
    		this.k = k;
    		for(int i:nums) {
    			add(i);
    		}
    }
    
    public int add(int val) {
        q.offer(val);
        if(q.size()>k) {
        		q.poll();
        }
        return q.peek();
    }
}
