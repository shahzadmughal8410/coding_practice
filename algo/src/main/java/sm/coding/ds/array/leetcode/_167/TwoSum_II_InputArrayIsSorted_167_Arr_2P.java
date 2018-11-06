/**
 * 
 */
package sm.coding.ds.array.leetcode._167;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class TwoSum_II_InputArrayIsSorted_167_Arr_2P {

	/**
Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a specific target number.
The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
You may assume that each input would have exactly one solution and you may not use the same element twice.
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

Links


Similar questions
Two Sum
https://www.geeksforgeeks.org/count-pairs-with-given-sum/  
	 * 
	 * @param args
	 */
	public static int[] twoSum(int[] arr, int target) {
		int[] result = new int[2];
		
		int left = 0;
		int right = arr.length-1;
		
		while(left<right) {
			int sum = arr[left]+arr[right]; 
			if(sum == target) {
				result[0] = left+1;
				result[1] = right+1;
				break;
			}else if(sum>target) {
				right--;
			}else {
				left++;
			}
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		int[] arr = new int[] {2, 7, 11, 15};
		int target = 13;
		System.out.println("Target="+target+",  Array: "+Arrays.stream(arr).boxed().collect(Collectors.toList()));
		int[] result = twoSum(arr, target);
		System.out.println("Indexes are: "+Arrays.stream(result).boxed().collect(Collectors.toList()));

	}

}
