/**
 * 
 */
package sm.coding.ds.array.leetcode._442;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class FindAllDuplicatesInAnArray_442_Arr {

	/**
Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements that appear twice in this array.
Could you do it without extra space and in O(n) runtime?
Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[2,3]

Links
https://leetcode.com/problems/find-all-duplicates-in-an-array/description/ 

Similar questions
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array  

https://leetcode.com/submissions/detail/167624339/ 
You are here!
Your runtime beats 81.63 % of java submissions.

	 * @param args
	 */
	public static List<Integer> findDuplicates(int[] nums) {
		List<Integer> duplicates = new ArrayList<>();
		for(int i = 0; i<nums.length; i++) {
			int valueAsIndex = Math.abs(nums[i])-1;
			if(nums[valueAsIndex]>0) {
				nums[valueAsIndex] = -1 * nums[valueAsIndex];
			}else {
				duplicates.add(valueAsIndex+1);
			}
		}
		
		return duplicates;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {4,3,2,7,8,2,3,1};
		List<Integer> duplicate = findDuplicates(nums);
		
		System.out.println("Numbers="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("Duplicates="+duplicate);

		// following is the invalid case as questions states that some elements are twice not more than that.
		nums = new int[] {1,1,1,1,1,1};
		duplicate = findDuplicates(nums);
		
		System.out.println("Numbers="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println("Duplicates="+duplicate);

	}

}
