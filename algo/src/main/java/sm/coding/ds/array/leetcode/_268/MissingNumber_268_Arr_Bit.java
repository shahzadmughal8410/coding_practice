/**
 * 
 */
package sm.coding.ds.array.leetcode._268;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MissingNumber_268_Arr_Bit {

	/**
Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
Example 1
Input: [3,0,1]
Output: 2


Example 2
Input: [9,6,4,2,3,5,7,0,1]
Output: 8



Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

Links
https://leetcode.com/problems/missing-number/solution/ 
https://www.geeksforgeeks.org/find-the-missing-number/ 

Submission
https://leetcode.com/submissions/detail/203027806/
You are here! 
Your runtime beats 100.00 % of java submissions.
	 * 
	 * @param args
	 */
	public static int missingNumber(int[] nums) {
		int n = nums.length;
		int expectedSum =( n*(n+1) )/2;
		int calculatedSum = 0;
		for(int i =0;i<nums.length;i++) {
			calculatedSum+=nums[i];
		}
		return expectedSum-calculatedSum;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {3,0,1};
		int missing = missingNumber(nums);
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList())+" missing number is "+missing);
		
		nums = new int[] {9,6,4,2,3,5,7,0,1};
		missing = missingNumber(nums);
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList())+" missing number is "+missing);

	}

}
