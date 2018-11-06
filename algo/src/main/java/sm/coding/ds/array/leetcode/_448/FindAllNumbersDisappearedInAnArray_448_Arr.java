/**
 * 
 */
package sm.coding.ds.array.leetcode._448;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class FindAllNumbersDisappearedInAnArray_448_Arr {

	/**
	 * 
Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
Find all the elements of [1, n] inclusive that do not appear in this array.
Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
Example:
Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

Links
https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution

https://leetcode.com/submissions/detail/167622218/ 
You are here!
Your runtime beats 84.30 % of java submissions.

	 * @param args
	 */
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> missingNums = new ArrayList<>();
		if(null==nums || nums.length==0) {
			return missingNums;
		}
		
		for(int i =0; i<nums.length; i++) {
			int valueAsIdnex = Math.abs(nums[i]) - 1; // as numbers starting from 1 not zero, -1 will map to the correct array index
			if(nums[valueAsIdnex] > 0) { //for duplicate numbers, we will get it as negative after first time
				nums[valueAsIdnex] = -1 * nums[valueAsIdnex];// make the number negative
			}
		}
		
		for(int i =0;i<nums.length;i++) {
			if(nums[i]>0) {
				missingNums.add(i+1);// actual number will be indexLocation+1
			}
		}
		return missingNums;
	}
	
	public static void main(String[] args) {
		int[] nums = new int[] {4,3,2,7,8,2,3,1};
//		List<Integer> missingNumbers = findDisappearedNumbers(nums);
		List<Integer> missingNumbers = SolutionDebug.findDisappearedNumbers(nums);
		System.out.println(Arrays.stream(nums).boxed().collect(Collectors.toList())+", missing ="+missingNumbers);
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
	
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> missingNums = new ArrayList<>();
		if(null==nums || nums.length==0) {
			return missingNums;
		}
		debug("   nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		debug("indexes="+IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		
		tableColumns("i:2", "nums[i]", "valueAsIdnex", "nums[valueAsIdnex]", "nums:29");
		debugColumns();
		for(int i =0; i<nums.length; i++) {
			int valueAsIdnex = Math.abs(nums[i]) - 1; // as numbers starting from 1 not zero, -1 will map to the right array index
			debugRow(i, nums[i], valueAsIdnex, nums[valueAsIdnex], Arrays.stream(nums).boxed().collect(Collectors.toList()));
			if(nums[valueAsIdnex] > 0) { //for duplicates numbers we will get it as negative after first time
				nums[valueAsIdnex] = -1 * nums[valueAsIdnex];// make the number negative
			}
		}
		debug("   nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
		debug("  index="+IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		debug("index+1="+IntStream.range(1, nums.length+1).boxed().collect(Collectors.toList()));
		for(int i =0;i<nums.length;i++) {
			if(nums[i]>0) {
				missingNums.add(i+1);// actual number will be indexLocation+1
			}
		}
		debug("missing="+missingNums);
		return missingNums;
	}
}
