/**
 * 
 */
package sm.coding.ds.array.leetcode._075;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author shahzadmughal8410

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]

Follow up:

    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
    Could you come up with a one-pass algorithm using only constant space?

Links
https://leetcode.com/problems/sort-colors/discuss/157080/3-way-partitioning
https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/ 
https://www.geeksforgeeks.org/3-way-quicksort-dutch-national-flag/ 
https://www.toptal.com/developers/sorting-algorithms/quick-sort-3-way 
http://users.monash.edu/~lloyd/tildeAlgDS/Sort/Flag/ 


Submission
https://leetcode.com/submissions/detail/168443402/
You are here!
Your runtime beats 100.00 % of java submissions.

 *
 */
public class DutchNationalFlag_SortColors_075_Arr_Quick_Sort {

	public static void sortColors(int[] nums) {
		int lo = 0;
		int mid = 0;
		int hi = nums.length - 1;
		while (mid <= hi) {
			switch (nums[mid]) {
				case 0: {
					swap(lo, mid, nums);
					lo++;
					mid++;
					break;
				}
				case 1:
					mid++;
					break;
				case 2: {
					swap(mid, hi, nums);
					hi--;
					break;
				}
			}
		}
	}

	public static void swap(int a, int b, int nums[]) {
		int temp   =  nums[a];
        nums[a]  = nums[b];
        nums[b] = temp;	
    }
	
	public static void main(String[] args) {
		int nums[] = new int[] {2,0,2,1,1,0} ;  
//		int nums[] = new int[] {1, 2, 1, 2, 1, 1, 0, 1, 0, 1} ;  
//		sortColors(nums);
		SolutionDebug.sortColors(nums);
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
	
	public static void sortColors(int[] nums) {
		int lo = 0;
		int hi = nums.length - 1;
		int mid = 0;
		while (mid <= hi) {
			debug("lo="+lo+", mid="+mid+", hi="+hi+", nums["+mid+"]="+nums[mid]+" nums="+Arrays.stream(nums).boxed().collect(Collectors.toList()));
			switch (nums[mid]) {
				case 0: {
					swap(lo, mid, nums);
					lo++;
					mid++;
					break;
				}
				case 1:
					mid++;
					break;
				case 2: {
					swap(mid, hi, nums);
					hi--;
					break;
				}
			}
		}
	}
	
	public static void swap(int a, int b, int nums[]) {
		debug("Swap nums["+a+"]="+nums[a]+" with nums["+b+"]="+nums[b]);
		int temp   =  nums[a];
        nums[a]  = nums[b];
        nums[b] = temp;	
    }

}
