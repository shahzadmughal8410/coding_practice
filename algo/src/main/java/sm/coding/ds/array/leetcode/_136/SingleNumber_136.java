/**
 * 
 */
package sm.coding.ds.array.leetcode._136;

/**
 * @author shahzadmughal8410
 *
 */
public class SingleNumber_136 {

	/**

Easy
Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

Submission
https://leetcode.com/submissions/detail/199255492/
You are here! 
Your runtime beats 79.02 % of java submissions.

	 * @param nums
	 * @return
	 */
	
	public static int findNumberWithOddCount(int[] nums) {
		int numberWithOddCount = 0;
		for(int i =0; i<nums.length;i++) {
			numberWithOddCount ^= nums[i];
		}
		return numberWithOddCount;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = new int[]{2,2,1};
		System.out.println(findNumberWithOddCount(nums));

		nums = new int[]{4,1,2,1,2};
		System.out.println(findNumberWithOddCount(nums));
		
		// works for any number of even odd count
		nums = new int[]{1,2,3,1,1,1,2,2,2,3,3,3,3};
		System.out.println(findNumberWithOddCount(nums));

	}

}
