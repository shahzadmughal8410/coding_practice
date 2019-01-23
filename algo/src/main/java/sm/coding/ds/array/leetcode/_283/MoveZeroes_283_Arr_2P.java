/**
 * 
 */
package sm.coding.ds.array.leetcode._283;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410
 *
 */
public class MoveZeroes_283_Arr_2P {

	/**
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.


Links
https://leetcode.com/problems/move-zeroes/solution/ 
https://www.geeksforgeeks.org/move-zeroes-end-array/  

https://leetcode.com/submissions/detail/203103105/
You are here!
Your runtime beats 100.00 % of java submissions.

	 * @param args
	 */
	public static void moveZeroes(int[] nums) {
		int wi = 0;
		for(int ri =0; ri<nums.length; ri++) {
			if(nums[ri]!=0) {
				if(wi!=ri) {
					nums[wi] = nums[ri];
					nums[ri]=0;
				}
				++wi;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {0, 1, 0, 3, 12,0,0,0,0};
		String before = "Before="+Arrays.stream(nums).boxed().collect(Collectors.toList());
		moveZeroes(nums);
//		SolutionDebug.moveZeroes(nums);
		System.out.println(before);
		System.out.println(" After="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {5, 0, 1, 0, 3, 12};
		before = "Before="+Arrays.stream(nums).boxed().collect(Collectors.toList());
//		moveZeroes(nums);
		SolutionDebug.moveZeroes(nums);
		System.out.println(before);
		System.out.println(" After="+Arrays.stream(nums).boxed().collect(Collectors.toList()));

	}

}

class SolutionDebug {

	static StringBuilder format = new StringBuilder();
	static String [] columns = new String[] {};
	public static void tableColumns(String ... cols) {
		for(int i = 0; i<cols.length;i++) {
			String c = cols[i];
			if(c.indexOf(":")!=-1) {
				String[] arr = c.split(Pattern.quote(":"));
				format.append("|%-").append(arr[1]).append("s ");
				cols[i] = arr[0];
			}else {
				format.append("|%-").append(c.length()).append("s ");
			}
			
		}
		format.append("|");
//		debugRow(cols);
		columns = cols;
	}
	
	public static void debugRow(Object ... cols) {
		debug(String.format(format.toString(), cols));
	}

	public static void debug(Object msg) {
		System.out.println("DEBUG: "+msg);
	}

	public static void debugColumns() {
		debugRow(columns);
	}
	
	public static void reset() {
		format = new StringBuilder();
		columns = new String[] {};
	}
	
	public static void moveZeroes(int[] nums) {
		int wi = 0;
		int writes = 0;
		tableColumns("wi", "ri:3", "nums[i]", "writes", "nums:30");
		debugColumns();
		for(int ri =0; ri<nums.length; ri++) {
			debugRow(wi, ri, nums[ri], writes, Arrays.stream(nums).boxed().collect(Collectors.toList()));
			if(nums[ri]!=0) {
				nums[wi] = nums[ri];
				++writes;
				if(wi!=ri) {
					nums[ri]=0;
					++writes;
				}
				++wi;
			}
		}
		debug("writes="+writes+", nonZeroIndex="+wi);
	}
}