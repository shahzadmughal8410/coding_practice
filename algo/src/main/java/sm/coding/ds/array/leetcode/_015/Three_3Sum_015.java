/**
 * 
 */
package sm.coding.ds.array.leetcode._015;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author shahzadmughal8410
 *
 */
public class Three_3Sum_015 {

	/**

Medium
Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? 
Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

Submission
https://leetcode.com/submissions/detail/198471037/
You are here! 
Your runtime beats 43.93 % of java submissions.
	 */
	public static List<List<Integer>> threeSum(int[] num) {
	    Arrays.sort(num);
	    List<List<Integer>> res = new LinkedList<>(); 
	    for (int i = 0; i < num.length-2; i++) {
	    		// skip same elements
	        if (i == 0 || (i > 0 && num[i] != num[i-1])) {
	        	
	            int lo = i+1; 
	            int hi = num.length-1;
	            int sum = 0 - num[i]; // sum for two sum target
	            
	            while (lo < hi) {
	                if (num[lo] + num[hi] == sum) { // found the triplet
	                    res.add(Arrays.asList(num[i], num[lo], num[hi]));
	                    
	                    // skip the same elements
	                    while (lo < hi && num[lo] == num[lo+1]) { 
	                    		lo++;
	                    }
	                    // skip the same elements
	                    while (lo < hi && num[hi] == num[hi-1]) {
	                    		hi--;
	                    }
	                    lo++; hi--;
	                } else if (num[lo] + num[hi] < sum) { // move forward left pointer
	                		lo++;
	                }
	                else { // move back right pointer
	                		hi--;
	                }
	           }
	        }
	    }
	    return res;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
