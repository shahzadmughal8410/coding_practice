/**
 * 
 */
package sm.coding.ds.array.icf.subarray_sum_zero;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class ZeroSumSubarray {

	/**
Given an array of positive and negative numbers, find if there is a subarray (of size at-least one) with 0 sum.
Examples :

Input: {4, 2, -3, 1, 6}
Output: true 
There is a subarray with zero sum from index 1 to 3.

Input: {4, 2, 0, 1, 6}
Output: true 
There is a subarray with zero sum from index 2 to 2.

Input: {-3, 2, 3, 1, 6}
Output: false
There is no subarray with zero sum.
	 */
	public static boolean subArrayExists(int arr[]) {
		if(null==arr || arr.length==0) {
			return false;
		}
		Set<Integer> sums = new HashSet<>(); 
		int sum = 0;
		for(int i=0; i<arr.length; i++) {
			sum+=arr[i];
			
			// 1- arr[i]==0
			// 2- sum==0
			// 3- sum already seen
			if(arr[i]==0 || sum==0 || sums.contains(sum)) {
				return true;
			}
			// add the current sum (0 --> i) to map
			sums.add(sum);
		}
		return false;
	}
			
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int arr[] = new int[] {-3, 2, 3, 1, 6};
		boolean zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {4, 2, -3, 1, 6};
		zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {4, 2, 0, 1, 6};
		zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {-3, 2, 3, 1, 6};
		zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {-3, 3};
		zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

		arr = new int[] {0};
		zeroSumSubarray = subArrayExists(arr);
		System.out.println("zeroSumSubarray="+zeroSumSubarray+", arr="+Arrays.stream(arr).boxed().collect(Collectors.toList()));

	}

}
