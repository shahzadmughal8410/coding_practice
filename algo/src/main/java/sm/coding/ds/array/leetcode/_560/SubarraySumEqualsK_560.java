/**
 * 
 */
package sm.coding.ds.array.leetcode._560;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shahzadmughal8410
 *
 */
public class SubarraySumEqualsK_560 {

	/**

Medium

Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum equals to k.

Example 1:
Input:nums = [1,1,1], k = 2
Output: 2
Note:
The length of the array is in range [1, 20,000].
The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].

Submission
https://leetcode.com/submissions/detail/195982881/
You are here! 
Your runtime beats 86.90 % of java submissions.

	 */
    public static int subarraySum(int[] nums, int k) {
	    	if(null==nums || nums.length==0) {
	    		return 0;
	    	}
	    	
	    	Map<Integer, Integer> map = new HashMap<>();
	    	map.put(0, 1); // count is already +1, when picked up it will be incremented for next occurence of sum
	    	int count = 0;
	    	int sum = 0;
	    	for(int i =0; i<nums.length; i++) {
	    		sum+=nums[i];
	    		int difference = sum-k;
	    		if(map.containsKey(difference)) { // if difference is already seen means 1 such subarray present
	    			count += map.get(difference); // increment the count by getting the last count of that difference
	    		}
	    		map.put(sum, map.getOrDefault(sum, 0) + 1);
	    	}
        
	    	return count;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
