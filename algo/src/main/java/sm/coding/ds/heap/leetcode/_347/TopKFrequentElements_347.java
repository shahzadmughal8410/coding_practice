/**
 * 
 */
package sm.coding.ds.heap.leetcode._347;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author shahzadmughal8410
 *
 */
public class TopKFrequentElements_347 {

	/**

Medium
Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

Submission
https://leetcode.com/submissions/detail/198425530/
You are here! 
Your runtime beats 9.76 % of java submissions.


	 * @param nums
	 * @param k
	 * @return
	 */
	public static List<Integer> topKFrequent_Heap(int[] nums, int k) {
	    Map<Integer, Integer> counterMap = new HashMap<>();
	    for(int num : nums) {
	        int count = counterMap.getOrDefault(num, 0);
	        counterMap.put(num, count+1);
	    }
	    
	    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue()); // min heap
	    for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
	        pq.offer(entry);
	        if(pq.size() > k) {
	        		pq.poll();
	        }
	    }
	    
	    LinkedList<Integer> res = new LinkedList<>();
	    while(!pq.isEmpty()) {
	        res.addFirst(pq.poll().getKey()); //constant time operation
	    }
	    return res;
	}
	/**
Submission
https://leetcode.com/submissions/detail/198427770/
You are here! 
Your runtime beats 42.68 % of java submissions.

	 * @param nums
	 * @param k
	 * @return
	 */
	public static List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		for (int n : nums) {
			frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
		}

		List<Integer>[] bucket = new List[nums.length + 1];// length +1, for corner case when we have only 1 element in list so frequency is 1.
		for (int key : frequencyMap.keySet()) {
			int frequency = frequencyMap.get(key);
			if (bucket[frequency] == null) {
				bucket[frequency] = new ArrayList<>();
			}
			bucket[frequency].add(key);
		}

		List<Integer> res = new ArrayList<>();
		
		// loop in reverse direction as we are picking top k from sorted frequency map
		// run loop either we get top k or array in exhausted
		for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
			if (bucket[pos] != null) {
				res.addAll(bucket[pos]);
			}
		}
		return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[] {1,1,1,2,2,3,3,4,5};
		int k =2;
		List<Integer> res = topKFrequent_Heap(nums, k);
		System.out.println("Heap  ="+res);
		res = topKFrequent(nums, k);
		System.out.println("Bucket="+res);
	}

}
