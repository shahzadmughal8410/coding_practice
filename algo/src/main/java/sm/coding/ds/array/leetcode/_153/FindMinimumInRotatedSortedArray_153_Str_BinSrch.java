/**
 * 
 */
package sm.coding.ds.array.leetcode._153;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author shahzadmughal8410
 *
 */
public class FindMinimumInRotatedSortedArray_153_Str_BinSrch {

	/**
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
Find the minimum element.
You may assume no duplicate exists in the array.

Links
https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/ 
https://research.googleblog.com/2006/06/extra-extra-read-all-about-it-nearly.html 
https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/48484/A-concise-solution-with-proof-in-the-comment

Submission
https://leetcode.com/submissions/detail/167633581/
You are here!
Your runtime beats 100.00 % of java submissions.
	 * 
	 * @param args
	 */
	public static int findMin(int[] nums) {
		if(null==nums || nums.length==0) {
			return -1;
		}
		
		int low = 0;
		int high = nums.length-1;
		
		while(low < high) {
			int mid = low + ((high - low) / 2);
			if(nums[mid] < nums[high]) {
				high = mid; // minimum is in left part of array, as mid < high, for next iteration include min as well
			}else if (nums[mid] > nums[high]) {
				low = mid+1; // minimum is in right part of array, also as mid is greater can;t be min, so skip (mid+1) it for next iteration
			}
		}
		return nums[low];
	}
	
	
	public static void main(String[] args) {
		int[] nums = new int[] {4, 5, 6, 7, 0, 1, 2};
//		int minimum = findMin(nums);
		int minimum = SolutionDebug.findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {1,2,3,4,5};
		minimum = findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {6,5,4,3,2,1};
		minimum = findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {7,8,9,10,1,2,3,4,5,6};
		minimum = findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {2,1};
		minimum = findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

		nums = new int[] {14, -5, -1, 0, 6, 8, 9};
		minimum = findMin(nums);
		System.out.printf("Minimum is %d, nums=%s %n", minimum, Arrays.stream(nums).boxed().collect(Collectors.toList()));

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
	
	public static int findMin(int[] nums) {
		if(null==nums || nums.length==0) {
			return -1;
		}
		debug(Arrays.stream(nums).boxed().collect(Collectors.toList()));
		debug(IntStream.range(0, nums.length).boxed().collect(Collectors.toList()));
		int low = 0;
		int high = nums.length-1;
		tableColumns("low", "high", "mid", "nums[mid] < nums[high]", "nums[mid] > nums[high]", "search");
		debugColumns();
		while(low < high) {
			int mid = low + ((high - low) / 2);
			debugRow(low, high, mid, nums[mid] +"<"+ nums[high]+"="+(nums[mid] < nums[high]) , nums[mid] +">"+ nums[high]+"="+(nums[mid] > nums[high])  ,nums[mid] < nums[high] ? "left" : nums[mid] > nums[high]? "right" :"");
			if(nums[mid] < nums[high]) {
				high = mid; // minimum is in left part of array
			}else if (nums[mid] > nums[high]) {
				low = mid+1; // minimum is in right part of array
			}
		}
		debug("Minimum="+nums[low]);
		return nums[low];
	}
}
