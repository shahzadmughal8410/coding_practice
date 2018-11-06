/**
 * 
 */
package sm.coding.ds.array.leetcode._026;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class RemoveDuplicatesFromSortedArray_026_Arr_2P {

	/**
Given a sorted array nums, remove the duplicates in-place such that each element appear only once and return the new length.

Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.

Example 1:

Given nums = [1,1,2],

Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.

It doesn't matter what you leave beyond the returned length.
Example 2:

Given nums = [0,0,1,1,1,2,2,3,3,4],

Your function should return length = 5, with the first five elements of nums being modified to 0, 1, 2, 3, and 4 respectively.

It doesn't matter what values are set beyond the returned length.
Clarification:

Confused why the returned value is an integer but your answer is an array?

Note that the input array is passed in by reference, which means modification to the input array will be known to the caller as well.

Internally you can think of this:

// nums is passed in by reference. (i.e., without making a copy)
int len = removeDuplicates(nums);

// any modification to nums in your function would be known by the caller.
// using the length returned by your function, it prints the first len elements.
for (int i = 0; i < len; i++) {
    print(nums[i]);
}

Submission
https://leetcode.com/submissions/detail/168969668/
You are here!
Your runtime beats 79.06 % of java submissions.

	 * @param args
	 */
	public static int removeDuplicates(int[] nums) {
		if(null==nums || nums.length==0) {
			return 0;
		}
		int write=0;
		for(int i = 1 ; i<nums.length; i++) { //there is no difference if we start j=0, it works with both j=0 or j=1
			if(nums[write]!=nums[i]) {
				write++;
				nums[write]=nums[i];
			}
		}
		return write+1;
	}	
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,1,2};
		System.out.println("Original="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		int length = removeDuplicates(nums);
		System.out.println(" No Dups="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("Length = "+length);

		nums = new int[] {0,0,1,1,1,2,2,3,3,4};
		System.out.println("Original="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		length = removeDuplicates(nums);
		System.out.println(" No Dups="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("Length = "+length);

		// it works for unsorted array too, but removes consecutive duplicates elements
		nums = new int[] {0,1,2,3,4,5,6,1,1,1,2,3,3,3,2,2,9};
		System.out.println("Original="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		length = removeDuplicates(nums);
		System.out.println(" No Dups="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("Length = "+length);
}

}
