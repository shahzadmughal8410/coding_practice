/**
 * 
 */
package sm.coding.ds.array.leetcode._001;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class TwoSum_001_Arr {

	/**
Given an array of integers, return indices of the two numbers such that they add up to a specific target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
Example:
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].

Links
https://leetcode.com/problems/two-sum/solution/ 
https://www.youtube.com/watch?v=XKu_SEDAykw  
	 * @param args
	 */
	public static int[] twoSum(int[] nums, int target) {
		if(null==nums || nums.length==0) {
			return null;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for(int i =0; i<nums.length;i++) {
			if(map.containsKey(nums[i])) {
				return new int[] {i, map.get(nums[i])};
			}else {
				map.put(target-nums[i], i);
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {1,2,3,4,5};
		int target = 8;
		int[] result = twoSum(nums, target);
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList())+", target="+target);
		System.out.println(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		System.out.println(Arrays.stream(result).boxed().collect(Collectors.toList()));
		

	}

}
