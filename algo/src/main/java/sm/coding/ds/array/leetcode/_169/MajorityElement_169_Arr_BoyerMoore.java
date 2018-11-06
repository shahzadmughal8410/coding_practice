/**
 * 
 */
package sm.coding.ds.array.leetcode._169;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MajorityElement_169_Arr_BoyerMoore {

	/**
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.

Links
https://leetcode.com/problems/majority-element/solution/
https://www.geeksforgeeks.org/majority-element/  

Submission
https://leetcode.com/submissions/detail/151305743/ 
You are here!
Your runtime beats 100.00 % of java submissions.

	 * @param args
	 */
	public static int majorityElement(int[] nums) { //assumes that array must have the majority element
		if(null==nums || nums.length==0) {
			return 0;
		}
		
		int majorityIndex = 0;
		int count = 1;
		
		for(int i=1;i<nums.length;i++) {
			if(nums[majorityIndex] == nums[i]) {
				count++;
			}else {
				count--;
			}
			
			if(count==0) {
				majorityIndex = i;
				count = 1;
			}
		}
		
		return nums[majorityIndex];
	}
	
	// assumes array may or may not have the majority element i.e. n/2
	public static Object[] majorityElement_Array_has_No_Majority_Element(int[] nums) {
		Object[] result = new Object[] {false, 0};
		int candidateMajorityElement = majorityElement(nums);
		int count = 0;
		for(int n:nums) {
			if(n == candidateMajorityElement) {
				++count;
			}
		}
		if(count > (nums.length/2)) {
			result[0] = true;
			result[1] = candidateMajorityElement;
		}
		return result;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1, 3, 3, 1, 2, 3};
		int majorityElement = majorityElement(nums);
		System.out.println("Majority element ="+majorityElement+" , nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {-1,-1,-1, 1, 3, 3, -1,-1,-1, -1, -1, 1, 2, 3, 0};
		majorityElement = majorityElement(nums);
		System.out.println("Majority element ="+majorityElement+" , nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {5};
		majorityElement = majorityElement(nums);
		System.out.println("Majority element ="+majorityElement+" , nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {7,5,6,7};
		majorityElement = majorityElement(nums);
		System.out.println("Majority element ="+majorityElement+" , nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

	}

}
