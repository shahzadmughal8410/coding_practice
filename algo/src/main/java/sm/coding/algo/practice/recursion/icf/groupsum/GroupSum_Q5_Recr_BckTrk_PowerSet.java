/**
 * 
 */
package sm.coding.algo.practice.recursion.icf.groupsum;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class GroupSum_Q5_Recr_BckTrk_PowerSet {

	
	/**
Bonus Problem:
Given an array of ints, is it possible to choose a group of some of the ints, such that the group sums to the given target? This is a classic backtracking recursion problem. Once you understand the recursive backtracking strategy in this problem, you can use the same pattern for many problems to search a space of choices. Rather than looking at the whole array, our convention is to consider the part of the array starting at index start and continuing to the end of the array. The caller can specify the whole array simply by passing start as 0. No loops are needed -- the recursive calls progress down the array.


groupSum(0, [2, 4, 8], 10) → true
groupSum(0, [2, 4, 8], 14) → true
groupSum(0, [2, 4, 8], 9) → false

https://codingbat.com/prob/p145416

	 */
	public static boolean groupSum(int start, int[] nums, int target) {
		return groupSumHelper(nums, target, 0+start);
	}
	
	public static boolean groupSumHelper(int[] nums, int target, int index) {
		//base case, nothing to choose
		if(index == nums.length) {
			// if we found the numbers equals to target
			if(target==0) {
				return true;
			}else {
				return false;
			}
		}
		
		//recursive case
		// choose
		int choosen = nums[index];
		
		//explore
		//with including choosen towards target
		if(groupSumHelper(nums, target-choosen, index+1)) {
			return true;
		}
		// without including choosen towards target
		return groupSumHelper(nums, target, index+1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int target;
		int nums[];
		int start;
		
		target = 10;
		nums = new int[] {2, 4, 8};
		start = 0;
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		System.out.printf("Start %d, Target %d, group sum found %s%n%n", start, target, groupSum(start, nums, target));

		target = 14;
		nums = new int[] {2, 4, 8};
		start = 0;
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		System.out.printf("Start %d, Target %d, group sum found %s%n%n", start, target, groupSum(start, nums, target));

		target = 19;
		nums = new int[] {2, 4, 8};
		start = 0;
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		System.out.printf("Start %d, Target %d, group sum found %s%n%n", start, target, groupSum(start, nums, target));

		target = 12;
		nums = new int[] {2, 4, 8};
		start = 1;
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		System.out.println(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		System.out.printf("Start %d, Target %d, group sum found %s%n%n", start, target, groupSum(start, nums, target));

	}

}
